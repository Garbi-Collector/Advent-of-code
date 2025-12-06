package garbi.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("inputs01.txt"));
        List<String> numerosGrande = new ArrayList<>();
        long resultado =0;

        for (String line: lines){
            char primerDigito = digitoGrande(line.substring(0, line.length() - 1));

            // Cortamos el string desde el primer dígito grande
            String parteDerecha = cortarDesdePrimerDigito(line, primerDigito);

            // Encontramos el segundo dígito desde la parte derecha
            char segundoDigito = digitoGrande(parteDerecha);

            numerosGrande.add("" + primerDigito + segundoDigito);
        }


        for(String n: numerosGrande){
            long linea = Long.parseLong(Long.toString(Long.parseLong(n)));
            resultado += linea;
        }
        System.out.println("el resultado es "+ resultado);
    }


    public static char digitoGrande(String linea){
        char[] caracteres = linea.toCharArray();

        Character caracterGrande = null;

        for (Character c: caracteres) {

            if (caracterGrande == null || caracterGrande< c){
                caracterGrande=c;
            }
        }
        //salta una advertencia de que es probable recibir un nulo, pero debido a como funciona este problema nunca recibiremos nulo
        return caracterGrande;
    }
    public static String cortarDesdePrimerDigito(String linea, char digitoGrande) {
        int index = linea.indexOf(digitoGrande);
        if (index == -1 || index == linea.length() - 1) {
            return ""; // si no hay nada a la derecha
        }
        return linea.substring(index + 1);
    }

}