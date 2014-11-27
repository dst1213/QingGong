package com.example.qinggong;

import android.test.InstrumentationTestCase;

import com.example.qinggong.util.MonthUtil;

/**
 * Created by ZJGJK03 on 2014/11/27.
 */
public class MonthUtilTest extends InstrumentationTestCase {
    public void testGetMonth() throws Exception
    {
        assertEquals("one", MonthUtil.getMonth(1));
        assertEquals("two", MonthUtil.getMonth(2));
        assertEquals("three", MonthUtil.getMonth(3));
        assertEquals("four", MonthUtil.getMonth(4));
        assertEquals("five", MonthUtil.getMonth(5));
        assertEquals("six", MonthUtil.getMonth(6));
        assertEquals("seven", MonthUtil.getMonth(7));
        assertEquals("eight", MonthUtil.getMonth(8));
        assertEquals("nine", MonthUtil.getMonth(9));
        assertEquals("ten", MonthUtil.getMonth(10));
        assertEquals("eleven", MonthUtil.getMonth(11));
        assertEquals("twelve", MonthUtil.getMonth(12));

    }
}
