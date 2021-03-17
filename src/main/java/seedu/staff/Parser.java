package seedu.staff;

import seedu.duke.exceptions.NoInputException;
import seedu.duke.exceptions.WrongListInputException;
import seedu.duke.exceptions.WrongStaffIdException;
import seedu.duke.storage.StaffStorage;
import seedu.duke.ui.UI;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static seedu.duke.ui.UI.staffHeader;
import static seedu.duke.ui.UI.staffMenuHeader;

public class Parser {

    public static void run() throws IOException{
        StaffStorage.fileHandling();
        staffMenuHeader();
        Scanner in = new Scanner(System.in);
        while (true) {
            UI.staffMenuPrompt();
            String line;
            line = in.nextLine();
            try {
                if (commandHandler(line) == 0) {
                    System.out.println("Back to main menu\n");
                    break;
                }
            } catch (WrongStaffIdException e) {
                UI.WrongStaffIDErrorMessage();
            } catch (WrongListInputException e) {
                UI.WrongListInputErrorMessage();
            } catch (NoInputException e) {
                UI.NoInputErrorMessage();
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
        if (!(line.charAt(0) == 'D' || line.charAt(0) == 'N')){
            throw new WrongStaffIdException();
        }
    }
    public static void checkEmptyInput(String line) throws NoInputException {
        if (line.split(" ").length < 2) {
            throw new NoInputException();
        }
    }

    public static void checkListCommand(String line) throws WrongListInputException {

        if ((line.split(" ").length > 1) &&
                !((line.split(" ")[1].equals("nurses") || line.split(" ")[1].equals("doctors")))) {
            throw new WrongListInputException();
        }
    }

    public static int commandHandler(String line) throws IOException, WrongStaffIdException,
            WrongListInputException, NoInputException {
        if (line.equals(" ")) {
            UI.noCommandErrorMessage();
            return 1;
        }
        switch (line.split(" ")[0]) {
        case ("add"):
            StaffList.add(line);
            break;

        case ("list"):
            UI.emptyLine();
            checkListCommand(line);
            UI.staffHeader();
            UI.showLine();
            String[] string = Arrays.copyOfRange(line.split(" "), 1, 2);
            StaffList.list(string);
            UI.emptyLine();
            break;

        case ("delete"):
            checkEmptyInput(line);
            checkID(line);
            StaffList.delete(line);
            break;

        case ("help"):
            UI.printStaffHelpList();
            break;

        case ("find"):
            checkEmptyInput(line);
            staffHeader();
            UI.showLine();
            StaffList.find(line.split(" ")[1]);
            UI.emptyLine();
            break;

        case ("return"):
            StaffStorage.writeToFile();
            return 0;

        default:
            UI.unrecognizedCommandMessage();
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
