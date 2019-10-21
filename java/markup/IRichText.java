package markup;

public interface IRichText {
    public void toMarkdown(StringBuilder result);
    public void toHtml(StringBuilder result);
}
