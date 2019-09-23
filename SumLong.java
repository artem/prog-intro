public class SumLong {
    public static void main(String[] args) {
        long ans = 0;

        for (String arg : args) {
            for (int j = 0; j < arg.length(); j++) {
                int idxStart = j;
                while (j < arg.length() && !Character.isWhitespace(arg.charAt(j))) {
                    j++;
                }

                if (idxStart < j) {
                    ans += Long.parseLong(arg.substring(idxStart, j));
                }
            }
        }
        System.out.println(ans);
    }
}
