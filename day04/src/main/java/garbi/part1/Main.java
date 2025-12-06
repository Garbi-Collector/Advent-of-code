package garbi.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("inputs01.txt"));

        int result = countAccessible(lines);
        System.out.println("Rollos accesibles: " + result);
    }

    private static int countAccessible(List<String> grid) {
        int rows = grid.size();
        int cols = grid.get(0).length();
        int count = 0;

        // 8 posibles direcciones
        int[][] dirs = {
                {-1, -1}, {-1, 0}, {-1, 1},
                { 0, -1},          { 0, 1},
                { 1, -1}, { 1, 0}, { 1, 1}
        };

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (grid.get(r).charAt(c) != '@') continue;  // no es rollo

                int neighbors = 0;

                // Revisar las 8 direcciones
                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    // Verificar límites
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                        if (grid.get(nr).charAt(nc) == '@') {
                            neighbors++;
                        }
                    }
                }

                // Condición: menos de 4 rollos alrededor
                if (neighbors < 4) {
                    count++;
                }
            }
        }

        return count;
    }
}
