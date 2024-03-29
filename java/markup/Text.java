package markup;

public class Text implements IRichText {
    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        result.append(text);
    }

    @Override
    public void toHtml(StringBuilder result) {
        result.append(text);
    }
}
