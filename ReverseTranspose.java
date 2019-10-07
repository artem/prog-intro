import java.util.*;
import java.io.*;

public class ReverseTranspose {
    public static int[] stringToIntArr(String s, int[] buffer) throws UnsupportedEncodingException, IOException {
        FastScanner sc = new FastScanner(s);
        int size = 0;

        while (sc.hasNextInt()) {
            buffer[size++] = sc.nextInt();
        }
        return Arrays.copyOf(buffer, size);
    }

    public static void main(String[] args) {
        try {
            FastScanner sc = new FastScanner(System.in);
            int[][] numbers = new int[1_000_000][];
            int[] buffer = new int[1_000_000];
            int size = 0;
            int maxLineLength = 0;

            while (sc.hasNextLine()) {
                numbers[size++] = stringToIntArr(sc.nextLine(), buffer);
                maxLineLength = Math.max(numbers[size - 1].length, maxLineLength);
            }

            for (int j = 0; j < maxLineLength; j++) {
                for (int i = 0; i < size; i++) {
                    if (j < numbers[i].length) {
                        System.out.print(numbers[i][j] + " ");
                    }
                }
                System.out.println();
            }
        } catch (UnsupportedEncodingException e) {
            System.err.println("Console input has unsupported encoding: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error during reading console input: " + e.getMessage());
        }
    }
}