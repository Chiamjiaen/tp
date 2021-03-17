package seedu.duke.storage;

import seedu.duke.exceptions.DukeException;
import seedu.patient.Patient;
import seedu.patient.PatientList;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PatientStorage {

    static File saveFile;
    static ArrayList<Patient> patients = new ArrayList<>();
    static String filePath;

    /**
     * Instantiates a storage handler by taking in the filepath
     *
     * @param filePath the filepath where the file will be created
     */
    public PatientStorage(String filePath) {
        saveFile = new File(filePath);
        PatientStorage.filePath = filePath;
    }

    /**
     * Checks if the file exists and initializes one if there is not.
     */
    public void fileInit() {
        try {
            //makes file directory if it doesnt exist in the system.
            if (!(saveFile.exists())) {
                saveFile.getParentFile().mkdirs();
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("OOPS! I can't create the directory or file!");
        }
    }

    /**
     * Loads the saved list of patients from save location
     *
     * @return Populated patients arraylist
     * @throws DukeException if there is an error in loading
     */
    public ArrayList<Patient> loadPatients() throws DukeException {
        fileInit();
        try {
            // initializing file scanner to scan the file
            Scanner fileScanner = new Scanner(saveFile);

            while (fileScanner.hasNext()) {
                String currentScan = fileScanner.nextLine();
                //splits the string into sections for storing in the ArrayList
                String[] taskSave = currentScan.trim().split(" \\| ");
                if (taskSave.length != 6) {
                    throw new DukeException("loadFile");
                }
                Patient tempPatient = new Patient(taskSave[0], taskSave[1], Integer.parseInt(taskSave[2]),
                        taskSave[3], taskSave[4], taskSave[5]);
                patients.add(tempPatient);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS! I can't read the save file!");
        } catch (DukeException e) {
            e.getError("loadFile");
        }
        return patients;
    }

    /**
     * Saves all tasks to text file
     *
     * @param saveInput current files to be stored
     */
    public void storePatients(PatientList saveInput) {
        fileInit();
        try {
            //creates a new file writer to write to text file
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < saveInput.getSize(); i++) {
                fileWriter.write(saveInput.toSaveFile(i) + "\n");
            }
            fileWriter.close();
        } catch (java.io.IOException e) {
            System.out.println("☹ OOPS!!! The file can't be saved :-(");
        }
    }
}
