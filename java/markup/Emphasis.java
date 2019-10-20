package markup;

import java.util.List;

public class Emphasis extends TextDecoration {
    protected Emphasis(List<IMarkdown> content) {
        super(content);
    }

    @Override
    protected String getSeparator() {
        return "*";
    }
}
