import java.util.*;

public class ReverseEven {
    public static int[] stringToIntArr(String s, int[] buffer) {
        Scanner sc = new Scanner(s);
        int size = 0;

        while (sc.hasNextInt()) {
            buffer[size++] = sc.nextInt();
        }
        return Arrays.copyOf(buffer, size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] numbers = new int[1_000_000][];
        int[] buffer = new int[1_000_000];
        int size = 0;

        while (sc.hasNextLine()) {
            int[] numsInLine;
            String curLine = sc.nextLine();

            numsInLine = stringToIntArr(curLine, buffer);
            numbers[size++]= numsInLine;
        }

        for (int i = size - 1; i >= 0; i--) {
            for (int j = numbers[i].length - 1; j >= 0; j--) {
                int cur = numbers[i][j];

                if (cur % 2 == 0)
                    System.out.print(cur + " ");
            }
            System.out.println();
        }
    }
}