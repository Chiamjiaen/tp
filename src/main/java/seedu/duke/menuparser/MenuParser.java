package seedu.duke.menuparser;


import seedu.duke.exceptions.DukeException;
import seedu.duke.ui.UI;
import seedu.patient.PatientList;

public class MenuParser {

    /**
     * Checks the number of tokens and throws an exception based on the command
     *
     * @param numberOfTokens number of tokens in the string
     * @param command        interpreted command from the user to be used in error finding
     * @throws DukeException exception based on command
     */
    public static void lengthCheck(int numberOfTokens, String command) throws DukeException {
        if (numberOfTokens < 2) {
            throw new DukeException(command);
        }
    }

    public static boolean patientParse(String fullCommand) {
        String[] stringTokens = fullCommand.trim().split(" ", 2);
        int numberOfTokens = stringTokens.length;
        String command = stringTokens[0];
        try {
            switch (command) {
            case "list":
                PatientList.listPatients();
                break;
            case "add":
                lengthCheck(numberOfTokens, command);
                String[] toCommand = stringTokens[1].trim().split(" ", 6);
                PatientList.addPatient(toCommand[0], toCommand[1], Integer.parseInt(toCommand[2]),
                        toCommand[3], toCommand[4], toCommand[5]);
                break;
            case "delete":
                lengthCheck(numberOfTokens, command);
                PatientList.deletePatient(Integer.parseInt(stringTokens[1]));
                break;
            case "find":
                lengthCheck(numberOfTokens, command);
                PatientList.findPatient(stringTokens[1]);
                break;
            case "help":
                UI.printPatientHelpList();
            case "return":
                return true;
            default:
                UI.unrecognizedCommandMessage();
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            UI.invalidFormatMessage();
        } catch (DukeException e) {
            e.getError(command);
        }
        return false;
    }
}
