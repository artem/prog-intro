import java.io.*;
import java.util.*;

public class WordStatLineIndex {
    private static class IntPair {
        private int l;
        private int r;

        public IntPair(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public int getL() {
            return l;
        }

        public int getR() {
            return r;
        }
    }

    private static void parseLine(String line, Map<String, List<IntPair > > list, int idxStr) {
        FastScanner sc = new FastScanner(line);
        int idxWord = 1;

        while (sc.hasNextWord()) {
            String word = sc.nextWord().toLowerCase();
            List<IntPair > chain = list.getOrDefault(word, new LinkedList<>());
            IntPair wordPos = new IntPair(idxStr, idxWord++);

            chain.add(wordPos);
            list.put(word, chain);
        }
    }

    public static void main(String[] args) {
        NavigableMap<String, List<IntPair > > statWords = new TreeMap<>();

        if (args.length != 2) {
            System.err.println("Usage: Word <input file> <output file>");
            return;
        }

        try {
            FastScanner sc = new FastScanner(new File(args[0]));

            try {
                int idx = 1;

                while (sc.hasNextLine()) {
                    parseLine(sc.nextLine(), statWords, idx++);
                }
            } finally {
                sc.close();
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
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
            try {
                for (Map.Entry<String, List<IntPair > > pair : statWords.entrySet()) {
                    writer.write(pair.getKey() + " " + pair.getValue().size());

                    for (IntPair pos : pair.getValue()) {
                        writer.write(" " + pos.getL() + ":" + pos.getR());
                    }
                    writer.newLine();
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
