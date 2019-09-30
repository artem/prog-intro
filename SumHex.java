public class SumHex {
    public static void main(String[] args) {
        int ans = 0;

        for (String arg : args) {
            for (int j = 0; j < arg.length(); j++) {
                int idxStart = j;
                while (j < arg.length() && !Character.isWhitespace(arg.charAt(j))) {
                    j++;
                }

                if (idxStart < j) {
                    String number = arg.substring(idxStart, j);

                    if (number.startsWith("0x") || number.startsWith("0X")) {
                        ans += Long.parseLong(number.substring(2), 16);
                    } else {
                        ans += Long.parseLong(number);
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
