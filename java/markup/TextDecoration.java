package markup;

import java.util.List;

public abstract class TextDecoration implements IRichText {
    private final String mdSep;
    private final String htmlSep;
    private final List<IRichText> content;

    protected TextDecoration(List<IRichText> content, String mdSep, String htmlSep) {
        this.mdSep = mdSep;
        this.htmlSep = htmlSep;
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        result.append(mdSep);
        for (IRichText node : content) {
            node.toMarkdown(result);
        }
        result.append(mdSep);
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
        if (!htmlSep.isEmpty()) {
            return "<" + htmlSep + ">"; 
        }

        return "";
    }

    private String htmlClosingTag() {
        if (!htmlSep.isEmpty()) {
            return "</" + htmlSep + ">"; 
        }

        return "";
    }

    protected void handleMdNodes(StringBuilder result) {
        
    }
}
