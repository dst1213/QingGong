package com.example.qinggong;

import android.test.InstrumentationTestCase;

import com.example.qinggong.db.QingGongDB;

import java.util.List;

/**
 * Created by ZJGJK03 on 2014/11/28.
 */
public class QingGongDBTest extends InstrumentationTestCase {

    public void testGetInstance()
    {
        QingGongDB qingGongDB=QingGongDB.getInstance();
        assertEquals("class com.example.qinggong.db.QingGongDB",qingGongDB.getClass().toString());
        //qingGongDB=QingGongDB.getInstance();
        assertEquals(qingGongDB,QingGongDB.getInstance());
    }

    public void testGetQingGong()
    {
        QingGongDB qingGongDB=QingGongDB.getInstance();
        assertEquals("男",qingGongDB.getQingGong("1",19));
        assertEquals("男",qingGongDB.getQingGong("one",19));
        assertEquals("女",qingGongDB.getQingGong("6",35));
        assertEquals("女",qingGongDB.getQingGong("six",35));
        assertEquals("",qingGongDB.getQingGong("six",90));
        assertEquals("",qingGongDB.getQingGong("123",10));
        assertEquals("男",qingGongDB.getQingGong("11",26));
        assertEquals("男",qingGongDB.getQingGong("eleven",26));
    }
    public void testGetMonths()
    {
        QingGongDB qingGongDB=QingGongDB.getInstance();
        List<String> months=qingGongDB.getMonths(2014,24,"男");
        assertEquals(0,months.size());
        assertEquals("没有足够的时间怀孕，请选择更长的时间",qingGongDB.periodUtil.errorUtil.getError());
        months=qingGongDB.getMonths(2015,29,"男");
        assertEquals(1,months.size());
        assertEquals("2015年-2月,",months.get(0));
        months=qingGongDB.getMonths(2013,24,"男");
        assertEquals(0,months.size());
        assertEquals("不能设置以前的日期，请选择以后的时间",qingGongDB.periodUtil.errorUtil.getError());
        months=qingGongDB.getMonths(2017,24,"男");
        assertEquals(8,months.size());
        assertEquals("2016年-4月,",months.get(0));
        assertEquals("2016年-5月,",months.get(1));
        assertEquals("2016年-7月,",months.get(2));
        assertEquals("2016年-9月,",months.get(3));
        assertEquals("2016年-10月,",months.get(4));
        assertEquals("2016年-11月,",months.get(5));
        assertEquals("2017年-1月,",months.get(6));
        assertEquals("2017年-3月,",months.get(7));

        months=qingGongDB.getMonths(2016,32,"男");
        assertEquals(3,months.size());
        assertEquals("2015年-12月,",months.get(0));
        assertEquals("2016年-1月,",months.get(1));
        assertEquals("2016年-3月,",months.get(2));

        months=qingGongDB.getMonths(2016,42,"男");
        assertEquals(5,months.size());

        months=qingGongDB.getMonths(2016,43,"男");
        assertEquals(0,months.size());

        months=qingGongDB.getMonths(2016,41,"男");
        assertEquals(7,months.size());

        months=qingGongDB.getMonths(2016,10,"男");
        assertEquals(0,months.size());

        months=qingGongDB.getMonths(2018,13,"男");
        assertEquals(0,months.size());

        months=qingGongDB.getMonths(2018,19,"男");
        assertEquals(2,months.size());

        months=qingGongDB.getMonths(2017,20,"男");
        assertEquals(6,months.size());

        months=qingGongDB.getMonths(2015,19,"男");
        assertEquals(2,months.size());

        months=qingGongDB.getMonths(2014,19,"男");
        assertEquals(0,months.size());

        months=qingGongDB.getMonths(2014,41,"男");
        assertEquals(0,months.size());


        months=qingGongDB.getMonths(2014,42,"男");
        assertEquals(0,months.size());

        //qingGongDB.periodUtil.setCurrentMonth(1);//这里不起作为。PeriodUtil是每次调用getMonths时初始化，所以在这里设置是不起作为的。
        months=qingGongDB.getMonths(2015,42,"男");
        assertEquals(1,months.size());

    }
}
