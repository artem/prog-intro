package markup;

import java.util.List;

public class Strong extends TextDecoration {
    protected Strong(List<IMarkdown> content) {
        super(content, "__");
    }
}
