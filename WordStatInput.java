import java.io.*;
import java.util.*;

public class WordStatInput {
    private static boolean isWordChar(char c) {
        boolean isLetter = Character.isLetter(c);
        boolean isDash = Character.getType(c) == Character.DASH_PUNCTUATION;
        boolean isApostrophe = c == '\'';
        return isLetter || isDash || isApostrophe;
    }

    private static void addToStats(String word, String[] list, int[] occurences) {
        int idx = 0;
        while (idx < list.length && list[idx] != null) {
            if (list[idx].equals(word)) {
                occurences[idx]++;
                return;
            }
            idx++;
        }
        list[idx] = word;
        occurences[idx]++;
    }

    public static void main(String[] args) {
        String[] words = new String[1_000_000];
        int[] wordOccurs = new int[1_000_000]; 
        int statSize = 0;

        if (args.length != 2) {
            System.err.println("Usage: Word <input file> <output file>");
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "utf8"));
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    for (int i = 0; i < line.length(); i++) {
                        int idxStart = i;
                        while (i < line.length() && isWordChar(line.charAt(i))) {
                            i++;
                        }

                        if (idxStart < i) {
                            String word = line.substring(idxStart, i).toLowerCase();
                            //System.out.println(line.substring(idxStart, i));
                            addToStats(word, words, wordOccurs);
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
                int idx = 0;
                while (words[idx] != null) {
                    writer.println(words[idx] + ' ' + wordOccurs[idx]);
                    idx++;
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}