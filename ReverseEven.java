import java.util.*;

public class ReverseEven {
    public static int[] stringArrToIntArr(String[] s) {
        int[] result = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i] = Integer.parseInt(s[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] numbers = new int[1_000_000][];
        int size = 0;

        while (sc.hasNextLine()) {
            int[] numsInLine;
            String curLine = sc.nextLine();
            if (!curLine.isEmpty()) {
                numsInLine = stringArrToIntArr(curLine.split(" ")); // работает быстрее, чем Scanner и Scanner.nextInt() !
            } else {
                numsInLine = new int[0];
            }

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