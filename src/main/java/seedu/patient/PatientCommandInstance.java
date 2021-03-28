package seedu.patient;

import seedu.duke.exceptions.DukeException;
import seedu.duke.menuparser.MenuParser;
import seedu.duke.storage.PatientStorage;
import seedu.duke.ui.PatientUI;
import seedu.duke.ui.UI;

public class PatientCommandInstance {

    private UI ui;
    private PatientList patients;
    private PatientStorage patientStorage;

    public PatientCommandInstance(String filepath) {
        ui = new UI();
        patientStorage = new PatientStorage(filepath);
        try {
            patients = new PatientList(patientStorage.loadPatients());
        } catch (DukeException e) {
            ui.showLoadingError();
            //creates new task list if failure to load from folder.
            patients = new PatientList();
        }
    }

    public void run() {
        PatientUI.patientCommandWelcome();
        boolean isReturnToStartMenu = false;
        while (!isReturnToStartMenu) {
            try {
                UI.showLine(); // show the divider line ("_______")
                PatientUI.patientMenuPrompt();
                String fullCommand = UI.scanInput();
                isReturnToStartMenu = MenuParser.patientParse(fullCommand, patients);
                UI.showLine();
            } catch (NullPointerException e) {
                //Command C can return as null if an error is triggered in parser
                //Null Pointer Exception may hence occur, the catch statement is to ensure it does not exit the loop.
            }
        }
        patientStorage.storePatients(patients);
    }

}
