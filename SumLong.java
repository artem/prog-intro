import java.util.*;

public class SumLong {
    public static void main(String[] args) {
        long ans = 0;

        for (int i = 0; i < args.length; i++) {
            Scanner sc = new Scanner(args[i]);
            while (sc.hasNext()) {
                ans += Long.parseLong(sc.next()); // Можно было sc.nextLong(), но работает медленнее
            }
        }
        System.out.println(ans);
    }
}
