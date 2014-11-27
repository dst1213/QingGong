package com.example.qinggong;

import android.test.InstrumentationTestCase;

import com.example.qinggong.util.ErrorUtil;

/**
 * Created by ZJGJK03 on 2014/11/27.
 */
public class ErrorUtilTest extends InstrumentationTestCase {
    public void testHasError()
    {
        ErrorUtil errorUtil=new ErrorUtil();
        errorUtil.set_IsAgo();
        assertEquals(true,errorUtil.HasError());
        assertEquals("不能设置以前的日期，请选择以后的时间",errorUtil.getError());
        errorUtil=new ErrorUtil();
        errorUtil.set_NotEnough();
        assertEquals(true,errorUtil.HasError());
        assertEquals("没有足够的时间怀孕，请选择更长的时间",errorUtil.getError());
        errorUtil=new ErrorUtil();
        errorUtil.set_NotHasMonth();
        assertEquals(true,errorUtil.HasError());
        assertEquals("没有合适的月份，请选择其它年份或性别试试",errorUtil.getError());
        errorUtil=new ErrorUtil();
        assertEquals("OK",errorUtil.getError());
    }
}
