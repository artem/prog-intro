import java.io.*;
import java.util.*;

public class WordStatWords {
    private static void addToStats(String word, Map<String, Integer> list) {
        list.put(word, list.getOrDefault(word, 0) + 1);
    }

    public static void main(String[] args) {
        NavigableMap<String, Integer> statWords = new TreeMap<>();

        if (args.length != 2) {
            System.err.println("Usage: Word <input file> <output file>");
            return;
        }

        try (FastScanner sc = new FastScanner(new File(args[0]))) {
            while (sc.hasNextWord()) {
                addToStats(sc.nextWord().toLowerCase(), statWords);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported input encoding: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IOException during read: " + e.getMessage());
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))) {
            for (Map.Entry<String, Integer> pair : statWords.entrySet()) {
                writer.write(pair.getKey() + " " + pair.getValue());
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
