package markup;

import java.util.List;

public class Strong extends TextDecoration {
    protected Strong(List<IRichText> content) {
        super(content);
    }

    @Override
    protected String getMdMark() {
        return "__";
    }

    @Override
    protected String getHtmlTag() {
        return "strong";
    }

}
