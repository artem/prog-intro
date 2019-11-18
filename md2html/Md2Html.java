package md2html;

import java.io.*;

public class Md2Html {
    public static void main(String[] args) {
        MDParser pr = new MDParser();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "utf8"))) {
            StringBuilder paragraph = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    pr.append(paragraph.toString());
                    paragraph.setLength(0);
                } else {
                    if (paragraph.length() > 0) {
                        paragraph.append('\n');
                    }
                    paragraph.append(line);
                }
            }
            pr.append(paragraph.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
            e.printStackTrace();
            return;
        } catch (IOException e) {
            System.out.println("I/O error during read: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))) {
            writer.write(pr.toString());
        } catch (FileNotFoundException e) {
            System.err.println("Can't open output file for writing: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IOException during write: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
