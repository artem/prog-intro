import java.util.*;

public class SumHex {
    public static void main(String[] args) {
        int ans = 0;

        for (int i = 0; i < args.length; i++) {
            Scanner sc = new Scanner(args[i]);
            while (sc.hasNext()) {
                String token = sc.next();

                if (token.toLowerCase().startsWith("0x")) {
                    ans += Long.parseLong(token.substring(2), 16);
                } else {
                    ans += Long.parseLong(token, 10);
                }
            }
        }
        System.out.println(ans);
    }
}
