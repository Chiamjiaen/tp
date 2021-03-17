package seedu.staff;

import seedu.duke.ui.UI;

import java.util.ArrayList;
import java.util.Iterator;

import static seedu.duke.ui.UI.prettyPrint;
import static seedu.staff.Parser.addFunctionParser;

public class StaffList {
    private static final String DOCTOR_TYPE = "D";
    private static final String NURSE_TYPE = "N";
    private static final ArrayList<Staff> list = new ArrayList<>();
    protected static int numStaff = 0;

    public static void addStaff(Staff staff) {
        list.add(staff);
        numStaff++;
    }

    public static void add(String line) {
        String[] array = addFunctionParser(line);
        if (isValidID(array[0])) {
            Staff staff = new Staff(array);
            addStaff(staff);
            UI.hiredOutput(line);
        }
    }
    public static boolean isValidID(String id) {
        for (Staff staff: list){
            if (staff.getId().equals(id)) {
                System.out.println("Error that staff ID has been taken/n");
                return false;
            }
        }
        return true;
    }

    public static ArrayList getList(){
        return list;
    }

    public static void list(String ... parameter){
        if (parameter[0] == (null)) {
            for (Staff staff : list) {
                display(staff);
            }
        } else if (parameter[0].equals("nurses")) {
            for (Staff staff: list){
                if (staff.getType().equals(NURSE_TYPE)) {
                    display(staff);
                }
            }
        } else if (parameter[0].equals("doctors")) {
            for (Staff staff: list){
                if (staff.getType().equals(DOCTOR_TYPE)) {
                    display(staff);
                }
            }
        }
    }

    public static void find(String keyword) {
        for (Staff staff: list){
            if (search(keyword, staff)) {
                display(staff);
            }
        }
    }

    public static boolean search(String keyword, Staff staff) {
        return staff.getAge().contains(keyword) || staff.getName().contains(keyword)
                || staff.getId().contains(keyword) || staff.getSpecialisation().contains(keyword);
    }

    public static void delete(String line) {
        int i = 0;
        for (Iterator<Staff> iterator = list.iterator(); iterator.hasNext(); ) {
            Staff staff = iterator.next();
            if (staff.getId().equals(line.split(" ")[1])) {
                iterator.remove();
            }
            i++;
        }
        System.out.println(line.split(" ")[1] + " has been fired.");
    }

    public static void display(Staff staff) {
        System.out.println(
                prettyPrint(staff.getId(), 10) + " | " + prettyPrint(staff.getName(), 10) + " | "
                        + prettyPrint(staff.getAge(), 5) + " | " + prettyPrint(staff.getSpecialisation(), 20));
    }

    public static int getNumStaff() {
        return numStaff;
    }
}
