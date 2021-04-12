package seedu.ui;

import static seedu.duke.Constants.ADD_COMMAND;
import static seedu.duke.Constants.DELETE_COMMAND;
import static seedu.duke.Constants.HELP_COMMAND;
import static seedu.duke.Constants.HELP_HEADER_COMMAND;
import static seedu.duke.Constants.HELP_HEADER_DESCRIPTION;
import static seedu.duke.Constants.HELP_HEADER_FORMAT;
import static seedu.duke.Constants.INVENTORY_ADD_DESCRIPTION;
import static seedu.duke.Constants.INVENTORY_ADD_FORMAT;
import static seedu.duke.Constants.INVENTORY_DELETE_DESCRIPTION;
import static seedu.duke.Constants.INVENTORY_DELETE_FORMAT;
import static seedu.duke.Constants.INVENTORY_FILE_PATH;
import static seedu.duke.Constants.INVENTORY_HELP_DESCRIPTION;
import static seedu.duke.Constants.INVENTORY_LIST_DESCRIPTION;
import static seedu.duke.Constants.INVENTORY_LIST_FORMAT;
import static seedu.duke.Constants.LIST_COMMAND;
import static seedu.duke.Constants.MARK_BLANK;
import static seedu.duke.Constants.RETURN_COMMAND;
import static seedu.duke.Constants.RETURN_DESCRIPTION;

public class InventoryUI extends UI {
    /**
     * Displays an output after adding a Inventory object.
     *
     * @param addedItemName Item name.
     * @param addedItemQuantity Quantity of Item.
     */
    public static void itemAddedMessage(String addedItemName, int addedItemQuantity) {
        System.out.println(addedItemQuantity + " " + addedItemName + " is added into Inventory!");
    }

    /**
     * Displays an output when Inventory List is empty.
     */
    public static void emptyInventoryListMessage() {
        System.out.println("You do not have any Items in your inventory:(");
    }

    /**
     * Displays an output header for the list command.
     */
    public static void inventoryListHeader() {
        System.out.println(
                UI.prettyPrint("Items Name", 15) + " | "
                        + UI.prettyPrint("Price", 10) + " | "
                        + UI.prettyPrint("Quantity", 5));
    }

    /**
     * Displays an output for Help Message.
     */
    public static void printInventoryHelpMessage() {

        UI.printEmptyLine();
        System.out.println("Here is a list of Inventory commands: ");

        UI.printEmptyLine();
        int[] lengthPara = {10,60,50};
        printer(new String[]{HELP_HEADER_COMMAND, HELP_HEADER_DESCRIPTION, HELP_HEADER_FORMAT}, lengthPara);
        UI.showLongLine();
        printer(new String[]{HELP_COMMAND, INVENTORY_HELP_DESCRIPTION, MARK_BLANK}, lengthPara);
        printer(new String[]{ADD_COMMAND, INVENTORY_ADD_DESCRIPTION, INVENTORY_ADD_FORMAT}, lengthPara);
        printer(new String[]{LIST_COMMAND, INVENTORY_LIST_DESCRIPTION, INVENTORY_LIST_FORMAT}, lengthPara);
        printer(new String[]{DELETE_COMMAND, INVENTORY_DELETE_DESCRIPTION, INVENTORY_DELETE_FORMAT}, lengthPara);
        printer(new String[]{RETURN_COMMAND, RETURN_DESCRIPTION, MARK_BLANK}, lengthPara);
    }

    /**
     * Displays an output after deleting quantity of Inventory object.
     *
     * @param deletedItem Item name
     * @param deletedItemQuantity Quantity of Item
     */
    public static void deleteItemMessage(String deletedItem, String deletedItemQuantity) {
        System.out.println("Noted. I've removed " + deletedItemQuantity + " " + deletedItem);
    }

    /**
     * Displays an output when accessing the Inventory Menu.
     */
    public static void inventoryMenuHeader() {
        System.out.print("Welcome to Inventory Menu!\nType \"help\" for Inventory menu commands\n");
    }

    /**
     * Displays an error message when file data is corrupted.
     */
    public void corruptedFileErrorMessage() {
        System.out.println("The file (" + INVENTORY_FILE_PATH + ") is corrupted!\n"
                + "Please exit the program and delete the corrupted file before trying to access Inventory Menu!");
    }
}