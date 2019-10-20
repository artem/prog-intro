package markup;

public class Text implements IMarkdown {
    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        result.append(text);
    }
}
