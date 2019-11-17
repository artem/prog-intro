package md2html;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class MDParser {
    private static final Map<String, String> decorators;
    private static final Map<String, String> paragraphs;
    private static final Map<Character, String> htmlSymbols;

    static {
        decorators = Map.of("*", "em",
                "_", "em",
                "**", "strong",
                "__", "strong",
                "--", "s",
                "`", "code",
                "++", "u");

        //TODO intelligent auto heading
        paragraphs = Map.of("# ", "h1",
                "## ", "h2",
                "### ", "h3",
                "#### ", "h4",
                "##### ", "h5",
                "###### ", "h6");

        htmlSymbols = Map.of('<', "&lt;",
                '>', "&gt;",
                '&', "&amp;");
    }

    private final StringBuilder result;
    private final Deque<String> tagStack;
    private int offset;
    private String paragraph;

    public MDParser() {
        result = new StringBuilder();
        tagStack = new ArrayDeque<>();
    }

    private static String symbolToHtml(char c) {
        return htmlSymbols.getOrDefault(c, String.valueOf(c));
    }

    private StringBuilder openTag(String tag, StringBuilder res) {
        return res.append("<").append(tag).append(">");
    }

    private StringBuilder closeTag(String tag, StringBuilder res) {
        return res.append("</").append(tag).append(">");
    }

    private void openParagraph(String line) {
        if (paragraph != null) {
            return;
        }

        paragraph = "p";

        //TODO intelligent auto heading
        for (Map.Entry<String, String> parDetect : paragraphs.entrySet()) {
            if (line.startsWith(parDetect.getKey())) {
                String tag = parDetect.getValue();
                paragraph = tag;
                offset += parDetect.getKey().length();
                break;
            }
        }

        openTag(paragraph, result);
    }

    private void closeParagraph() {
        if (paragraph != null) {
            closeTag(paragraph, result);
            paragraph = null;
        }
    }

    public void append(String paragraph) {
        offset = 0;
        if (paragraph.isEmpty()) {
            return;
        }

        openParagraph(paragraph);
        result.append(parseText(paragraph));
        closeParagraph();
        result.append('\n');
    }

    private StringBuilder parseText(String paragraph) {
        StringBuilder buffer = new StringBuilder();

        outer:
        while (offset < paragraph.length()) {
            char cur = paragraph.charAt(offset);

            if (cur == '\\') {
                if (offset + 1 < paragraph.length()) {
                    append(paragraph.charAt(++offset), buffer);
                    offset++;
                    continue;
                }
            }

            String tag;
            for (int j = Math.min(paragraph.length() - offset, 2); j > 0; j--) {
                String mdFlag = paragraph.substring(offset, offset + j);
                if ((tag = decorators.get(mdFlag)) != null) {
                    offset += j;
                    if (tagStack.size() > 0 && tagStack.peek().equals(mdFlag)) {
                        tagStack.pop();
                        return closeTag(tag, openTag(tag, new StringBuilder()).append(buffer));
                    } else {
                        tagStack.push(mdFlag);
                        buffer.append(parseText(paragraph));
                    }
                    continue outer;
                }
            }

            append(paragraph.charAt(offset++), buffer);
        }
        if (tagStack.size() > 0) {
            buffer.insert(0, tagStack.pop());
        }
        return buffer;
    }

    public void append(char c, StringBuilder res) {
        res.append(symbolToHtml(c));
    }

    @Override
    public String toString() {
        String res = result.toString();
        if (paragraph != null) {
            res += "</" + paragraph + ">";
        }
        return res;
    }
}
