import java.util.*;

public class SumLong {
    public static void main(String[] args) {
        long ans = 0;

        for (String arg : args) {
            String[] parse;
            arg = arg.strip();

            if (arg.isBlank())
                continue;

            parse = arg.split("\\p{javaWhitespace}+");

            for (int j = 0; j < parse.length; j++) {
                ans += Long.parseLong(parse[j]);
            }
        }
        System.out.println(ans);
    }
}
