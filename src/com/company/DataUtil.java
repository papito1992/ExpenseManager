package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
    public static Date stringToDate(String dateAsString){

        try {
          SimpleDateFormat df=new SimpleDateFormat("dd/mm/yyyy");
            return df.parse(dateAsString);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public static String dateToString(Date date) {
        SimpleDateFormat df=new SimpleDateFormat("dd//MM/yyyy");
        return df.format(date);


    }
}
