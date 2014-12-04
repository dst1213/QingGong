package com.example.qinggong.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by ZJGJK03 on 2014/11/26.
 */
public class MonthUtil {

    static final List<String> months_En=new ArrayList<String>(){{add("one");add("two");add("three");add("four");add("five");add("six");add("seven");add("eight");add("nine");add("ten");add("eleven");add("twelve");}};
    static final List<String> months_Num=new ArrayList<String>(){{add("1");add("2");add("3");add("4");add("5");add("6");add("7");add("8");add("9");add("10");add("11");add("12");}};

    public static boolean isEnMonth(String month)
    {
        boolean ok=false;
        if(months_En.contains(month))
            ok=true;
        return ok;
    }
    public static boolean isNumMonth(String month)
    {
        boolean ok=false;
        if(months_Num.contains(month))
            ok=true;
        return ok;
    }
    public static String getMonthEn(int month) {
        String monthName = "";

//        if(!(month>0 && month<=12))
//        {
//            return monthName;
//        }
        int indexof=months_Num.indexOf(month+"");
        if(indexof>=0 && indexof<=11)
        {
            monthName=months_En.get(indexof);
        }

//        if (month == 1) {
//            monthName = "one";
//        } else if (month == 2) {
//
//            monthName = "two";
//        } else if (month == 3) {
//            monthName = "three";
//        } else if (month == 4) {
//            monthName = "four";
//        } else if (month == 5) {
//            monthName = "five";
//        } else if (month == 6) {
//            monthName = "six";
//        } else if (month == 7) {
//            monthName = "seven";
//        } else if (month == 8) {
//            monthName = "eight";
//        } else if (month == 9) {
//            monthName = "nine";
//        } else if (month == 10) {
//            monthName = "ten";
//        } else if (month == 11) {
//            monthName = "eleven";
//        } else if (month == 12) {
//            monthName = "twelve";
//        }
        return monthName;
    }
    public static String getMonthNum(String monthName) {
        String month = "";
        int indexof=months_En.indexOf(monthName);
        if(indexof>=0 && indexof<=11)
        {
            month=months_Num.get(indexof);
        }
//        if (monthName == "one") {
//            month = "1";
//        } else if (monthName == "two") {
//
//            month = "2";
//        } else if (monthName == "three") {
//            month = "3";
//        } else if (monthName == "four") {
//            month = "4";
//        } else if (monthName == "five") {
//            month = "5";
//        } else if (monthName == "six") {
//            month = "6";
//        } else if (monthName == "seven") {
//            month = "7";
//        } else if (monthName == "eight") {
//            month = "8";
//        } else if (monthName == "nine") {
//            month = "9";
//        } else if (monthName == "ten") {
//            month = "10";
//        } else if (monthName == "eleven") {
//            month = "11";
//        } else if (monthName == "twelve") {
//            month = "12";
//        }
        return month;
    }
}
