import java.util.*;
import java.io.*;

public class ReverseEven {
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

            while (sc.hasNextLine()) {
                numbers[size++] = stringToIntArr(sc.nextLine(), buffer);
            }

            for (int i = size - 1; i >= 0; i--) {
                for (int j = numbers[i].length - 1; j >= 0; j--) {
                    int cur = numbers[i][j];

                    if (cur % 2 == 0) {
                        System.out.print(cur + " ");
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