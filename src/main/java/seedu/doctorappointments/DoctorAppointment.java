package seedu.doctorappointments;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DoctorAppointment {
    protected String doctorId;
    protected String patientsName;
    protected String gender;
    protected String date;

    public DoctorAppointment(String doctorId, String name, String gender, String date) {
        this.doctorId = doctorId;
        this.patientsName = name;
        this.gender = gender;
        this.date = date;
    }

    public String getDoctorId() {
        return this.doctorId;
    }

    public String getPatientsName() {
        return this.patientsName;
    }

    public String getGender() {
        return this.gender;
    }

    public String getDate() {
        return this.date;
    }

    /*
        Date format = YYYY-MM-DD
    */
    public void getDateFormat(String inputDate) {
        String dateInString = inputDate;
        LocalDate dater = LocalDate.parse(dateInString, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dater);
    }


}
