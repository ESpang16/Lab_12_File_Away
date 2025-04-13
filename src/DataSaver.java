import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;  // Import CREATE

public class DataSaver {
    public static void main(String[] args) {
        // ArrayList to store CSV records
        ArrayList<String> records = new ArrayList<>();

        // Loop to collect user data
        boolean continueInput = true;
        while (continueInput) {
            // Collecting user data using JOptionPane
            String firstName = JOptionPane.showInputDialog("Enter First Name:");
            String lastName = JOptionPane.showInputDialog("Enter Last Name:");
            String idNumber = String.format("%06d", Integer.parseInt(JOptionPane.showInputDialog("Enter ID Number (1 to 999999):")));
            String email = JOptionPane.showInputDialog("Enter Email:");

            // Ensure email is in a valid format (basic check)
            while (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                email = JOptionPane.showInputDialog("Invalid email format. Enter Email again:");
            }

            int yearOfBirth = Integer.parseInt(JOptionPane.showInputDialog("Enter Year of Birth (4 digits):"));
            while (yearOfBirth < 1900 || yearOfBirth > 2100) {
                yearOfBirth = Integer.parseInt(JOptionPane.showInputDialog("Year of Birth must be between 1900 and 2100. Enter again:"));
            }

            // Formatting the data into CSV record
            String record = String.format("%s, %s, %s, %s, %d", firstName, lastName, idNumber, email, yearOfBirth);
            records.add(record);

            // Asking the user if they want to continue entering more records
            int option = JOptionPane.showConfirmDialog(null, "Would you like to enter another record?", "Continue?", JOptionPane.YES_NO_OPTION);
            continueInput = (option == JOptionPane.YES_OPTION);
        }

        // Prompting for the filename to save the records
        String fileName = JOptionPane.showInputDialog("Enter the filename to save (including .csv extension):");

        // Ensure the filename ends with .csv
        if (!fileName.endsWith(".csv")) {
            fileName += ".csv";
        }

        // Get the path to the `src` directory
        String projectDirectory = System.getProperty("user.dir");
        String srcDirectoryPath = projectDirectory + File.separator + "src";

        // Create the file path within the `src` directory
        Path filePath = Paths.get(srcDirectoryPath, fileName);

        // Writing the records to the CSV file using Files.write()
        try {
            // Creating the file if it doesn't exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            // Writing the records to the file
            Files.write(filePath, records, CREATE, StandardOpenOption.APPEND);

            // Inform the user that the data was saved successfully
            JOptionPane.showMessageDialog(null, "Data saved successfully to " + filePath.toString());

        } catch (IOException e) {
            // Handling exceptions during file writing
            JOptionPane.showMessageDialog(null, "An error occurred while saving the data.");
            e.printStackTrace();
        }
    }
}