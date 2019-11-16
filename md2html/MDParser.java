package md2html;

import java.util.HashMap;
import java.util.Map;

public class MDParser {
    private final StringBuilder result;
    private final StringBuilder buffer;
    private static final Map<String, String> decorators;
    private static final Map<String, String> paragraphs;

    private String paragraph;

    static {
        decorators = new HashMap<>();
        paragraphs = new HashMap<>();

        decorators.put("*", "em");
        decorators.put("_", "em");
        decorators.put("**", "strong");
        decorators.put("__", "strong");
        decorators.put("--", "s");
        decorators.put("`", "code");

        paragraphs.put("# ", "h1");
        paragraphs.put("## ", "h2");
        //TODO
    }

    public MDParser() {
        result = new StringBuilder();
        buffer = new StringBuilder();
    }

    private void openTag(String tag) {
        result.append("<").append(tag).append(">");
    }

    private void closeTag(String tag) {
        result.append("</").append(tag).append(">");
    }

    private void openParagraph(String line) {
        if (paragraph != null) {
            return;
        }

        //TODO intelligent auto heading
        for (Map.Entry<String, String> parDetect : paragraphs.entrySet()) {
            if (line.startsWith(parDetect.getKey())) {
                String tag = parDetect.getValue();
                openTag(tag);
                paragraph = tag;
                return;
            }
        }
    }

    private void closeParagraph() {
        if (paragraph != null) {
            closeTag(paragraph);
            paragraph = null;
        }
    }
    public void append(String line) {
        if (line.isEmpty()) {
            closeParagraph();
            return;
        }

        openParagraph(line);

    }
}
