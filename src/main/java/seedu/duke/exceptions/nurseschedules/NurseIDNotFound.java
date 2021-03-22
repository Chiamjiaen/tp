package seedu.duke.exceptions.nurseschedules;

import seedu.duke.exceptions.DukeException;

public class NurseIDNotFound extends DukeException {
    public String getMessage() {
        return "NurseID does not exist! Please check ID and try again!";
    }
}