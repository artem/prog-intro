package markup;

import java.util.List;

public abstract class TextDecoration implements IRichText {
    private final List<IRichText> content;

    protected TextDecoration(List<IRichText> content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        result.append(getMdMark());
        for (IRichText node : content) {
            node.toMarkdown(result);
        }
        result.append(getMdMark());
    }

    @Override
    public void toHtml(StringBuilder result) {
        result.append(htmlOpeningTag());
        for (IRichText node : content) {
            node.toHtml(result);
        }
        result.append(htmlClosingTag());
    }

    private String htmlOpeningTag() {
        String tag = getHtmlTag();

        if (!tag.isEmpty()) {
            return "<" + tag + ">";
        }

        return "";
    }

    private String htmlClosingTag() {
        String tag = getHtmlTag();

        if (!tag.isEmpty()) {
            return "</" + tag + ">";
        }

        return "";
    }

    protected abstract String getMdMark();
    protected abstract String getHtmlTag();
}
