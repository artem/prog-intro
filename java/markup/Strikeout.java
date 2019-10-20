package markup;

import java.util.List;

public class Strikeout extends TextDecoration {
    protected Strikeout(List<IMarkdown> content) {
        super(content, "~");
    }
}
