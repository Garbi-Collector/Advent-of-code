package garbi.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("inputs01.txt"));
        long resultado = 0;

        for (String line : lines) {
            String mejor = maxSubsequence12(line);
            long valor = Long.parseLong(mejor);
            resultado += valor;
        }

        System.out.println("El resultado es " + resultado);
    }

    // Elige la subsecuencia lexicográficamente mayor de longitud EXACTA 12
    public static String maxSubsequence12(String s) {
        int k = 12;
        int n = s.length();
        int toRemove = n - k;

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {

            while (!stack.isEmpty() && toRemove > 0 && stack.peekLast() < c) {
                stack.pollLast();
                toRemove--;
            }
            stack.addLast(c);
        }

        // Si todavía faltan sobrantes por remover, se quitan del final
        while (stack.size() > k) {
            stack.pollLast();
        }

        // Convertimos la pila en String
        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c);

        return sb.toString();
    }
}
