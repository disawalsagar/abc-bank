package com.abc;

import java.time.ZonedDateTime;

public class DateProvider {
    private static DateProvider instance = null;

     //Singleton pattern not properly implemented
     //Added constructor which is private
    private DateProvider() {}

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

   public ZonedDateTime now() {
        //return Calendar.getInstance().getTime();
       //Sagar Changed java.util.date to java.time.*
      return ZonedDateTime.now();
    }
}


