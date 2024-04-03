package quanlythuvien.utils;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFomatterUtil{
    private static String datePattern = "dd/MM/yyyy";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
    public static String valueToString(Date date) {
        return dateFormat.format(date);
    }

    public static Date stringToValue(String string) throws ParseException {
        return dateFormat.parse(string);
    }
}
