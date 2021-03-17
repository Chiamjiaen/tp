package seedu.patient;

public class Patient {
    protected String patientID;
    protected String name;
    protected int age;
    protected String gender;
    protected String illness;
    protected String drugsNeeded;

    public Patient(String patientID, String name, int age, String gender, String illness, String drugsNeeded) {
        this.patientID = patientID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.illness = illness;
        this.drugsNeeded = drugsNeeded;
    }

    public String getPatientDetails() {
        return (patientID + ", " + name + ", " + age + ", " +
                gender + ", " + illness + ", " + drugsNeeded);
    }

    public String toSaveFormat() {
        return (patientID + " | " + name + " | " + age + " | " +
                gender + " | " + illness + " | " + drugsNeeded);
    }
}
