import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ProductReader {
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

                System.out.printf("%-10s %-15s %-15s %-10s%n",
                        "ID", "Name", "Description", "Cost");
                System.out.println("================================================================");
                while (fileScanner.hasNextLine()) {
                    line = fileScanner.nextLine();
                    String[] fields = line.split(",");

                    if (fields.length == 4) {
                        String ID = fields[0].trim();
                        String Name = fields[1].trim();
                        String Descrption = fields[2].trim();
                        String cost = fields[3].trim();
                        System.out.printf("%-10s %-15s %-15s %10s%n", ID, Name, Descrption, cost);


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
