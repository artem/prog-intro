import java.io.*;
import java.util.*;

public class WordStatIndex {
    private static void addToStats(String word, Map<String, Integer> list,
                                    Map<String, LinkedHashSet<Integer> >  position,
                                    int idx) {
        list.put(word, list.getOrDefault(word, 0) + 1);
        LinkedHashSet<Integer> chain = position.getOrDefault(word, new LinkedHashSet<>());
        chain.add(idx);
        position.put(word, chain);
    }

    public static void main(String[] args) {
        Map<String, Integer> statWords = new LinkedHashMap<>();
        Map<String, LinkedHashSet<Integer> > occurs = new HashMap<>();

        if (args.length != 2) {
            System.err.println("Usage: Word <input file> <output file>");
            return;
        }

        try {
            FastScanner sc = new FastScanner(new File(args[0]));

            int idx = 1;
            while (sc.hasNextWord()) {
                String word = sc.nextWord();
                addToStats(word.toLowerCase(), statWords, occurs, idx++);
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
                for (Map.Entry<String, Integer> pair : statWords.entrySet()) {
                    writer.print(pair.getKey() + " " + pair.getValue());
                    for (int idx : occurs.get(pair.getKey())) {
                        writer.print(" " + idx);
                    }
                    writer.println();
                }
            } finally {
                writer.close();
                //while(args[1].equals("test8.out")) {}
            }
        } catch (IOException e) {
            System.err.println("IOException during write: " + e.getMessage());
            e.printStackTrace();
        }
    }
}