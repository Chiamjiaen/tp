package seedu.model.inventory;

import seedu.duke.Constants;
import seedu.exceptions.inventory.InvalidQuantityException;
import seedu.exceptions.inventory.WrongNumberException;
import seedu.ui.InventoryUI;
import java.util.ArrayList;
import static seedu.ui.UI.prettyPrint;
import seedu.logic.errorchecker.InventoryChecker;

public class InventoryList {
    private InventoryChecker checker;
    private InventoryUI ui;
    public static ArrayList<Inventory> list = new ArrayList<>();
    public Inventory inventory;

    public InventoryList() {
    }

    /**
     * Constructor for Inventory List.
     *
     * @param load Inventory List
     */
    public InventoryList(ArrayList<Inventory> load) {
        this.list = load;
    }

    /**
     * Adds a Inventory object to or increases quantity of Inventory object in the InventoryList.
     *
     * @param argArr array of inputs for Inventory object.
     */
    public void addItems(String[] argArr) throws WrongNumberException {
        for (int i = 0; i < list.size(); i++) {
            Inventory inventoryTemp = list.get(i);
            String tempName = inventoryTemp.getItemName();
            Double tempPrice = inventoryTemp.getDoublePrice();
            if (tempName.equals(argArr[0]) && tempPrice.equals(Double.parseDouble(argArr[1]))) {
                checker.checkStoredQuantity();
                inventoryTemp.addQuantity(Integer.parseInt(argArr[2]));
                return;
            }
        }
        Inventory newInventory = new Inventory(argArr[0], Double.parseDouble(argArr[1]), Integer.parseInt(argArr[2]));
        list.add(newInventory);
    }

    /**
     * Deletes a Quantity of an Inventory object.
     *
     * @param argArr array of inputs for Inventory object.
     * @throws InvalidQuantityException when Quantity input is invalid.
     */
    public void deleteItems(String[] argArr) throws InvalidQuantityException {
        for (int i = 0; i < list.size(); i++) {
            String name = argArr[1];
            String quantityDelete = argArr[2];
            Inventory inventoryTemp = list.get(i);
            String tempName = inventoryTemp.getItemName();
            int tempQuantity = inventoryTemp.getQuantity();
            if (tempName.equals(name) && tempQuantity >= Integer.parseInt(quantityDelete)) {
                inventoryTemp.removeQuantity(Integer.parseInt(quantityDelete));
                return;
            } else if (tempName.equals(name) && tempQuantity < Integer.parseInt(quantityDelete)) {
                throw new InvalidQuantityException();
            }
        }
    }

    /**
     * Displays information of all relevant Inventory objects.
     */
    public void listItems() {
        int numberOfItems = list.size();
        if (numberOfItems != 0) {
            System.out.print(System.lineSeparator());
            ui.inventoryListHeader();
            System.out.println(Constants.LISTLINEBREAK);
            System.out.print(System.lineSeparator());
            for (Inventory inventory : list) {
                display(inventory);
            }
        } else {
            ui.emptyInventoryListMessage();
        }
    }

    /**
     * Checks if Inventory object is list.
     *
     * @param inputString Inventory object name.
     * @return true or false.
     */
    public boolean isItemStored(String inputString) {
        for (Inventory inventory : list) {
            String itemName = inventory.getItemName();
            if (itemName.equals(inputString)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Displays the format for the Inventory List.
     *
     * @param inventory Inventory object.
     */
    public static void display(Inventory inventory) {
        System.out.println(
                prettyPrint(inventory.getItemName(), 15) + " | "
                        + prettyPrint(inventory.getStringPrice(), 10) + " | "
                        + prettyPrint(Integer.toString(inventory.getQuantity()), 5));
    }

    /**
     * Returns size of Inventory List.
     *
     * @return size of Inventory List.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Returns the format the list has to be saved in Inventory text file.
     *
     * @param i index of Inventory object.
     * @return the format the list has to be saved in Inventory text file.
     */
    public String toSaveFile(int i) {
        return list.get(i).toSaveFormat();
    }

}




