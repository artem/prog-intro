import java.io.*;

public class FastScanner {
    /*private final InputStream stream;
    private final InputStreamReader reader;
    private final BufferedReader bufferedReader;*/
    private final BufferedReader reader;
    private String cachedLine;

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

    private void updateCachedLine() throws IOException {
        cachedLine = reader.readLine();
        pos = 0;
    }

    private static boolean isWordChar(char c) {
        boolean isLetter = Character.isLetter(c);
        boolean isDash = Character.getType(c) == Character.DASH_PUNCTUATION;
        boolean isApostrophe = c == '\'';

        return isLetter || isDash || isApostrophe;
    }

    private void skipNonWord() throws IOException {
        while (cachedLine != null) {
            while (pos < cachedLine.length()) {
                if (isWordChar(cachedLine.charAt(pos))) {
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
            skipNonWord();
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
            pos = idxEnd;
            return true;
        }

        return false;
    }

    public String nextWord() {
        String ret = cachedNextWord;

        cachedNextWord = null;
        return ret;
    }
}
