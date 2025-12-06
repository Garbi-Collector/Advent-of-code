package garbi.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("inputs01.txt"));
        List<StringBuilder> grid = new ArrayList<>();

        // Convertir a StringBuilder para poder modificar
        for (String s : lines) {
            grid.add(new StringBuilder(s));
        }

        int totalRemoved = simulateRemovals(grid);
        System.out.println("Rollos retirados en total: " + totalRemoved);
    }

    private static int simulateRemovals(List<StringBuilder> grid) {
        int rows = grid.size();
        int cols = grid.get(0).length();

        int[][] dirs = {
                {-1, -1}, {-1, 0}, {-1, 1},
                { 0, -1},          { 0, 1},
                { 1, -1}, { 1, 0}, { 1, 1}
        };

        int removedCount = 0;

        while (true) {
            List<int[]> toRemove = new ArrayList<>();

            // 1. Encontrar todos los accesibles
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid.get(r).charAt(c) != '@') continue;

                    int neighbors = 0;

                    for (int[] d : dirs) {
                        int nr = r + d[0];
                        int nc = c + d[1];

                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                            if (grid.get(nr).charAt(nc) == '@') {
                                neighbors++;
                            }
                        }
                    }

                    if (neighbors < 4) {
                        toRemove.add(new int[]{r, c});
                    }
                }
            }

            // 2. Si no hay nada para remover → terminamos
            if (toRemove.isEmpty()) break;

            // 3. Remover todos los accesibles encontrados
            for (int[] pos : toRemove) {
                grid.get(pos[0]).setCharAt(pos[1], '.');
            }

            removedCount += toRemove.size();
        }

        return removedCount;
    }
}
