package markup;

import java.util.List;

public class Emphasis extends TextDecoration {
    protected Emphasis(List<IRichText> content) {
        super(content);
    }

    @Override
    protected String getMdMark() {
        return "*";
    }

    @Override
    protected String getHtmlTag() {
        return "em";
    }
}
