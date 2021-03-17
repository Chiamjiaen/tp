package seedu.nurseschedules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class NurseScheduleStorage {

    /** Creates new file */
    private static void createFile() {
        try {
            File file = new File("duke.txt");
            if (file.createNewFile()) {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile(List<NurseSchedule> nurseSchedules) {
        try {
            FileInputStream file = new FileInputStream("duke.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] details = sc.nextLine().split("\\|", 0);
                nurseSchedules.add(new NurseSchedule(details[0], details[1], details[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(List<NurseSchedule> nurseSchedules) {
        try {
            File file = new File("duke.txt");
            FileWriter writer = new FileWriter(file);
            for (NurseSchedule n : nurseSchedules) {
                writer.write(n.toSave());
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(List<NurseSchedule> nurseSchedules) {
        createFile();
        readFile(nurseSchedules);
    }

}
