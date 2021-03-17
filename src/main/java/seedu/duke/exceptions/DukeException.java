package seedu.duke.exceptions;

public class DukeException extends Exception {

    protected String error;

    /**
     * Instantiates this exception
     *
     * @param error error type
     */
    public DukeException(String error) {
        this.error = error;
    }

    /**
     * Shows the error encountered by the user
     *
     * @param input error type
     */
    public void getError(String input) {
        switch (error) {
        case "done":
            System.out.println("OOPS! I need the command to be in the right format! \n" +
                    "Try \"done [(integer) task number]\" \n" +
                    "Please make sure you do not include any brackets [ or ] in your input! \n" +
                    "Also make sure that the task number is actually accounted for in the list! ");
            break;
        case "todo":
            System.out.println("OOPS! I need the command to be in the right format! \n" +
                    "Try \"todo [description]\" \n" +
                    "Please make sure you do not include any brackets [ or ] in your input!");
            break;
        case "deadline":
            System.out.println("OOPS! I need the command to be in the right format! \n" +
                    "Try \"deadline [description] /by [time]\" \n" +
                    "Please make sure you do not include any brackets [ or ] in your input!");
            break;
        case "event":
            System.out.println("OOPS! I need the command to be in the right format! \n" +
                    "Try \"event [description] /at [time]\" \n" +
                    "Please make sure you do not include any brackets [ or ] in your input!");
            break;
        case "delete":
            System.out.println("OOPS! I need the command to be in the right format! \n" +
                    "Try \"delete [(integer) task number]\" \n" +
                    "Please make sure you do not include any brackets [ or ] in your input! \n" +
                    "Also make sure that the task number is actually accounted for in the list! ");
            break;
        case "loadFile":
            System.out.println("OOPS! The file format is wrong! It may have been corrupted! \n" +
                    "Please delete the file \"data/PatientList.txt\" to try making another file! ");
        default:
            System.out.println("OOPS! Your command may not be valid! \n" +
                    "Please check the list of available commands using \"help\"");
            break;
        }
    }
}
