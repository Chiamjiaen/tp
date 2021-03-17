package seedu.drugs;

import java.util.ArrayList;

public class DrugAction {
    public ArrayList<Drug> Drugs = new ArrayList<>();

    public void addDrugs(String description) {
        try {
            description = description.substring(4);
            String[] elements = description.split(" ");
            Drugs.add(new Drug(elements[0], elements[1], elements[2]));
            System.out.println("Added " + elements[elements.length - 1] + " " + elements[0] + " to inventory!");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("The description of 'add' cannot be empty");
            return;
        }
    }

    public void deleteDrugs(String description) {
        try {
            description = description.substring(7);
            for (int i = 0; i< Drugs.size(); ++i) {
                if (Drugs.get(i).getName().contains(description)) {
                    System.out.println("Deleted " + Drugs.get(i).getName() + " from inventory!");
                    Drugs.remove(Drugs.get(i));
                    break;
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("The description of 'delete' cannot be empty");
            return;
        }
    }

    public void printList() {
        if (Drugs.size() != 0) {
            System.out.println("Current Inventory: ");
            for (int i = 1; i <= Drugs.size(); ++i) {
                System.out.println(i + ". " + Drugs.get(i-1).getName() + " " + Drugs.get(i-1).getPrice() + " " + Drugs.get(i-1).getQuantity());
            }
        } else {
            System.out.println("You do not have any Drugs in your inventory:(");
        }
    }

}




