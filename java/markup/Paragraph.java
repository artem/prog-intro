package markup;

import java.util.List;

public class Paragraph extends TextDecoration {
    protected Paragraph(List<IRichText> content) {
        super(content);
    }

    @Override
    protected String getMdMark() {
        return "";
    }

    @Override
    protected String getHtmlTag() {
        return "";
    }

}
