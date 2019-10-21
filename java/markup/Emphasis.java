package markup;

import java.util.List;

public class Emphasis extends TextDecoration {
    protected Emphasis(List<IRichText> content) {
        super(content, "*", "em");
    }
}
