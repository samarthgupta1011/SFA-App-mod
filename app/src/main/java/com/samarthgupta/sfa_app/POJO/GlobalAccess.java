package com.samarthgupta.sfa_app.POJO;

import com.samarthgupta.sfa_app.POJO.WT_JobTicket.JobTicket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by samarthgupta on 03/10/17.
 */

//USE
public class GlobalAccess {

    public static JobTicket jobTicket;
    public static int recordsPerPage = 5;
//        public static String baseUrl = "http://28b8c12a.ngrok.io";
    public static String baseUrl = "https://sfatest.herokuapp.com";

    public static String convertDateFromUTC(String date, String format){

        date = date.substring(0, date.length()-1);
        SimpleDateFormat formatOnBackend = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.UK);

        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
        sdf.setTimeZone(TimeZone.getTimeZone("ISO"));

        try {
            //Parse string to date object
            Date dateObject = formatOnBackend.parse(date);

            //1 hr difference compensate
            dateObject.setTime(dateObject.getTime() - (60 * 60 * 1000));
            date = sdf.format(dateObject);

            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return date;
    }
}
