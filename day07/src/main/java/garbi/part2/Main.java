package garbi.part2;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

        // Normalizar ancho rellenando con espacios
        String[] grid = new String[filas];
        for (int r = 0; r < filas; r++) {
            String s = lineas.get(r);
            grid[r] = s.length() < columnas ? String.format("%-" + columnas + "s", s) : s;
        }

        // Encontrar la 'S'
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

        // El haz comienza justo debajo de S
        int initR = startR + 1;
        int initC = startC;

        // memo: codigo(r,c) -> cantidad de timelines (BigInteger)
        Map<Long, BigInteger> memo = new HashMap<>();
        // visiting para detectar ciclos (por si acaso)
        Set<Long> visiting = new HashSet<>();

        BigInteger totalTimelines = contar(initR, initC, grid, filas, columnas, memo, visiting);

        System.out.println("Total de timelines (mundos) posibles: " + totalTimelines);
    }

    // Codifica una posición (r,c) en un long
    private static long code(int r, int c) {
        return (((long) r) << 32) | (c & 0xffffffffL);
    }

    private static BigInteger contar(int r, int c, String[] grid, int filas, int columnas,
                                     Map<Long, BigInteger> memo, Set<Long> visiting) {
        // Salida del manifold -> una timeline
        if (r < 0 || r >= filas || c < 0 || c >= columnas) {
            return BigInteger.ONE;
        }

        long key = code(r, c);
        if (memo.containsKey(key)) return memo.get(key);

        // Protección contra ciclos: si encontramos un ciclo, es un problema del input.
        if (visiting.contains(key)) {
            throw new IllegalStateException("Se detectó un ciclo en el manifold en la celda r=" + r + " c=" + c);
        }

        visiting.add(key);

        char ch = grid[r].charAt(c);
        BigInteger res;

        if (ch == '^') {
            // Split: suma de las timelines de izquierda y derecha (misma fila)
            BigInteger left = contar(r, c - 1, grid, filas, columnas, memo, visiting);
            BigInteger right = contar(r, c + 1, grid, filas, columnas, memo, visiting);
            res = left.add(right);
        } else {
            // Continúa hacia abajo
            res = contar(r + 1, c, grid, filas, columnas, memo, visiting);
        }

        visiting.remove(key);
        memo.put(key, res);
        return res;
    }
}
