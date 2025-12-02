package garbi.part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

//es gracioso porque entendi mal la parte uno y sin querer resolvi la parte dos

public class Main {
    public static void main(String[] args)throws Exception{
        List<String> lines = Files.readAllLines(Path.of("inputs01.txt"));

        int pos = 50, countZero = 0;

        for (String line : lines) {
            char dir = line.charAt(0);
            int steps = Integer.parseInt(line.substring(1));

            for (int i = 0; i < steps; i++)
            {
                if (dir == 'R') {
                    pos = (pos + 1) % 100;
                }
                else {
                    pos = (pos - 1 + 100) % 100;
                }
                if (pos == 0) {
                    countZero++; }
            }
        }
        System.out.println("La respuesta es: " + countZero);
    }
}