package seedu.staff;

import seedu.duke.exceptions.WrongListInputException;
import seedu.duke.exceptions.WrongStaffIdException;

import seedu.duke.storage.StaffStorage;
import seedu.duke.ui.UI;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static seedu.duke.ui.UI.staffHeader;

public class Parser {

    public static void run() throws IOException {
        StaffStorage.fileHandling();
        System.out.println("Type something");
        Scanner in = new Scanner(System.in);
        while (true) {
            String line;
            line = in.nextLine();
            try {
                if (commandHandler(line) == 0) {
                    break;
                }
            } catch (WrongStaffIdException e) {
                UI.WrongStaffIDErrorMessage();
            } catch (WrongListInputException e) {
                UI.WrongListInputErrorMessage();
            }
        }
    }
    public static void checkID(String line) throws WrongStaffIdException {
        line = line.split(" ")[1];
        try {
            Integer.parseInt(line.substring(1));
        } catch (NumberFormatException e) {
            throw new WrongStaffIdException();
        }
        if ((line.charAt(0) == 'D' || line.charAt(0) == 'N')){
            throw new WrongStaffIdException();
        }
    }

    public static void checkListCommand(String line) throws WrongListInputException {

        if ((line.split(" ").length > 1) && !((line.equals("nurses") || line.equals("doctors")))) {
            throw new WrongListInputException();
        }
    }

    public static int commandHandler(String line) throws IOException, WrongStaffIdException,
            WrongListInputException {

        switch (line.split(" ")[0]) {
        case ("add"):
            StaffList.add(line);
            break;

        case ("list"):
            checkListCommand(line);
            staffHeader();
            UI.showLine();
            String[] string = Arrays.copyOfRange(line.split(" "), 1, 2);
            StaffList.list(string);
            break;

        case ("delete"):
            checkID(line);
            StaffList.delete(line);
            break;
        case ("help"):
            UI.printStaffHelpList();
            break;

        case ("find"):
            checkID(line);
            UI.showLine();
            staffHeader();
            UI.showLine();
            try {
                StaffList.find(line.split(" ")[1]);
            } catch (IndexOutOfBoundsException e ) {
                System.out.println("No input error");
            }
            break;

        case ("bye"):
            StaffStorage.writeToFile();
            return 0;
        }
        return 1;
    }

    protected static String[] addFunctionParser(String line) {
        int length = line.split(" ").length;
        String[] input = new String[4];
        String[] array = line.split(" ");
        for (int i = 1; i<length; i++) {
            try {
                if (i <= 4) {
                    input[i-1] = array[i];
                } else {
                    input[3] = input[3] + " " + array[i];
                }

            } catch (IndexOutOfBoundsException e){
                input[i-1] = " ";
            }
        }
        return input;
    }
}
