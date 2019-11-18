package md2html;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class MDParser {
    private static final Map<String, String> decorators = Map.of("*", "em",
                "_", "em",
                "**", "strong",
                "__", "strong",
                "--", "s",
                "`", "code",
                "++", "u");
    private static final Map<Character, String> htmlSymbols = Map.of('<', "&lt;",
                '>', "&gt;",
                '&', "&amp;");
    private static final int MAX_MARKER_LENGTH = 2;

    private final StringBuilder result;
    private final Deque<String> tagStack;
    private int offset;
    private int headerLevel;

    public MDParser() {
        result = new StringBuilder();
        tagStack = new ArrayDeque<>();
        headerLevel = -1;
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

    private String getHeaderTag(int headerLevel) {
        return headerLevel > 0 ? "h" + headerLevel : "p";
    }

    private void openParagraph(String line) {
        if (headerLevel != -1) {
            return;
        }
        headerLevel = 0;

        for (int i = 0; i < line.length(); i++) {
            char tmp = line.charAt(i);

            if (tmp == '#' && i != line.length() - 1) {
                headerLevel++;
                offset++;
            } else if (i > 0 && tmp == ' ') {
                offset++;
                break;
            } else {
                headerLevel = 0;
                offset = 0;
                break;
            }

        }

        openTag(getHeaderTag(headerLevel), result);
    }

    private void closeParagraph() {
        if (headerLevel != -1) {
            closeTag(getHeaderTag(headerLevel), result);
            headerLevel = -1;
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
                    appendChar(paragraph.charAt(offset + 1), buffer);
                    offset += 2;
                    continue;
                }
            }


            for (int j = Math.min(paragraph.length() - offset, MAX_MARKER_LENGTH); j > 0; j--) {
                String mdFlag = paragraph.substring(offset, offset + j);

                String tag = decorators.get(mdFlag);
                if (tag != null) {
                    offset += j;
                    if (tagStack.size() > 0 && tagStack.peek().equals(mdFlag)) {
                        tagStack.pop();
                        return closeTag(tag, openTag(tag, new StringBuilder()).append(buffer)); // returns <tag> ... buffer ...</tag>
                    } else {
                        tagStack.push(mdFlag);
                        buffer.append(parseText(paragraph));
                    }

                    continue outer;
                }
            }

            appendChar(paragraph.charAt(offset++), buffer);
        }

        if (tagStack.size() > 0) {
            buffer.insert(0, tagStack.pop());
        }

        return buffer;
    }

    private void appendChar(char c, StringBuilder res) {
        res.append(symbolToHtml(c));
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
