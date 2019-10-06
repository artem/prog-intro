import java.io.*;

public class FastScanner {
    /*private final InputStream stream;
    private final InputStreamReader reader;
    private final BufferedReader bufferedReader;*/
    private final BufferedReader reader;
    private String cachedLine;
    private String cachedNext;
    private String cachedNextWord;
    private Integer cachedInt;
    private Long cachedLong;

    private int pos = 0;

    public FastScanner(File file)
                throws FileNotFoundException, UnsupportedEncodingException, IOException {
        this(new FileInputStream(file));
    }

    public FastScanner(String string) throws UnsupportedEncodingException, IOException {
        this(new ByteArrayInputStream(string.getBytes()));
    }

    public FastScanner(InputStream stream) throws UnsupportedEncodingException, IOException {
        this.reader = new BufferedReader(new InputStreamReader(stream, "utf8"));
        updateCachedLine();
    }

    private static boolean isWordChar(char c) {
        boolean isLetter = Character.isLetter(c);
        boolean isDash = Character.getType(c) == Character.DASH_PUNCTUATION;
        boolean isApostrophe = c == '\'';

        return isLetter || isDash || isApostrophe;
    }

    private void updateCachedLine() throws IOException {
        cachedLine = reader.readLine();
        pos = 0;
    }

    private void skipWhitespaces() throws IOException {
        while (cachedLine != null) {
            while (pos < cachedLine.length()) {
                if (!Character.isWhitespace(cachedLine.charAt(pos))) {
                    return;
                }
                pos++;
            }
            updateCachedLine();
        }
    }

    public boolean hasNextWord() {
        if (cachedNextWord != null) {
            return true;
        }

        try {
            skipWhitespaces();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (cachedLine == null) {
            return false;
        }

        int idxEnd = pos;
        int idxStart = pos;
        while (idxEnd < cachedLine.length() && isWordChar(cachedLine.charAt(idxEnd))) {
            idxEnd++;
        }

        if (idxStart < idxEnd) {
            cachedNextWord = cachedLine.substring(idxStart, idxEnd);//.toLowerCase();
            return true;
        }

        return false;
    }

    public String nextWord() {
        String ret = cachedNextWord;

        cachedNextWord = null;
        return ret;
    }

    /*public boolean hasNext() {
        int idx = pos;
        int curChar;

        while ((curChar = reader.read()) != -1) {
            if (!Character.isWhitespace((char)curChar)) {
                return true;
            }
            idx++;
        }
        return false;
    }

    public String next() {
        int start = pos;
        int idx = pos;

        while (idx < stream.length()) {
            if (!Character.isWhitespace(stream.charAt(idx))) {
                break;
            }
            idx++;
        }

        if (idx == stream.length()) {
            System.err.println("Oops!");
            return null;
        }

        pos = idx;
        return stream.substring(start, idx);
    }*/
}
