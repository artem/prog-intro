package markup;

import java.util.List;

public class Strong extends TextDecoration {
    protected Strong(List<IRichText> content) {
        super(content, "__", "strong");
    }
}
