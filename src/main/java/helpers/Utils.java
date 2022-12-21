package helpers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getSystemTimeStamp(){
        String currentSystemTime;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        currentSystemTime = timeFormat.format(timestamp);
        return currentSystemTime;
    }

    public static long convertToEpoc(Timestamp timestamp) throws ParseException{
        String epoc;

        Date date = timeFormat.parse(String.valueOf(timestamp));
        long epoch = date.getTime();

        return epoch;
    }



}
