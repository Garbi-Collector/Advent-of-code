package garbi.part1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<String> lineas = Files.readAllLines(Path.of("inputs01.txt"));

        List<long[]> rangos = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        boolean leyendoRangos = true;

        for (String linea : lineas) {
            linea = linea.trim();

            if (linea.isEmpty()) {
                leyendoRangos = false;
                continue;
            }

            if (leyendoRangos) {
                String[] partes = linea.split("-");
                long inicio = Long.parseLong(partes[0]);
                long fin = Long.parseLong(partes[1]);
                rangos.add(new long[]{inicio, fin});
            } else {
                ids.add(Long.parseLong(linea));
            }
        }

        int frescos = 0;

        for (long id : ids) {
            boolean esFresco = false;

            for (long[] rango : rangos) {
                long inicio = rango[0];
                long fin = rango[1];

                if (id >= inicio && id <= fin) {
                    esFresco = true;
                    break;
                }
            }

            if (esFresco) {
                frescos++;
            }
        }

        System.out.println("Cantidad de ingredientes frescos: " + frescos);
    }
}
