import java.io.*;
import java.util.*;

public class WordStatInput {
    private static boolean isWordChar(char c) {
        boolean isLetter = Character.isLetter(c);
        boolean isDash = Character.getType(c) == Character.DASH_PUNCTUATION;
        boolean isApostrophe = c == '\'';
        return isLetter || isDash || isApostrophe;
    }

    private static void addToStats(String word, ArrayList<String> list, ArrayList<Integer> occurences) {
        int pos = list.indexOf(word);

        if (pos != -1) {
            occurences.set(pos, occurences.get(pos) + 1);
            return;
        }

        list.add(word);
        occurences.add(1);
        return;
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<Integer> wordOccurs = new ArrayList<>();

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
                for (int i = 0; i < words.size(); i++) {
                    writer.println(words.get(i) + ' ' + wordOccurs.get(i));
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}