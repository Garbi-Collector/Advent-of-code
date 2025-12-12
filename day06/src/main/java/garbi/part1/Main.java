package garbi.part1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<String> lineas = Files.readAllLines(Path.of("inputs06.txt"));

        // Asegurar que todas las líneas tengan el mismo largo
        int ancho = 0;
        for (String s : lineas) {
            if (s.length() > ancho) ancho = s.length();
        }

        List<String> canvas = new ArrayList<>();
        for (String s : lineas) {
            if (s.length() < ancho) {
                canvas.add(String.format("%-" + ancho + "s", s)); // rellenar con espacios derecha
            } else {
                canvas.add(s);
            }
        }

        long total = 0;

        int col = 0;

        while (col < ancho) {

            // Saltar columnas vacías (separadores)
            boolean columnaVacia = true;
            for (String fila : canvas) {
                if (fila.charAt(col) != ' ') {
                    columnaVacia = false;
                    break;
                }
            }

            if (columnaVacia) {
                col++;
                continue;
            }

            // Ya encontramos una columna perteneciente a un problema
            // Vamos a capturar TODAS las columnas de ese problema
            int start = col;
            while (col < ancho) {

                boolean vacia = true;
                for (String fila : canvas) {
                    if (fila.charAt(col) != ' ') {
                        vacia = false;
                        break;
                    }
                }

                if (vacia) break; // fin del problema

                col++;
            }
            int end = col - 1;

            // Procesar el rango de columnas start..end como un problema
            List<String> numeros = new ArrayList<>();
            char operacion = '?';

            for (int i = 0; i < canvas.size(); i++) {
                String fila = canvas.get(i);
                String slice = fila.substring(start, end + 1).trim();

                if (slice.equals("*") || slice.equals("+")) {
                    operacion = slice.charAt(0);
                } else if (!slice.isEmpty()) {
                    numeros.add(slice);
                }
            }

            // Resolver el problema
            long resultado = (operacion == '+') ? 0 : 1;

            for (String num : numeros) {
                long n = Long.parseLong(num);
                if (operacion == '+') resultado += n;
                else resultado *= n;
            }

            total += resultado;
        }

        System.out.println("Resultado total: " + total);
    }
}
