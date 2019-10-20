public class TextPos {
    private final int line;
    private final int word;
    public TextPos(int line, int word) {
        this.line = line;
        this.word = word;
    }

    public int getLineNumber() {
        return line;
    }

    public int getWordNumber() {
        return word;
    }
} 
