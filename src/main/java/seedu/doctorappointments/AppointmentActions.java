package seedu.doctorappointments;


import java.util.ArrayList;

public class AppointmentActions {
    public static ArrayList<DoctorAppointment> appointmentList = new ArrayList<DoctorAppointment>();

    public static void addAppointment(String input) {
        String[] inputArray = input.split(" ");

        String iD = inputArray[1];
        String patientName = inputArray[2];
        String gender = inputArray[3];
        String date = inputArray[4];


        DoctorAppointment newAppointment = new DoctorAppointment(iD, patientName, gender, date);

        System.out.println("Appointment Added");
        appointmentList.add(newAppointment);
    }

    public static void listAppointment(String input) throws Exception {
        String[] inputArray = input.split(" ");

        String iD = inputArray[1];
        if (appointmentList.size() == 0) throw new Exception();
        else {
            for (DoctorAppointment doc : appointmentList) {
                if (doc.getDoctorId().equals(iD)) {
                    System.out.println(doc.getDoctorId());
                    System.out.println(doc.getPatientsName());
                    System.out.println(doc.getGender());
                    doc.getDateFormat(doc.getDate());
                }
            }
        }
    }

    public static void deleteAppointment(String input) {
        String[] inputArray = input.split(" ");
        String iD = inputArray[1];

        for (DoctorAppointment doc : appointmentList) {
            if (doc.getDoctorId().equals(iD)) {
                appointmentList.remove(iD);
            }
        }

        int index = 2;
        for (int i = 0; i < appointmentList.size(); i++) {
            if(appointmentList.get(i).doctorId.equals(iD))
                index =i;
                //System.out.println(appointmentList.get(i).doctorId.equals(iD));
        }
        System.out.print(index);
        appointmentList.remove(index);

    }
}
