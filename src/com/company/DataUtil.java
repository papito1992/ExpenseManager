package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
    public static final String[] MONTHS={"January","February","March","April","May","June", "July","August", "September","October","November","December"};
    public static Date stringToDate(String dateAsString){

        try {
          SimpleDateFormat df=new SimpleDateFormat("yyyy.mm.dd");
            return df.parse(dateAsString);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public static String dateToString(Date date) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy.mm.dd");
        return df.format(date);


    }
    public static String getYearAndMonth(Date date){
        SimpleDateFormat df=new SimpleDateFormat("yyyy/mm"); //EX. 2016.01;2016.2 so on...
        return df.format(date);
    }
    public static String getMonthName(Integer monthNo){
        return MONTHS[monthNo-1];
    }
}
