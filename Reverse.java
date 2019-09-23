import java.util.*;

public class Reverse {
    public static int[] StringArrToIntArr(String[] s) {
        int[] result = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals("")) {
                return new int[0];
            }
            result[i] = Integer.parseInt(s[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> numbers = new ArrayList<>();

        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            int[] numsInLine = StringArrToIntArr(curLine.split(" "));
            //String[] numsInLine = curLine.split(" ");
            numbers.add(numsInLine);
        }

        for (int i = numbers.size() - 1; i >= 0; i--) {
            for (int j = numbers.get(i).length - 1; j >= 0; j--) {
                System.out.print(numbers.get(i)[j] + " ");
            }
            System.out.println();
        }
    }
}