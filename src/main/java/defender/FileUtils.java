package defender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {

    private static final String FILEPATH = "./src/main/resources/scores.txt";

    public List<Long> readScore() {
        List<Long> records = new ArrayList<>();
        try {
            File file = new File(FILEPATH);
            System.out.println(file.getAbsolutePath());
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                records.add(Long.valueOf(data));
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }

    public  void writeScore(List<Long> records) {
        try {
            File file = new File(FILEPATH);
            FileWriter myWriter = new FileWriter(file);
            for (Long record : records) {
                myWriter.write(record + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
