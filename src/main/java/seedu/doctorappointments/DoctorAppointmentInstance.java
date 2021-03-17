package seedu.doctorappointments;

import java.util.Scanner;

public class DoctorAppointmentInstance {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name? MingShun?");

        Scanner userInput = new Scanner(System.in);

        while (userInput.hasNext()) {
            String input = userInput.nextLine();
            String[] inputArray = input.split(" ");

            switch (inputArray[0]) {
            case "add":
                AppointmentActions.addAppointment(input);
                break;
            case "list":
                AppointmentActions.listAppointment(input);
                break;
            case "delete":
                AppointmentActions.deleteAppointment(input);
                break;
            }
        }
    }
}
