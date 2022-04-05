package com.psp.note_app.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String getCurrDate() {
        try {
            return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        }
        catch (Exception e) {
            return "";
        }
    }

    public static String getCurrTime() {
        try {
            return new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        }
        catch (Exception e) {
            return "";
        }
    }
}
