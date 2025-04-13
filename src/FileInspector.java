import javax.swing.*;
import java.io.*;

public class FileInspector {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        File selectedFile;
        File currentDirectory = new File(System.getProperty("user.dir") + "/src");
        fileChooser.setCurrentDirectory(currentDirectory);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    wordCount += countWords(line);
                    charCount += line.length();
                    lineCount++;
                }
                System.out.println("\n--- Summary Report ---");
                System.out.println("File name: " + selectedFile.getName());
                System.out.println("Number of lines: " + lineCount);
                System.out.println("Number of words: " + wordCount);
                System.out.println("Number of characters: " + charCount);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected. Exiting...");
        }
    }
    private static int countWords(String line) {
        String[] words = line.split("\\s+");
        return words.length;
    }
}