package md2html;

import java.io.*;

public class Md2Html {
    public static void main(String[] args) {
        MDParser pr = new MDParser();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), "utf8")
        )) {
            String line;
            while ((line = reader.readLine()) != null) {
                pr.append(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O error during read: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            PrintWriter writer = new PrintWriter(args[1]);
            String out = pr.toString();
            writer.write(out);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
