package markup;

import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HtmlTest extends MarkdownTest {
    private static final Map<String, String> Html = Map.of(
            "*<", "<em>",
            "*>", "</em>",
            "__<", "<strong>",
            "__>", "</strong>",
            "~<", "<s>",
            "~>", "</s>"
    );

    public static void main(String[] args) {
        new HtmlTest().run();
    }

    @Override
    protected void test(final Paragraph paragraph, final String expected) {
        super.test(paragraph, expected);
        test(paragraph::toHtml, expected, Html);
    }
}
