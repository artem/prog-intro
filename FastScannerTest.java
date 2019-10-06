import java.io.*;

public class FastScannerTest {
	public static void main(String arg[]) {
		try {
			FastScanner sc = new FastScanner(" to be or not\nto be");
			while (sc.hasNextWord()) {
				System.out.println(sc.nextWord());
			}
		} catch (UnsupportedEncodingException e) {
			System.err.println("Bad Encoding: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException during read: " + e.getMessage());
		}
	}
}