package garbi.part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<String> lineas = Files.readAllLines(Path.of("inputs06.txt"));

        // Asegurar que todas las líneas tengan el mismo largo
        int ancho = lineas.stream().mapToInt(String::length).max().orElse(0);

        List<String> canvas = new ArrayList<>();
        for (String s : lineas) {
            canvas.add(String.format("%-" + ancho + "s", s));
        }

        long total = 0;
        int col = 0;

        while (col < ancho) {

            // Saltar columnas vacías
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

            // Detectar un bloque de columnas contiguas
            int start = col;
            while (col < ancho) {
                boolean vacia = true;
                for (String fila : canvas)
                    if (fila.charAt(col) != ' ')
                        vacia = false;

                if (vacia) break;
                col++;
            }
            int end = col - 1;

            // Procesar el problema vertical
            List<Long> numeros = new ArrayList<>();
            char operacion = '?';

            for (int c = start; c <= end; c++) {

                StringBuilder sb = new StringBuilder();

                for (int fila = 0; fila < canvas.size(); fila++) {
                    char ch = canvas.get(fila).charAt(c);

                    if (ch == '+' || ch == '*') {
                        operacion = ch;
                    } else if (Character.isDigit(ch)) {
                        sb.append(ch);
                    }
                }

                if (sb.length() > 0) {
                    numeros.add(Long.parseLong(sb.toString()));
                }
            }

            // Resolver el problema
            long resultado = (operacion == '+') ? 0 : 1;
            for (long n : numeros) {
                if (operacion == '+') resultado += n;
                else resultado *= n;
            }

            total += resultado;
        }

        System.out.println("Resultado total: " + total);
    }
}
