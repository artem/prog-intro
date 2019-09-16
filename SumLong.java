public class SumLong {
    static long ans;

    // private static boolean isDigit(char digit) {
    //     return digit >= '0' && digit <= '9';
    // }

    // private static void handleNumber(long add, boolean negative) {
    //     if (negative) {
    //         add = -add;
    //     }
    //     ans += add;
    // }

    public static void main(String[] args) {
        ans = 0;
        for (int i = 0; i < args.length; i++) {
            String[] parse = args[i].split("\\p{javaWhitespace}");
            for (int j = 0; j < parse.length; j++) {
                try {
                    ans += Long.parseLong(parse[j]);
                } catch (Exception ignore) { }
            }
        }
        System.out.println(ans);
    }
}