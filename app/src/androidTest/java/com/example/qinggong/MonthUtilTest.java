package com.example.qinggong;

import android.test.InstrumentationTestCase;

import com.example.qinggong.util.MonthUtil;

/**
 * Created by ZJGJK03 on 2014/11/27.
 */
public class MonthUtilTest extends InstrumentationTestCase {
    public void testGetMonthEn() throws Exception
    {
        assertEquals("one", MonthUtil.getMonthEn(1));
        assertEquals("two", MonthUtil.getMonthEn(2));
        assertEquals("three", MonthUtil.getMonthEn(3));
        assertEquals("four", MonthUtil.getMonthEn(4));
        assertEquals("five", MonthUtil.getMonthEn(5));
        assertEquals("six", MonthUtil.getMonthEn(6));
        assertEquals("seven", MonthUtil.getMonthEn(7));
        assertEquals("eight", MonthUtil.getMonthEn(8));
        assertEquals("nine", MonthUtil.getMonthEn(9));
        assertEquals("ten", MonthUtil.getMonthEn(10));
        assertEquals("eleven", MonthUtil.getMonthEn(11));
        assertEquals("twelve", MonthUtil.getMonthEn(12));
        assertEquals("",MonthUtil.getMonthEn(142));
        assertEquals("",MonthUtil.getMonthEn(0));

    }

    public void testGetMonthNumn() throws Exception
    {
        assertEquals("1", MonthUtil.getMonthNum("one"));
        assertEquals("2", MonthUtil.getMonthNum("two"));
        assertEquals("3", MonthUtil.getMonthNum("three"));
        assertEquals("4", MonthUtil.getMonthNum("four"));
        assertEquals("5", MonthUtil.getMonthNum("five"));
        assertEquals("6", MonthUtil.getMonthNum("six"));
        assertEquals("7", MonthUtil.getMonthNum("seven"));
        assertEquals("8", MonthUtil.getMonthNum("eight"));
        assertEquals("9", MonthUtil.getMonthNum("nine"));
        assertEquals("10", MonthUtil.getMonthNum("ten"));
        assertEquals("11", MonthUtil.getMonthNum("eleven"));
        assertEquals("12", MonthUtil.getMonthNum("twelve"));
        assertEquals("",MonthUtil.getMonthNum("1122"));
        assertEquals("",MonthUtil.getMonthNum("adf"));

    }

}
