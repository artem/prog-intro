import java.util.*;

public class Reverse {
    public static ArrayList<Integer> StringToIntArr(String s) {
        Scanner sc = new Scanner(s);
        ArrayList<Integer> result = new ArrayList<>();

        while (sc.hasNext()) {
            result.add(Integer.parseInt(sc.next()));
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> numbers = new ArrayList<>();

        while (sc.hasNextLine()) {
            ArrayList<Integer> numsInLine;
            String curLine = sc.nextLine();

            numsInLine = StringToIntArr(curLine);
            numbers.add(numsInLine);
        }

        for (int i = numbers.size() - 1; i >= 0; i--) {
            for (int j = numbers.get(i).size() - 1; j >= 0; j--) {
                System.out.print(numbers.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}