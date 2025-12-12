package garbi.day7;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> lineas = Files.readAllLines(Path.of("inputs07.txt"));

        if (lineas.isEmpty()) {
            System.out.println("Entrada vacía.");
            return;
        }

        int filas = lineas.size();
        int columnas = lineas.stream().mapToInt(String::length).max().orElse(0);

        // Normalizar ancho (rellenar con espacios a la derecha)
        String[] grid = new String[filas];
        for (int r = 0; r < filas; r++) {
            String s = lineas.get(r);
            if (s.length() < columnas) {
                grid[r] = String.format("%-" + columnas + "s", s);
            } else {
                grid[r] = s;
            }
        }

        // Encontrar la S (suponemos exactamente una S)
        int startR = -1, startC = -1;
        outer:
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                if (grid[r].charAt(c) == 'S') {
                    startR = r;
                    startC = c;
                    break outer;
                }
            }
        }

        if (startR == -1) {
            System.out.println("No se encontró 'S' en el diagrama.");
            return;
        }

        // BFS sobre celdas (fila, col)
        Deque<long[]> queue = new ArrayDeque<>();
        Set<Long> visited = new HashSet<>(); // codificamos (r,c) en long: (r << 32) | c

        // Haz inicial empieza justo debajo de S
        int initR = startR + 1;
        if (initR >= filas) {
            System.out.println("Resultado total de splits: 0 (S está en la última fila)");
            return;
        }

        queue.add(new long[]{initR, startC});

        long splits = 0;

        while (!queue.isEmpty()) {
            long[] p = queue.poll();
            int r = (int) p[0];
            int c = (int) p[1];

            // Bounds check
            if (r < 0 || r >= filas || c < 0 || c >= columnas) continue;

            long code = (((long) r) << 32) | (c & 0xffffffffL);
            if (visited.contains(code)) continue;
            visited.add(code);

            char ch = grid[r].charAt(c);

            if (ch == '^') {
                // Este haz es partido aquí: cuenta 1 split y generamos dos nuevos haces
                splits++;
                // izquierda
                int lc = c - 1;
                if (lc >= 0) {
                    long codeL = (((long) r) << 32) | (lc & 0xffffffffL);
                    if (!visited.contains(codeL)) queue.add(new long[]{r, lc});
                }
                // derecha
                int rc = c + 1;
                if (rc < columnas) {
                    long codeR = (((long) r) << 32) | (rc & 0xffffffffL);
                    if (!visited.contains(codeR)) queue.add(new long[]{r, rc});
                }
            } else {
                // espacio vacío (o S u otro), el haz continúa hacia abajo
                int nr = r + 1;
                if (nr < filas) {
                    long codeDown = (((long) nr) << 32) | (c & 0xffffffffL);
                    if (!visited.contains(codeDown)) queue.add(new long[]{nr, c});
                }
                // si nr >= filas, el haz sale del manifold (nada que hacer)
            }
        }

        System.out.println("Cantidad de splits totales: " + splits);
    }
}
