package seedu.patient;

import seedu.duke.ui.UI;

import java.util.ArrayList;

public class PatientList {
    private static ArrayList<Patient> patients = new ArrayList<Patient>();

    public PatientList() {
    }

    public PatientList(ArrayList<Patient> load) {
        this.patients = load;
    }

    public static void addPatient(String patientID, String name, int age, String gender, String illness, String drugsNeeded) {
        Patient newPatient = new Patient(patientID, name, age, gender, illness, drugsNeeded);
        patients.add(newPatient);
        UI.patientAddedMessage(name);
    }

    public static void findPatient(String inputString) {
        int numberOfPatients = patients.size();
        for (int i = 0; i < numberOfPatients; i++) {
            String patientDetails = patients.get(i).getPatientDetails();
            if (patientDetails.contains(inputString)) {
                UI.printPatientList(i, patientDetails);
            }
        }
    }

    public static void listPatients() {
        int numberOfPatients = patients.size();
        if (numberOfPatients == 0) {
            UI.emptyPatientListMessage();
        } else {
            UI.notEmptyPatientListMessage();
            for (int i = 0; i < numberOfPatients; i++) {
                UI.printPatientList(i, patients.get(i).getPatientDetails());
            }
        }
    }

    public static void deletePatient(int inputInt) {
        int patientIndex = inputInt - 1;
        if (inputInt <= 0 || inputInt > patients.size()) {
            UI.incorrectInput();
        }
        Patient toBeDeleted = patients.get(patientIndex);
        patients.remove(patientIndex);
        UI.deletePatientMessage(toBeDeleted.getPatientDetails(), patients.size());
    }

    public int getSize() {
        return patients.size();
    }

    public String toSaveFile(int i) {
        return patients.get(i).toSaveFormat();
    }
}
