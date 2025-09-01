import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class PersonGenerator {
    public static void main(String[] args) {
        String ID = " ";
        String firstName = " ";
        String lastName = " ";
        String title = " ";
        String CSVPersonRec = " ";
        int yearOfBirth = 0;

        Scanner in = new Scanner(System.in);
        ArrayList<String> csvPerson = new ArrayList<>();

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");


        boolean done = false;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter ID");
            firstName = SafeInput.getNonZeroLenString(in, "Enter first name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter last name");
            title = SafeInput.getNonZeroLenString(in, "Enter title");
            yearOfBirth = SafeInput.getRangedInt(in, "Enter your YOB", 1000, 9999);

            CSVPersonRec = ID + "," + firstName + "," + lastName + "," + title + ",    " + yearOfBirth;

            csvPerson.add(CSVPersonRec);
            done = SafeInput.getYNConfirm(in, "Are you done?");
        } while (!done);

        for (String s : csvPerson) {
            System.out.println(s);
        }

        try {

            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for (String rec : csvPerson) {
                writer.write(rec, 0, rec.length());
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}