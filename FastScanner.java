import java.io.*;
import java.util.*;

public class FastScanner implements AutoCloseable {
    private final BufferedReader reader;

    private String cachedNext;
    private String cachedNextLine;
    private Queue<String> cachedWord;
    private Integer cachedInt;

    public FastScanner(File file) throws FileNotFoundException,
                                            UnsupportedEncodingException,
                                            IOException {
        this(new FileInputStream(file));
    }

    public FastScanner(String string) {
        this.reader = new BufferedReader(new StringReader(string));
    }

    public FastScanner(InputStream stream) throws UnsupportedEncodingException, IOException {
        this.reader = new BufferedReader(new InputStreamReader(stream, "utf8"));
    }

    public void close() throws IOException {
        this.reader.close();
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

    public boolean hasNextWord() throws IOException {
        boolean empty = true;

        if (cachedWord != null && cachedWord.size() > 0) {
            return true;
        }

        Queue<String> queue = new ArrayDeque<>();

        while (hasNext()) {
            String buffer = next();

            for (int i = 0; i < buffer.length(); i++) {
                int idxStart = i;

                while (i < buffer.length() && isWordChar(buffer.charAt(i))) {
                    i++;
                }

                if (idxStart < i) {
                    queue.add(buffer.substring(idxStart, i));
                    empty = false;
                }
            }

            if (!empty) {
                cachedWord = queue;
                return true;
            }
        }

        return false;
    }

    public String nextWord() throws IOException {
        if (!hasNextWord()) {
            throw new NoSuchElementException("No words in stream!");
        }

        return cachedWord.remove();
    }

    public boolean hasNextInt() throws IOException {
        String buffer;

        if (cachedInt != null) {
            return true;
        }

        if (!hasNext()) {
            return false;
        }

        buffer = next();

        /*for (int i = 0; i < buffer.length(); i++) {
            char c = buffer.charAt(i);
            if (!Character.isDigit(c) && c != '-' && c != '+') {
                return false;
            }
        }*/

        try {
            cachedInt = Integer.parseInt(buffer);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Integer nextInt() throws IOException {
        if (!hasNextInt()) {
            throw new NoSuchElementException("No integers in stream!");
        }

        Integer ret = cachedInt;
        cachedInt = null;
        return ret;
    }

    public boolean hasNext() throws IOException {
        int cur;
        if (cachedNext != null) {
            return true;
        }

        while (true) {
            cur = reader.read();

            if (cur == -1) {
                return false;
            } else if (!Character.isWhitespace(cur)) {
                break;
            }
        }

        StringBuilder str = new StringBuilder();

        do {
            str.append((char)cur);
            cur = reader.read();
        } while (cur != -1 && !Character.isWhitespace(cur));

        cachedNext = str.toString();
        return true;
    }

    public String next() throws IOException {
        if (!hasNext()) {
            throw new NoSuchElementException("No tokens in stream!");
        }

        String ret = cachedNext;
        cachedNext = null;
        cachedNextLine = null;
        cachedWord = null;
        cachedInt = null;
        return ret;
    }
}
