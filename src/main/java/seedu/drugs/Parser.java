package seedu.drugs;

import java.util.Scanner;

public class Parser {

    protected DrugAction drugAction;

    public Parser(DrugAction drugAction) {
        this.drugAction = drugAction;
    }
    public void parseMethod() {
        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                drugAction.printList();
            } else if (command.contains("add")) {
                drugAction.addDrugs(command);
            } else if (command.contains("delete")) {
                drugAction.deleteDrugs(command);
            } else {
                System.out.println("There is no such action! Please only enter the following: ");
                System.out.println("1) add <...>\r\n2) delete<...>\r\n3) list\r\n4) bye");
            }
            command = myObj.nextLine();
        }
    }
}
