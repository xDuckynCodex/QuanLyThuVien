package quanlythuvien.utils;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFomatterUtil{
    private static final String datePattern = "dd/MM/yyyy";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
    public static String valueToString(Date date) {
        return dateFormat.format(date);
    }

    public static Date stringToValue(String string) {
        try{
            return dateFormat.parse(string);
        }
        catch(Exception e){
           
        }
        return null;
    }
}
