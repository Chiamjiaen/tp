package seedu.logic.parser;


import seedu.exceptions.HealthVaultException;
import seedu.exceptions.UnrecognizedCommandException;
import seedu.logger.HealthVaultLogger;
import seedu.model.doctorappointment.AppointmentList;
import seedu.logic.command.Command;
import seedu.logic.command.doctorappointment.*;
import seedu.logic.errorchecker.DoctorAppointmentChecker;
import seedu.logic.errorchecker.MainChecker;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.ui.UI.smartCommandRecognition;

public class DoctorAppointmentParser {

    public static Logger logger = HealthVaultLogger.getLogger();

    public static Command parse(String input, AppointmentList details) throws HealthVaultException {
        final String[] COMMANDS = {"add", "delete", "list", "return", "help"};

        String[] inputArray = input.split("/");
        assert inputArray.length > 0;
        assert inputArray.length < 7;
        Command c = null;
        MainChecker.checkBlankInput(input);
        MainChecker.checkNumInput(input, 6,1);
        switch (smartCommandRecognition(COMMANDS, input.split("/")[0])) {
        case "add": {
            logger.log(Level.INFO, "Parsing Add command");
            MainChecker.checkNumInput(input, 6, 6);
            DoctorAppointmentChecker.checkValidDataForAdd(inputArray);
            c = new DoctorAppointmentAdd(inputArray);
            break;
        }
        case "list": {
            logger.log(Level.INFO, "Parsing List command");
            MainChecker.checkNumInput(input, 2, 2);
            DoctorAppointmentChecker.checkValidDataForList(inputArray);
            c = new DoctorAppointmentList(inputArray);
            break;
        }
        case "delete": {
            logger.log(Level.INFO, "Parsing Delete command");
            MainChecker.checkNumInput(input, 2, 2);
            DoctorAppointmentChecker.checkValidDataForDelete(inputArray);
            c = new DoctorAppointmentDelete(inputArray);
            break;
        }
        case "return":
            logger.log(Level.INFO, "Parsing Return command");
            c = new DoctorAppointmentReturn();
            break;
        case "help":
            logger.log(Level.INFO, "Parsing Help command");
            c = new DoctorAppointmentHelp();
            break;
        default:
            logger.log(Level.WARNING, "Unrecognized Command Exception being handled");
            throw new UnrecognizedCommandException();
        }
        return c;
    }


}
