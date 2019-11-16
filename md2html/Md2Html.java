package md2html;

import java.io.*;

public class Md2Html {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), "utf8")
        )) {

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O error during read: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
