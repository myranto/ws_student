package com.my.ws_student.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Fonction {

    public Timestamp transform(String dateString) throws Exception
    {
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy", new Locale("fr"));
        Date parsedDate = format.parse(dateString);
        Timestamp timestamp = new Timestamp(parsedDate.getTime());
        return timestamp;
    }
}