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

        int indexof=months_Num.indexOf(month+"");
        if(indexof>=0 && indexof<=11)
        {
            monthName=months_En.get(indexof);
        }

        return monthName;
    }
    public static String getMonthNum(String monthName) {
        String month = "";
        int indexof=months_En.indexOf(monthName);
        if(indexof>=0 && indexof<=11)
        {
            month=months_Num.get(indexof);
        }
        return month;
    }
    public static int getAge(String age)
    {
        String tmp=age.substring(0,age.length()-1);
        int iAge=Integer.parseInt(tmp);
        LogUtil.d("QingGong",iAge+"");
        return iAge;
    }
    public static String getMonth(String month)
    {
        String tmp=month.substring(0,month.length()-1);
        LogUtil.d("QingGong",tmp);
        return tmp;
    }
    public static int getYear(String year)
    {
        String tmp=year.substring(0,year.length()-1);
        int iYear=Integer.parseInt(tmp);
        LogUtil.d("QingGong",iYear+"");
        return iYear;
    }
    public static String getSex(String sex)
    {
        String tmp=sex.substring(0,sex.length()-1);
        LogUtil.d("QingGong",tmp);
        return tmp;
    }
}
