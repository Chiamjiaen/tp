package seedu.logic.errorchecker;

import seedu.exceptions.CorruptedFileException;
import seedu.exceptions.HealthVaultException;
import seedu.exceptions.NoInputException;
import seedu.exceptions.inventory.DuplicateItemException;
import seedu.exceptions.inventory.NonExistentItemException;
import seedu.exceptions.inventory.WrongNumberException;
import seedu.exceptions.IllegalCharacterException;
import seedu.model.inventory.InventoryList;
import seedu.model.inventory.Inventory;
import java.util.ArrayList;

public class InventoryChecker extends MainChecker {
    private InventoryList inventory;
    private ArrayList<Inventory> inventoryArrayList;
    private String[] stringTokens;
    private int numberOfTokens;

    /**
     * Constructor for InventoryChecker.
     *
     * @param inventory      Inventory List of Inventory objects.
     * @param stringTokens   array of field inputs.
     * @param numberOfTokens number of field inputs.
     */
    public InventoryChecker(InventoryList inventory, String[] stringTokens, int numberOfTokens) {
        this.inventory = inventory;
        this.stringTokens = stringTokens;
        this.numberOfTokens = numberOfTokens;
    }

    /**
     * Constructor for InventoryChecker.
     *
     * @param inventory      Array list of Inventory objects.
     * @param stringTokens   array of field inputs.
     * @param numberOfTokens number of field inputs.
     */
    public InventoryChecker(ArrayList<Inventory> inventory, String[] stringTokens, int numberOfTokens) {
        inventoryArrayList = inventory;
        this.stringTokens = stringTokens;
        this.numberOfTokens = numberOfTokens;
    }

    /**
     * Calls various checker functions to determine the validity of data
     * when loading data from text files.
     *
     * @throws HealthVaultException If any input field from data files is corrupted.
     */
    public void checkStorage() throws HealthVaultException {
        emptySpaceCheck();
        checkStorageLength();
        illegalCharacterChecker(stringTokens[0], "name");
        illegalCharacterChecker(stringTokens[2], "quantity");
        checkPrice(stringTokens[1]);
        checkQuantity(stringTokens[2]);
        checkDuplicate(stringTokens[0], Double.parseDouble(stringTokens[1]));
    }

    /**
     * Calls various checker functions to determine the validity of inputs for Add command.
     *
     * @throws DuplicateItemException    If Inventory object already exist in list.
     * @throws WrongNumberException      If any input for price and quantity is invalid.
     * @throws IllegalCharacterException If any illegal character is present.
     */
    public void checkAdd() throws DuplicateItemException, WrongNumberException, IllegalCharacterException {
        stringTokens[1] = stringTokens[1].toLowerCase();
        illegalCharacterChecker(stringTokens[1], "name");
        illegalCharacterChecker(stringTokens[3], "quantity");
        checkPrice(stringTokens[2]);
        checkQuantity(stringTokens[3]);
        checkDuplicate(stringTokens[1], Double.parseDouble(stringTokens[2]));
    }

    /**
     * Calls various checker functions to determine the validity of inputs for Delete command.
     *
     * @throws IllegalCharacterException If any illegal character is present.
     * @throws NonExistentItemException  If item name does not exist in list.
     */
    public void checkDelete() throws IllegalCharacterException, NonExistentItemException, WrongNumberException {
        checkQuantity(stringTokens[2]);
        stringTokens[1] = stringTokens[1].toLowerCase();
        illegalCharacterChecker(stringTokens[1], "name");
        isNameExist(stringTokens[1], inventory);
    }

    /**
     * Checks the validity of data in the Inventory text file.
     *
     * @throws HealthVaultException If any of the data is changed.
     */
    public void checkStorageLength() throws HealthVaultException {
        if (numberOfTokens != 3) {
            throw new CorruptedFileException("Inventory");
        }
    }

    /**
     * Checks if input is black.
     *
     * @throws NoInputException If there is any empty spaces.
     */
    public void emptySpaceCheck() throws NoInputException {
        for (int i = 0; i < numberOfTokens; i++) {
            if (stringTokens[i].trim().equals("")) {
                throw new NoInputException();
            }
        }
    }

    /**
     * Checks if name of Inventory object is taken.
     *
     * @param inputString Name of Inventory object.
     * @param price       Price of Inventory object.
     * @throws DuplicateItemException If Inventory object already exist in list.
     */
    public static void checkDuplicate(String inputString, Double price) throws DuplicateItemException {
        for (Inventory inventory : InventoryList.list) {
            String itemName = inventory.getItemName();
            Double itemPrice = inventory.getDoublePrice();
            if (itemName.equalsIgnoreCase(inputString) && !itemPrice.equals(price)) {
                throw new DuplicateItemException();
            }
        }
    }

    /**
     * Checks the validity of input field, quantity of Inventory object.
     *
     * @param number Quantity of Inventory object.
     * @throws WrongNumberException If input quantity is invalid.
     */
    public static void checkQuantity(String number) throws WrongNumberException {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new WrongNumberException("quantity");
        }
        if (Integer.parseInt(number) <= 0 || Integer.parseInt(number) >= 1000000) {
            throw new WrongNumberException("quantity");
        }
    }

    /**
     * Checks the validity of input price.
     *
     * @param price Price of Inventory object.
     * @return True or false. (1 or 0)
     * @throws WrongNumberException If input price is invalid.
     */
    public static boolean checkPrice(String price) throws WrongNumberException {
        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) { //check if price is a double
            throw new WrongNumberException("price");
        }
        if (Double.parseDouble(price) <= 0 || Double.parseDouble(price) >= 1000000) {
            throw new WrongNumberException("price");
        }
        return false;
    }

    /**
     * Checks if name exist in the Inventory list.
     *
     * @param userInput Name of Inventory object.
     * @param items     Inventory List.
     * @throws NonExistentItemException If name does not exist in Inventory list.
     */
    public void isNameExist(String userInput, InventoryList items) throws NonExistentItemException {
        if (!items.isItemStored(userInput)) {
            throw new NonExistentItemException("NameDoesNotExist");
        }
    }

    public void checkStoredQuantity() throws WrongNumberException {
        for (Inventory inventory : InventoryList.list) {
            int quantity = inventory.getQuantity();
            if (quantity >= 1000000) {
                throw new WrongNumberException("quantity");
            }
        }

    }
}