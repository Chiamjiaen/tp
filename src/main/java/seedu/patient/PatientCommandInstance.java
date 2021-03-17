package seedu.patient;

import seedu.duke.exceptions.DukeException;
import seedu.duke.menuparser.MenuParser;
import seedu.duke.storage.PatientStorage;
import seedu.duke.ui.UI;

public class PatientCommandInstance {

    private UI ui;
    private PatientList patients;
    private PatientStorage patientStorage;
    static final String PATIENT_FILE_PATH = "data/PatientList.txt";

    public PatientCommandInstance(String filepath) {
        ui = new UI();
        patientStorage = new PatientStorage(PATIENT_FILE_PATH);
        try {
            patients = new PatientList(patientStorage.loadPatients());
        } catch (DukeException e) {
            ui.showLoadingError();
            //creates new task list if failure to load from folder.
            patients = new PatientList();
        }
    }

    public void patientInstance() {
        ui.patientCommandWelcome();
        boolean isReturnToStartMenu = false;
        while (!isReturnToStartMenu) {
            try {
                String fullCommand = UI.scanInput();
                ui.showLine(); // show the divider line ("_______")
                isReturnToStartMenu = MenuParser.patientParse(fullCommand);
                if (isReturnToStartMenu) {
                    ui.returningToStartMenuMessage();
                }
                ui.showLine();
            } catch (NullPointerException e) {
                //Command C can return as null if an error is triggered in parser
                //Null Pointer Exception may hence occur, the catch statement is to ensure it does not exit the loop.
            }
        }
        patientStorage.storePatients(patients);
    }

}
