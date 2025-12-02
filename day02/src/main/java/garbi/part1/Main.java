package garbi.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
//este me costo
public class Main {

    public static void main(String[] args) throws IOException {
        String line = Files.readString(Path.of("inputs01.txt")).trim();

        String[] ranges = line.split(",");

        long totalSum = 0;

        for (String range : ranges) {
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            for (long n = start; n <= end; n++) {
                if (isInvalid(n)) {
                    totalSum += n;
                }
            }
        }

        System.out.println("Suma total de IDs inválidos = " + totalSum);
    }

    /**
     * Un ID inválido es aquel compuesto por una secuencia repetida dos veces.
     */
    private static boolean isInvalid(long number) {
        String s = Long.toString(number);

        if (s.length() % 2 != 0) return false;

        int mid = s.length() / 2;
        String left = s.substring(0, mid);
        String right = s.substring(mid);

        return left.equals(right);
    }
}
