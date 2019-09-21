import java.util.Arrays;

public class SumHex {
    public static void main(String[] args) {
        int ans = 0;

        for (int i = 0; i < args.length; i++) {
            String[] parse = args[i].split("\\p{javaWhitespace}+");
            //System.err.println(Arrays.toString(parse) + parse.length);
            for (int j = 0; j < parse.length; j++) {
                String cur = parse[j];
                int base;
                if (cur.isEmpty()) {
                    continue;
                }
                if (cur.length() >= 3 && cur.substring(0, 2).toLowerCase().equals("0x")) {
                    ans += Long.parseLong(cur.substring(2), 16);
                } else {
                    ans += Long.parseLong(cur, 10);
                }
            }
        }
        System.out.println(ans);
    }
}
