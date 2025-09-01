import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser personChooser = new JFileChooser();
        Scanner consoleScanner = new Scanner(System.in);
        Scanner fileScanner;
        File selectedFile;
        File workingDirectory = new File(System.getProperty("user.dir"));
        personChooser.setCurrentDirectory(workingDirectory);
        String line;

        try {
            if (personChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = personChooser.getSelectedFile();
                Path file = selectedFile.toPath();

                fileScanner = new Scanner(file);

                System.out.printf("%-10s %-15s %-15s %-10s %-5s%n",
                        "ID", "Firstname", "Lastname", "Title", "YOB");
                System.out.println("================================================================");
                while (fileScanner.hasNextLine()) {
                    line = fileScanner.nextLine();
                    String[] fields = line.split(",");

                    if (fields.length == 5) {
                        String ID = fields[0].trim();
                        String FirstName = fields[1].trim();
                        String LastName = fields[2].trim();
                        String Title = fields[3].trim();
                        String yearofBirth = fields[4].trim();
                        System.out.printf("%-10s %-15s %-15s %-10s %5s%n", ID, FirstName, LastName, Title, yearofBirth);


                    } else {
                        System.out.println("Invalid record: " + line);
                    }
                }

                fileScanner.close();
                System.out.println("\nData file read successfully!");

            } else {
                System.out.println("No file selected. Exiting.");
                System.exit(0);
            }

        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }
    }
}


