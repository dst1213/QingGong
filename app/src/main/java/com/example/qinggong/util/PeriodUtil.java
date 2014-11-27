package com.example.qinggong.util;


import android.text.format.Time;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by ZJGJK03 on 2014/11/26.
 */
public class PeriodUtil {

    //String sex;
    int year;
    int currentYear,currentMonth;
    public int getCurrentYear() {
        return currentYear;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    /**
     * 仅为了测试使用
     * @param month
     */
    public void setCurrentMonth(int month)
    {
        currentMonth=month;
    }

    Time t;
    public ErrorUtil errorUtil;

    public int getYear() {
        return year;
    }

    public Time getT() {
        return t;
    }

    public PeriodUtil(String year) {
        this.year=getIntYear(year);
        t=new Time("GMT+8");
        t.setToNow();
        currentYear=t.year;
        currentMonth=t.month+1;
        errorUtil=new ErrorUtil();
    }

    /**
     * 判断从现在到所选的年份，是否有足够的时间
     * @return
     */
    public boolean timeEnough()
    {
        boolean enough;
        if(isAgo())
        {
            errorUtil.set_IsAgo();
            enough=false;
        }
        else if(year==currentYear)
        {
            if ((12 - currentMonth) > 9)
            {
                enough = true;
            }
            else
            {
                errorUtil.set_NotEnough();
                enough = false;
            }
        }
        else
        {
            enough=true;
        }
        return enough;
    }

    /**
     * 获得可以怀孕的月份列表，在此之前需要先调用timeEnough方法，确认有足够的时间后才可以调用此方法
     * 如果返回的是空的话，需要调用ErrorUtil来获得错误内容
     * @return
     */
    public TreeMap<String,List<String>> getMonth() {


        if (!timeEnough())
            return new TreeMap<String, List<String>>();
        TreeMap<String, List<String>> months = new TreeMap<String, List<String>>();

        if(year==currentYear)//处理同一年的日期
        {
            months.put(year+"",currentYear());
        }
        else if(year==currentYear+1)
        {
            months=nextYear();
        }
        else if(year>currentYear+1)
        {
            months=lastYear();
        }
        return months;
    }
    /**
     *如果选择的同一年，返回可以用的月份

     * @return
     */
    public List<String> currentYear() {
        List<String> months = new ArrayList<String>();
        if (year!=currentYear) {
            if(year<currentYear)
                errorUtil.set_IsAgo();
            return months;
        }
        if(!timeEnough())
            return months;
        int currentmonth = currentMonth;
        int availableDate = 12 - 9 - currentmonth;
        for (int i = currentmonth; i <= currentmonth + availableDate; i++) {
            months.add(MonthUtil.getMonth(i));
        }
        return months;
    }

    /**
     *处理当前年份以后的日期
     * @return
     */
    public TreeMap<String, List<String>> nextYear()
    {
        TreeMap<String, List<String>> months = new TreeMap<String, List<String>>();
        if(year!=currentYear+1) {
            return months;
        }
        List<String> month1=new ArrayList<String>();
        for(int i=1;i<=3;i++)
        {
            month1.add(MonthUtil.getMonth(i));
        }
        months.put(year+"",month1);

        List<String> month2=new ArrayList<String>();
        for(int i=currentMonth;i<=12;i++)
        {
            month2.add(MonthUtil.getMonth(i));
        }
        months.put((year-1)+"",month2);
        return months;
    }

    /**
     * 以后的年份，可以以后多年
     * @return
     */
    public TreeMap<String, List<String>> lastYear()
    {
        TreeMap<String, List<String>> months = new TreeMap<String, List<String>>();
        if(year<=currentYear+1) {
            return months;
        }
        List<String> month1=new ArrayList<String>();
        for(int i=1;i<=3;i++)
        {
            month1.add(MonthUtil.getMonth(i));
        }
        months.put(year+"",month1);
        List<String> month2=new ArrayList<String>();
        for(int i=4;i<=12;i++)
        {
            month2.add(MonthUtil.getMonth(i));
        }
        months.put((year-1)+"",month2);

        return months;
    }
    /**
     *是否选择了以前的日期
     * @return
     */
    public boolean isAgo() {
        boolean enough;

        if (year > currentYear || year==currentYear)
            enough = false;
        else
            enough = true;

        return enough;
    }

    /**
     *从字符型转成数字型
     * @param year
     * @return
     */
    public int getIntYear(String year) {
        boolean digital = false;
        for (int i = 0; i < year.length(); i++) {
            if (Character.isDigit(year.charAt(i)))
                digital = true;
            else {
                digital = false;
                break;
            }
        }
        if (digital)
            return Integer.parseInt(year);
        else
            return -1;
    }
}
