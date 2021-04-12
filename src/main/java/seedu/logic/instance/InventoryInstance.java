package seedu.logic.instance;

import seedu.exceptions.HealthVaultException;
import seedu.exceptions.InsufficientInputException;
import seedu.exceptions.ExcessInputException;
import seedu.exceptions.NoInputException;
import seedu.exceptions.inventory.DuplicateItemException;
import seedu.exceptions.inventory.InvalidQuantityException;
import seedu.exceptions.inventory.WrongNumberException;
import seedu.exceptions.IllegalCharacterException;
import seedu.model.inventory.InventoryList;
import seedu.logic.parser.InventoryParser;
import seedu.model.inventory.Inventory;
import seedu.storage.InventoryStorage;
import seedu.logic.command.Command;
import seedu.ui.InventoryUI;
import seedu.ui.UI;
import java.util.ArrayList;

public class InventoryInstance {
    private InventoryUI ui;
    private InventoryList inventory;
    private InventoryStorage inventoryStorage;
    private InventoryParser parser;

    /**
     * Constructor for InventoryInstance.
     *
     * @param filePath String of the filepath for InventoryStorage.
     */
    public InventoryInstance(String filePath) {
        ui = new InventoryUI();
        inventoryStorage = new InventoryStorage(filePath);
        parser = new InventoryParser();
    }

    /**
     * Executes the Inventory Menu.
     */
    public void run() {
        try {
            ArrayList<Inventory> list = inventoryStorage.loadInventory();
            inventory = new InventoryList(list);
        } catch (HealthVaultException e) {
            ui.corruptedFileErrorMessage();
            inventory = new InventoryList();
            return;
        }
        UI.showLine(); // show the divider line ("_______")
        InventoryUI.inventoryMenuHeader();
        boolean isReturnToStartMenu = false;
        while (!isReturnToStartMenu) {
            try {
                System.out.print("\n");
                String fullCommand = ui.getInput("Inventory");
                Command c = parser.inventoryParse(fullCommand, inventory);
                c.execute(inventory, ui);
                inventoryStorage.storeInventory(inventory);
                isReturnToStartMenu = c.isExit();
                if (isReturnToStartMenu) {
                    UI.returningToStartMenuMessage();
                    System.out.print(System.lineSeparator());
                }
            } catch (NullPointerException e) {
                System.out.println("Sorry something went wrong somewhere:( Please follow format for command!");
            } catch (WrongNumberException e) {
                e.getError();
            } catch (DuplicateItemException e) {
                e.getError("ItemStored");
            } catch (IllegalCharacterException | InsufficientInputException
                    | ExcessInputException | NoInputException e) {
                System.out.println(e.getMessage());
            } catch (InvalidQuantityException e) {
                e.getError();
            } catch (HealthVaultException e) {
                e.getError("");
            }
        }
    }
}