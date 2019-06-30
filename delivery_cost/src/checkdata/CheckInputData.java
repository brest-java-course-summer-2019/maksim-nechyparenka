package checkdata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInputData {

    public CheckInputData() {
    }

    public static boolean checkData(String data) {

        Pattern pattern = Pattern.compile("(\\d*\\.?\\d*)");
        Matcher matcher = pattern.matcher(data);

        if (!matcher.matches()) {

            System.out.println("Your data is not correct! Please try again!");
            return false;

        } else return true;
    }
}
