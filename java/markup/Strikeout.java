package markup;

import java.util.List;

public class Strikeout extends TextDecoration {
    protected Strikeout(List<IRichText> content) {
        super(content);
    }

    @Override
    protected String getMdMark() {
        return "~";
    }

    @Override
    protected String getHtmlTag() {
        return "s";
    }
}
