import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver {
    public static void main(String[] args) {
        ArrayList<String> records = new ArrayList<>();
        boolean continueInput = true;
        while (continueInput) {
            String firstName = JOptionPane.showInputDialog("Enter First Name:");
            String lastName = JOptionPane.showInputDialog("Enter Last Name:");
            String idNumber = String.format("%06d", Integer.parseInt(JOptionPane.showInputDialog("Enter ID Number (1 to 999999):")));
            String email = JOptionPane.showInputDialog("Enter Email:");
            while (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                email = JOptionPane.showInputDialog("Invalid email format. Enter Email again:");
            }

            int yearOfBirth = Integer.parseInt(JOptionPane.showInputDialog("Enter Year of Birth (4 digits):"));
            while (yearOfBirth < 1900 || yearOfBirth > 2100) {
                yearOfBirth = Integer.parseInt(JOptionPane.showInputDialog("Year of Birth must be between 1900 and 2100. Enter again:"));
            }
            String record = String.format("%s, %s, %s, %s, %d", firstName, lastName, idNumber, email, yearOfBirth);
            records.add(record);
            int option = JOptionPane.showConfirmDialog(null, "Would you like to enter another record?", "Continue?", JOptionPane.YES_NO_OPTION);
            continueInput = (option == JOptionPane.YES_OPTION);
        }
        String fileName = JOptionPane.showInputDialog("Enter the filename to save (including .csv extension):");
        if (!fileName.endsWith(".csv")) {
            fileName += ".csv";
        }
        String projectDirectory = System.getProperty("user.dir");
        String srcDirectoryPath = projectDirectory + File.separator + "src";
        Path filePath = Paths.get(srcDirectoryPath, fileName);
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            Files.write(filePath, records, CREATE, StandardOpenOption.APPEND);
            JOptionPane.showMessageDialog(null, "Data saved successfully to " + filePath.toString());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while saving the data.");
            e.printStackTrace();
        }
    }
}