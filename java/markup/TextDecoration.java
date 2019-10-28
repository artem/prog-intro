package markup;

import java.util.List;

public abstract class TextDecoration implements IRichText {
    private final List<IRichText> content;

    protected TextDecoration(List<IRichText> content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        String mark = getMdMark();

        result.append(mark);

        for (IRichText node : content) {
            node.toMarkdown(result);
        }

        result.append(mark);
    }

    @Override
    public void toHtml(StringBuilder result) {
        String tag = getHtmlTag();
        boolean empty = tag.isEmpty();

        if (!empty) {
            result.append("<").append(tag).append(">");
        }

        for (IRichText node : content) {
            node.toHtml(result);
        }

        if (!empty) {
            result.append("</").append(tag).append(">");
        }
    }

    protected abstract String getMdMark();
    protected abstract String getHtmlTag();
}
