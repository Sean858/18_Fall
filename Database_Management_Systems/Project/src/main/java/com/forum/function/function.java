package com.forum.function;

import java.text.SimpleDateFormat;
import java.util.Date;

public class function {
    public static String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        return sdf.format(date);
    }
}
