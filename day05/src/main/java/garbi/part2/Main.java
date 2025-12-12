package garbi.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> lineas = Files.readAllLines(Path.of("inputs01.txt"));

        List<long[]> rangos = new ArrayList<>();

        // Leer solo la primera parte del archivo (hasta la línea vacía)
        for (String linea : lineas) {
            linea = linea.trim();
            if (linea.isEmpty()) break;

            String[] partes = linea.split("-");
            long inicio = Long.parseLong(partes[0]);
            long fin = Long.parseLong(partes[1]);
            rangos.add(new long[]{inicio, fin});
        }

        // Ordenar los rangos por inicio
        rangos.sort(Comparator.comparingLong(r -> r[0]));

        // Fusionar rangos solapados
        List<long[]> fusionados = new ArrayList<>();

        long[] actual = rangos.get(0);

        for (int i = 1; i < rangos.size(); i++) {
            long[] siguiente = rangos.get(i);

            if (siguiente[0] <= actual[1] + 1) {
                // Se solapan → extender rango
                actual[1] = Math.max(actual[1], siguiente[1]);
            } else {
                // No se solapa → guardar y avanzar
                fusionados.add(actual);
                actual = siguiente;
            }
        }

        fusionados.add(actual); // último rango

        // Contar IDs únicos cubiertos
        long totalIDs = 0;

        for (long[] r : fusionados) {
            totalIDs += (r[1] - r[0] + 1);
        }

        System.out.println("Total de ingredientes frescos según los rangos: " + totalIDs);
    }
}
