import java.io.*;
import java.util.*;

public class WordStatIndex {
    private static void addToStats(String word, Map<String, LinkedHashSet<Integer> > list, int idx) {
        LinkedHashSet<Integer> chain = list.getOrDefault(word, new LinkedHashSet<>());
        chain.add(idx);
        list.put(word, chain);
    }

    public static void main(String[] args) {
        Map<String, LinkedHashSet<Integer> > statWords = new LinkedHashMap<>();

        if (args.length != 2) {
            System.err.println("Usage: Word <input file> <output file>");
            return;
        }

        try {
            FastScanner sc = new FastScanner(new File(args[0]));

            int idx = 1;
            while (sc.hasNextWord()) {
                String word = sc.nextWord();
                addToStats(word.toLowerCase(), statWords, idx++);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported input encoding: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException during read: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(args[1]));
            try {
                for (Map.Entry<String, LinkedHashSet<Integer> > pair : statWords.entrySet()) {
                    writer.print(pair.getKey() + " " + pair.getValue().size());
                    for (int idx : pair.getValue()) {
                        writer.print(" " + idx);
                    }
                    writer.println();
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("IOException during write: " + e.getMessage());
            e.printStackTrace();
        }
    }
}