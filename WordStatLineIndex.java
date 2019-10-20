import java.io.*;
import java.util.*;

public class WordStatLineIndex {
    private static void parseLine(String line, Map<String, List<TextPos>> list, int idxStr) {
        FastScanner sc = new FastScanner(line);
        int idxWord = 1;

        try {
            while (sc.hasNextWord()) {
                String word = sc.nextWord().toLowerCase();
                List<TextPos> chain = list.getOrDefault(word, new ArrayList<TextPos>());

                chain.add(new TextPos(idxStr, idxWord++));
                list.put(word, chain);
            }
        } catch (IOException e) {
            System.err.println("I/O error during reading console input: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NavigableMap<String, List<TextPos>> statWords = new TreeMap<>();

        if (args.length != 2) {
            System.err.println("Usage: Word <input file> <output file>");
            return;
        }

        try (FastScanner sc = new FastScanner(new File(args[0]))) {
            int idx = 1;

            while (sc.hasNextLine()) {
                parseLine(sc.nextLine(), statWords, idx++);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported input encoding: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException during read: " + e.getMessage());
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))) {
            for (Map.Entry<String, List<TextPos>> pair : statWords.entrySet()) {
                writer.write(pair.getKey() + " " + pair.getValue().size());

                for (TextPos pos : pair.getValue()) {
                    writer.write(" " + pos.getLineNumber() + ":" + pos.getWordNumber());
                }
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't open output file for writing: " + e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported output encoding: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IOException during write: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
