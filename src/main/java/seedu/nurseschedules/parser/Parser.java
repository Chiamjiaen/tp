package nurseschedules.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Parser {

    /**
     * Gets user input
     *
     * @return User input
     */
    public String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Returns the command of user
     *
     * @param text User input
     * @return First word of user input
     */
    public static String getFirstWord(String text) {
        int index = text.indexOf(' ');

        if (index > -1) {

            return text.substring(0, index).trim();

        } else {

            return text;
        }
    }

    public String[] getDetails(String text) {
        String parts[] = text.split(" ", 0);
        String details[] = new String[3];
        if (getFirstWord(text).equals("add")) {
            details[0] = parts[1];
            details[1] = parts[2];
            details[2] = parts[3];
        } else if (getFirstWord(text).equals("delete")) {
            details[0] = parts[1];
            details[1] = parts[2];
        } else if (getFirstWord(text).equals("list")) {
            details[0] = parts[1];
        }

        return details;
    }

    public String formatDate(String text) throws ParseException {
        String[] details = getDetails(text);
        SimpleDateFormat parser = new SimpleDateFormat("ddMMyyyy");
        Date date = parser.parse(details[2]);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        String formattedDate = formatter.format(date);

        return formattedDate;
    }
}
