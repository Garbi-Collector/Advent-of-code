package garbi.part1;

import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of("inputs01.txt"));

        int pos = 50;
        int countZero = 0;

        for (String line : lines) {

            char dir = line.charAt(0);
            int steps = Integer.parseInt(line.substring(1));

            if (dir == 'R') {
                pos = (pos + steps) % 100;
            } else {
                pos = (pos - steps + 10000) % 100;
            }

            if (pos == 0) {
                countZero++;
            }
        }


        System.out.println("La respuesta es: " + countZero);
    }
}