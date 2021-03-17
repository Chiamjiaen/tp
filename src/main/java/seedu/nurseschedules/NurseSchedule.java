package seedu.nurseschedules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NurseSchedule implements Comparable<NurseSchedule> {
    protected String nurseID;
    protected String patientID;
    protected String datetime;

    public NurseSchedule(String nurseID, String patientID, String datetime) {
        this.nurseID = nurseID;
        this.patientID = patientID;
        this.datetime = datetime;
    }

    /** Compares as Date object */
    @Override
    public int compareTo(NurseSchedule o) {
        if (getDatetime() == null || o.getDatetime() == null) {
            return 0;
        }
        SimpleDateFormat parser = new SimpleDateFormat("ddMMyyyy");
        Date date = null;
        Date odate = null;
        try {
            date = parser.parse(getDatetime());
            odate = parser.parse(o.getDatetime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.compareTo(odate);
    }

    /** Formats String date into dd/MM/YYYY */
    public String formatDate(String datetime) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("ddMMyyyy");
        Date date = parser.parse(datetime);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");

        return formatter.format(date);
    }

    public String getNurseID() { return nurseID; }

    public String getPatientID() { return patientID; }

    public String getDatetime() { return datetime; }

    public String getFormattedDatetime() {
        String date = null;
        try {
            date = formatDate(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String toSave() { return getNurseID() + "|" + getPatientID() + "|" + getDatetime(); }

    public String toFind() { return getFormattedDatetime() + " " + patientID; }

    public String toString() { return nurseID + " " + patientID + " " + getFormattedDatetime(); }
}
