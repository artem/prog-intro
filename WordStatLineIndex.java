import java.io.*;
import java.util.*;

public class WordStatLineIndex {
    private static void addToStats(String word, Map<String, Set<List<Integer> > > list, int idxStr, int idxWord) {
        Set<List<Integer> > chain = list.getOrDefault(word, new LinkedHashSet<>());
        List<Integer> wordPos = List.of(idxStr, idxWord);
        chain.add(wordPos);
        list.put(word, chain);
    }
    private static void parseLine(String line, Map<String, Set<List<Integer> > > list, int idxStr)
                                    throws UnsupportedEncodingException, IOException {
        FastScanner sc = new FastScanner(line);
        int idxWord = 1;

        while (sc.hasNextWord()) {
            addToStats(sc.nextWord().toLowerCase(), list, idxStr, idxWord++);
        }
    }

    public static void main(String[] args) {
        Map<String, Set<List<Integer> > > statWords = new TreeMap<>();

        if (args.length != 2) {
            System.err.println("Usage: Word <input file> <output file>");
            return;
        }

        try {
            FastScanner sc = new FastScanner(new File(args[0]));

            int idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                parseLine(line, statWords, idx++);
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
                for (Map.Entry<String, Set<List<Integer> > > pair : statWords.entrySet()) {
                    writer.print(pair.getKey() + " " + pair.getValue().size());
                    for (List<Integer> pos : pair.getValue()) {
                        writer.print(" " + pos.get(0) + ":" + pos.get(1));
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