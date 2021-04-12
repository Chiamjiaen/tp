package seedu.logic.command.inventory;

import seedu.exceptions.inventory.WrongNumberException;
import seedu.logic.command.Command;
import seedu.model.inventory.InventoryList;
import seedu.ui.InventoryUI;

/**
 * InventoryAdd Command executes the necessary action for adding a Inventory object.
 */
public class InventoryAdd extends Command {

    private String[] argArr;

    /**
     * Constructor for InventoryAdd command.
     *
     * @param args Array of inputs for Inventory object.
     */
    public InventoryAdd(String[] args) {
        this.argArr = args;
    }

    /**
     * Adds an Inventory object or increases quantity of Inventory object.
     * @param items Inventory list.
     * @param ui Instance of InventoryUI used.
     */
    public void execute(InventoryList items, InventoryUI ui) throws WrongNumberException {
        items.addItems(argArr);
        ui.itemAddedMessage(argArr[0], Integer.parseInt(argArr[2]));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}