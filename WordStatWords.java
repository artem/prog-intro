import java.io.*;
import java.util.*;

public class WordStatWords {
    private static boolean isWordChar(char c) {
        boolean isLetter = Character.isLetter(c);
        boolean isDash = Character.getType(c) == Character.DASH_PUNCTUATION;
        boolean isApostrophe = c == '\'';

        return isLetter || isDash || isApostrophe;
    }

    private static void addToStats(String word, TreeMap<String, Integer> list) {
        int value = 0;

        if (list.containsKey(word)) {
            value = list.get(word);
        }

        list.put(word, value + 1);
    }

    public static void main(String[] args) {
        TreeMap<String, Integer> statWords = new TreeMap<>();

        if (args.length != 2) {
            System.err.println("Usage: Word <input file> <output file>");
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]), "utf8"
                ), 1024
            );

            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int i = 0; i < line.length(); i++) {
                        int idxStart = i;
                        while (i < line.length() && isWordChar(line.charAt(i))) {
                            i++;
                        }

                        if (idxStart < i) {
                            String word = line.substring(idxStart, i).toLowerCase();
                            addToStats(word, statWords);
                        }
                    }
                }
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported encoding: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(args[1]));
            try {
                for (Map.Entry<String, Integer> pair : statWords.entrySet()) {
                    writer.println(pair.getKey() + ' ' + pair.getValue());
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}