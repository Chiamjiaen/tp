package seedu.logic.command;

import seedu.exceptions.DuplicateIdException;
import seedu.exceptions.inventory.InvalidQuantityException;
import seedu.exceptions.inventory.WrongNumberException;
import seedu.exceptions.nurseschedules.NurseIdNotFound;
import seedu.exceptions.nurseschedules.InvalidiDTypeException;
import seedu.exceptions.nurseschedules.NurseCrossValidationError;
import seedu.exceptions.nurseschedules.PatientIdNotFound;
import seedu.exceptions.nurseschedules.PatientCrossValidationError;
import seedu.exceptions.nurseschedules.DuplicateScheduleException;
import seedu.exceptions.HealthVaultException;
import seedu.model.doctorappointment.AppointmentList;
import seedu.model.inventory.InventoryList;
import seedu.model.nurseschedule.NurseScheduleList;
import seedu.model.patient.PatientList;
import seedu.model.staff.StaffList;
import seedu.storage.StaffStorage;

import seedu.ui.InventoryUI;
import seedu.ui.DoctorAppointmentUI;
import seedu.ui.NurseScheduleUI;
import seedu.ui.PatientUI;
import seedu.ui.StaffUI;


import java.io.IOException;

/**
 * Abstract class that represents a command to be executed.
 */
public abstract class Command {

    public Command() {
    }

    public void execute() {
    }

    public void execute(PatientList patients, PatientUI ui){
    }

    public void execute(StaffList staff, StaffUI staffUI, StaffStorage staffStorage) throws IOException {
    }

    public void execute(InventoryList drugs, InventoryUI ui) throws InvalidQuantityException, WrongNumberException {
    }

    public void execute(AppointmentList appointment, DoctorAppointmentUI ui) throws HealthVaultException, IOException,
            Exception {
    }

    public void execute(NurseScheduleList nurseSchedule, NurseScheduleUI ui) throws NurseIdNotFound,
            InvalidiDTypeException, NurseCrossValidationError, DuplicateIdException, PatientIdNotFound,
            PatientCrossValidationError, DuplicateScheduleException {
    }

    public abstract boolean isExit();

}
