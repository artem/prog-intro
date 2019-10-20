package markup;

import java.util.List;

public abstract class TextDecoration implements IMarkdown {
    private final String mark;
    private final List<IMarkdown> content;

    protected TextDecoration(List<IMarkdown> content, String mark) {
        this.mark = mark;
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        result.append(mark);
        handleNodes(result);
        result.append(mark);
    }

    protected void handleNodes(StringBuilder result) {
        for (IMarkdown node : content) {
            node.toMarkdown(result);
        }
    }
}
