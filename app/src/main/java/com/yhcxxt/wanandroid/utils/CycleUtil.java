package com.yhcxxt.wanandroid.utils;


import java.util.Calendar;
import java.util.Date;

/**
 * 判定两个时间在不在同一个周期内（月，年）
 */
public class CycleUtil {


        /**
         * 同一年
         * @param date1
         * @param date2
         * @return
         */
        public static Boolean isSameYear(Date date1, Date date2){
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
            return isSameYear;
        }
        /**
         * 同一月
         * @param date1
         * @param date2
         * @return
         */
        public static Boolean isSameMonth(Date date1,Date date2){
            if(!isSameYear(date1,date2)){
                return false;
            }
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            boolean isSameMonth = cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
            return isSameMonth;
        }


}
