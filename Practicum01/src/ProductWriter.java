import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {
        String ID = " ";
        String Name = " ";
        String Description = " ";
        String CSVProduct = ",";
        double cost = 0;

        Scanner in = new Scanner(System.in);
        ArrayList<String> csvProduct = new ArrayList<>();


        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");


        boolean done = false;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter ID");
            Name = SafeInput.getNonZeroLenString(in, "Enter Name");
            Description = SafeInput.getNonZeroLenString(in, "Enter Description");
            cost = SafeInput.getDouble(in, "Enter Cost");


            CSVProduct = ID + "," + Name + "," + Description + "," + cost;

            csvProduct.add(CSVProduct);
            done = SafeInput.getYNConfirm(in, "Are you done?");
        } while (!done);

        for (String s : csvProduct) {
            System.out.println(s);
        }

        try {

            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for (String rec : csvProduct) {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
