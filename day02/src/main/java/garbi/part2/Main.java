package garbi.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    private static boolean isInvalid(long number) {
        String s = Long.toString(number);
        int len = s.length();

        for (int seqLen = 1; seqLen <= len / 2; seqLen++) {
            if (len % seqLen != 0) continue;

            String seq = s.substring(0, seqLen);

            StringBuilder sb = new StringBuilder();
            int repeat = len / seqLen;

            for (int i = 0; i < repeat; i++) {
                sb.append(seq);
            }

            if (sb.toString().equals(s)) {
                return true;
            }
        }

        return false;
    }
}
