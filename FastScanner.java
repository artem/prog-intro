import java.io.*;
import java.util.*;

public class FastScanner {
    private final BufferedReader reader;

    private String cachedNext;
    private String cachedNextLine;
    private LinkedList<String> cachedWord = new LinkedList<>();
    private Integer cachedInt;
    private Long cachedLong;

    public FastScanner(File file) throws FileNotFoundException,
                                            UnsupportedEncodingException,
                                            IOException {
        this(new FileInputStream(file));
    }

    public FastScanner(String string) throws UnsupportedEncodingException, IOException {
        this(new ByteArrayInputStream(string.getBytes()));
    }

    public FastScanner(InputStream stream) throws UnsupportedEncodingException, IOException {
        this.reader = new BufferedReader(new InputStreamReader(stream, "utf8"));
    }

    public boolean hasNextLine() throws IOException {
        if (cachedNextLine != null) {
            return true;
        }

        cachedNextLine = reader.readLine();
        return cachedNextLine != null;
    }

    public String nextLine() throws IOException {
        if (!hasNextLine()) {
            throw new NoSuchElementException("No new lines in stream!");
        }

        String ret = cachedNextLine;
        cachedNextLine = null;
        return ret;
    }

    private static boolean isWordChar(char c) {
        boolean isLetter = Character.isLetter(c);
        boolean isDash = Character.getType(c) == Character.DASH_PUNCTUATION;
        boolean isApostrophe = c == '\'';

        return isLetter || isDash || isApostrophe;
    }

    public boolean hasNextWord() {
        String buffer;
        boolean empty = true;

        if (cachedWord.size() > 0) {
            return true;
        }

        while (hasNext()) {
            buffer = next();

            for (int i = 0; i < buffer.length(); i++) {
                int idxStart = i;

                while (i < buffer.length() && isWordChar(buffer.charAt(i))) {
                    i++;
                }

                if (idxStart < i) {
                    cachedWord.add(buffer.substring(idxStart, i));
                    empty = false;
                }
            }

            if (!empty) {
                return true;
            }
        }

        return false;
    }

    public String nextWord() {
        if (!hasNextWord()) {
            throw new NoSuchElementException("No words in stream!");
        }

        return cachedWord.remove();
    }

    public boolean hasNextInt() {
        String buffer;

        if (cachedInt != null) {
            return true;
        }

        if (!hasNext()) {
            return false;
        }

        buffer = next();

        for (int i = 0; i < buffer.length(); i++) {
            char c = buffer.charAt(i);
            if (!Character.isDigit(c) && c != '-' && c != '+') {
                return false;
            }
        }

        try {
            cachedInt = Integer.parseInt(buffer);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public Integer nextInt() {
        if (!hasNextInt()) {
            throw new NoSuchElementException("No integers in stream!");
        }

        Integer ret = cachedInt;
        cachedInt = null;
        return ret;
    }

    public boolean hasNext() {
        int cur;
        StringBuilder str;

        if (cachedNext != null) {
            return true;
        }

        try {
            while (true) {
                cur = reader.read();

                if (cur == -1) {
                    return false;
                } else if (!Character.isWhitespace(cur)) {
                    break;
                }
            }

            str = new StringBuilder();
            str.append((char)cur);

            while (true) {
                cur = reader.read();

                if (cur == -1 || Character.isWhitespace(cur)) {
                    break;
                }
                str.append((char)cur);
            }

            cachedNext = str.toString();
            return true;
        } catch (IOException e) {
            System.err.println("IOException during read: " + e.getMessage());
            return false;
        }
    }

    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No tokens in stream!");
        }

        String ret = cachedNext;
        cachedNext = null;
        return ret;
    }
}
