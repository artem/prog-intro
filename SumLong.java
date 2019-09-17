import java.util.Arrays;

public class SumLong {
    public static void main(String[] args) {
        long ans = 0;

        for (int i = 0; i < args.length; i++) {
            String[] parse = args[i].split("\\p{javaWhitespace}+");
            //System.err.println(Arrays.toString(parse) + parse.length);
            for (int j = 0; j < parse.length; j++) {
                if (parse[j].isEmpty()) {
                    continue;
                }
                ans += Long.parseLong(parse[j]);
            }
        }
        System.out.println(ans);
    }
}
