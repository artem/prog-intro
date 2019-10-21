package markup;

import java.util.List;

public class Strikeout extends TextDecoration {
    protected Strikeout(List<IRichText> content) {
        super(content, "~", "s");
    }
}
