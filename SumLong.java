public class SumLong {
    static long ans;

    private static boolean isDigit(char digit) {
        return digit >= '0' && digit <= '9';
    }

    private static void handleNumber(long add, boolean negative) {
        if (negative) {
            add = -add;
        }
        ans += add;
    }

    public static void main(String[] args) {
        ans = 0;
        for (int i = 0; i < args.length; i++) {
            long cur = 0;
            boolean negative = false;

            for (int j = 0; j < args[i].length(); j++) {
                char symbol = args[i].charAt(j);

                if (isDigit(symbol)) {
                    cur = cur * 10 + Character.getNumericValue(symbol);
                } else {
                    handleNumber(cur, negative);
                    cur = 0;
                    negative = symbol == '-';
                }
            }
            //System.out.println(cur);
            handleNumber(cur, negative);
        }
        System.out.println(ans);
    }
}