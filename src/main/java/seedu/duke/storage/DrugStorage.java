package seedu.duke.storage;
import seedu.drugs.Drug;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class DrugStorage {
    protected String filepath;
    protected ArrayList<Drug> Drugs = new ArrayList<>();
    public DrugStorage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Drug> createNewFile() {
        File drugsFile = new File(getFilepath());
        try {
            if (drugsFile.createNewFile()) {
                System.out.println("\tTo save your task locally,\n" +
                        "\tA new file has been created at:\n\t" +
                        drugsFile.getAbsolutePath() + "\n");
            }
        } catch (IOException e) {
            System.out.println("\tThere was an I/O error:\nBye!\n");
            e.printStackTrace();
        }
        return Drugs;
    }

    public String getFilepath() {
        return this.filepath;
    }

    public ArrayList<Drug> uploadDrugs() throws FileNotFoundException {
        File f = new File(getFilepath());
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] part = s.nextLine().split(" | ");
            Drugs.add(new Drug(part[0], part[1], part[2]));
        }
        return Drugs;
    }

    public void saveDrugs() throws IOException {
        FileWriter fw = new FileWriter(getFilepath());
        for (Drug Drug : Drugs) {
            fw.write(Drug.stringToSave());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        try {
            saveDrugs();
        } catch (IOException e) {
            System.out.println("No file was saved due to an I/O error.\n");
        }
    }

}


