package seedu.drugs;

import seedu.duke.storage.DrugStorage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DrugInstance {

    /**
     * Main entry-point for the java.duke.DrugInstance application.
     */
    protected DrugStorage drugStorage;
    protected DrugAction drugAction;
    protected ArrayList<Drug> Drugs;
    public DrugInstance(String filePath) {
        drugStorage = new DrugStorage(filePath);
        try {
            Drugs = drugStorage.uploadDrugs();
            drugAction = new DrugAction();
        } catch (FileNotFoundException e) {
            Drugs = drugStorage.createNewFile();
        }
    }

    public void run() {
        Parser parser = new Parser(drugAction);
        parser.parseMethod();
        drugStorage.exitProgram();
    }

}
