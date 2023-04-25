package com.labkoding.product.ewallet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static Date stringToDate(String str, String format) throws ParseException {
        Date date1=new SimpleDateFormat(format).parse(str);
        return date1;
    }
}
