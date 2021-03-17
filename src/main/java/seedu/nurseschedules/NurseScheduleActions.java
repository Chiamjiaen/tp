package seedu.nurseschedules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NurseScheduleActions {

    List<NurseSchedule> findSchedules = new ArrayList<NurseSchedule>();
    List<String> nursesFound = new ArrayList<String>();

    public void listSchedules(List<NurseSchedule> nurseSchedules, String[] details) {
        int i = 0;
        if (details[0].equals("all")) {
            listAllSchedules(nurseSchedules);
        } else {
            findSchedules.clear();
            while (i < nurseSchedules.size()) {
                if (nurseSchedules.get(i).getNurseID().equals(details[0])) {
                    findSchedules.add(nurseSchedules.get(i));
                }
                i++;
            }
            Collections.sort(findSchedules);

            int j = 0;
            System.out.println(details[0]);
            while (j < findSchedules.size()) {
                System.out.println("\t" + findSchedules.get(j).toFind());
                j++;
            }
        }
    }

    public void listAllSchedules(List<NurseSchedule> nurseSchedules) {
        nursesFound.clear();
        for (int i = 0; i<nurseSchedules.size(); i++) {
            findSchedules.clear();
            if (!nursesFound.contains(nurseSchedules.get(i).getNurseID())) {
                nursesFound.add(nurseSchedules.get(i).getNurseID());
                String nurseID = nurseSchedules.get(i).getNurseID();
                for (NurseSchedule nurseSchedule : nurseSchedules) {
                    if (nurseID.equals(nurseSchedule.getNurseID())) {
                        findSchedules.add(nurseSchedule);
                    }
                }
                Collections.sort(findSchedules);
                System.out.println(nurseID);
                for (NurseSchedule findSchedule : findSchedules) {
                    System.out.println("\t" + findSchedule.toFind());
                }
            }
        }
    }

    public void deleteSchedule(List<NurseSchedule> nurseSchedules, String[] details) {
        int i = 0;
        while (i < nurseSchedules.size()) {
            if ((nurseSchedules.get(i).getNurseID()).equals(details[0])
                    && nurseSchedules.get(i).getDatetime().equals(details[1])) {
                System.out.println("Trip to " + nurseSchedules.get(i).getPatientID() +
                        " on " + nurseSchedules.get(i).getFormattedDatetime() + " has been cancelled!");
                nurseSchedules.remove(i);
                break;
            }
            i++;
        }
    }
}
