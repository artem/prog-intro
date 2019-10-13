import java.util.*;
import java.io.*;

public class ReverseTranspose {
    private static void fillColumn(String nums, List<List<Integer> > matrix) throws UnsupportedEncodingException, IOException {
        FastScanner sc = new FastScanner(nums);

        int idx = 0;
        while (sc.hasNextInt()) {
            int num = sc.nextInt();

            if (idx >= matrix.size()) {
                matrix.add(new ArrayList<Integer>());
            }
            matrix.get(idx).add(num);

            idx++;
        }
    }

    public static void main(String[] args) {
        try {
            FastScanner sc = new FastScanner(System.in);
            List<List<Integer> > matrix = new ArrayList<>();

            while (sc.hasNextLine()) {
                fillColumn(sc.nextLine(), matrix);
            }

            for (List<Integer> row : matrix) {
                for (int num : row) {
                    System.out.print(num + " ");
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