import java.io.*;
import java.util.*;

public class WordStatWords {
    final static int MAX_SIZE = 1_000_000;

    private static boolean isWordChar(char c) {
        boolean isLetter = Character.isLetter(c);
        boolean isDash = Character.getType(c) == Character.DASH_PUNCTUATION;
        boolean isApostrophe = c == '\'';
        return isLetter || isDash || isApostrophe;
    }

    private static int addToStats(String word, String[] list, int[] occurences, int size) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(word)) {
                occurences[i]++;
                return size;
            }
        }

        if (size < list.length) {
            list[size] = word;
            occurences[size]++;
            size++;
        } else {
            System.err.println("Array is full :(");
        }

        return size;
    }

    public static void main(String[] args) {
        String[] statWords = new String[MAX_SIZE];
        int[] statOccurencies = new int[MAX_SIZE];
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
                            statSize = addToStats(word, statWords, statOccurencies, statSize);
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

        for(int i = 0; i < statSize - 1; i++) {
            for (int j = i + 1; j < statSize; j++) {
                if (statWords[i].compareTo(statWords[j]) > 0) {
                    String tempString = statWords[i];
                    statWords[i] = statWords[j];
                    statWords[j] = tempString;

                    int tempInt = statOccurencies[i];
                    statOccurencies[i] = statOccurencies[j];
                    statOccurencies[j] = tempInt;
                }
            }
        }

        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(args[1]));
            try {
                for (int i = 0; i < statSize; i++) {
                    writer.println(statWords[i] + ' ' + statOccurencies[i]);
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}