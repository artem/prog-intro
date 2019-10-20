package markup;

import java.util.List;

public abstract class TextDecoration implements IMarkdown {
    private List<IMarkdown> content;

    protected TextDecoration(List<IMarkdown> content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        result.append(getSeparator());
        handleNodes(result);
        result.append(getSeparator());
    }

    protected void handleNodes(StringBuilder result) {
        for (IMarkdown node : content) {
            node.toMarkdown(result);
        }
    }

    protected abstract String getSeparator();
}
