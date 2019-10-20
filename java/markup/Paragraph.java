package markup;

import java.util.List;

public class Paragraph extends TextDecoration {
    protected Paragraph(List<IMarkdown> content) {
        super(content);
    }

    @Override
    protected String getSeparator() {
        return "";
    }
}
