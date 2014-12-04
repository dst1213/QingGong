package com.example.qinggong;

import android.test.InstrumentationTestCase;

import com.example.qinggong.util.ErrorUtil;
import com.example.qinggong.util.LogUtil;
import com.example.qinggong.util.PeriodUtil;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ZJGJK03 on 2014/11/27.
 */
public class PeriodUtilTest extends InstrumentationTestCase {

    public void testGetIntYear()
    {
        PeriodUtil periodUtil=new PeriodUtil(2014);
        assertEquals(2014,periodUtil.getIntYear("2014"));
        assertEquals(-1,periodUtil.getIntYear("2014a"));
        assertEquals(0,periodUtil.getIntYear("0000"));
    }

    public void testIsAgo() throws Exception
    {
        PeriodUtil periodUtil=new PeriodUtil(2014);
        assertEquals(false,periodUtil.isAgo());
        periodUtil=new PeriodUtil(2013);
        assertEquals(true,periodUtil.isAgo());
        periodUtil=new PeriodUtil(2015);
        assertEquals(false,periodUtil.isAgo());
    }
    public void testTimeEnough() throws Exception{
        PeriodUtil periodUtil=new PeriodUtil(2014);
        assertEquals(false,periodUtil.timeEnough());
        assertEquals(2014,periodUtil.getCurrentYear());
        assertEquals(12,periodUtil.getCurrentMonth());
        periodUtil=new PeriodUtil(2015);
        assertEquals(true, periodUtil.timeEnough());
        periodUtil=new PeriodUtil(2020);
        assertEquals(true,periodUtil.timeEnough());
        periodUtil=new PeriodUtil(2013);
        assertEquals(false,periodUtil.timeEnough());
    }

    public void testCurrentYear() throws Exception
    {
        PeriodUtil periodUtil=new PeriodUtil(2014);
        List<String> month=periodUtil.currentYear();
        assertEquals(0,month.size());
        assertEquals(new ArrayList<String>(),month);
        assertEquals(true, periodUtil.errorUtil.HasError());
        assertEquals("没有足够的时间怀孕，请选择更长的时间",periodUtil.errorUtil.getError());
    }

    public void testNextYear() throws Exception
    {
        PeriodUtil periodUtil=new PeriodUtil(2015);
        TreeMap<String, List<String>> months = new TreeMap<String, List<String>>();
        List<String> month=new ArrayList<String>();
        months=periodUtil.nextYear();
        assertEquals(2,months.size());
        Iterator<String> key=months.keySet().iterator();
        while (key.hasNext())
        {
            month.clear();
            String skey=key.next();
            month=months.get(skey);
            //LogUtil.d("QingGong",skey);
            //LogUtil.d("QingGong",month.size()+"");
            for (int i=0;i<month.size();i++)
            {
                //LogUtil.d("QingGong",month.get(i));
            }
        }

        periodUtil=new PeriodUtil(2014);
        months = new TreeMap<String, List<String>>();
        month=new ArrayList<String>();
        months=periodUtil.nextYear();
        //LogUtil.d("QingGong",months.size()+"");
        assertEquals(0,months.size());

        periodUtil=new PeriodUtil(2013);
        months = new TreeMap<String, List<String>>();
        month=new ArrayList<String>();
        months=periodUtil.nextYear();
        assertEquals(0,months.size());

        periodUtil=new PeriodUtil(2017);
        months = new TreeMap<String, List<String>>();
        month=new ArrayList<String>();
        months=periodUtil.nextYear();
        assertEquals(0,months.size());
    }

    public void testLastYear() throws Exception
    {
        //测试用例2015年
        PeriodUtil periodUtil=new PeriodUtil(2015);
        TreeMap<String, List<String>> months = new TreeMap<String, List<String>>();
        List<String> month=new ArrayList<String>();
        months=periodUtil.lastYear();
        LogUtil.d("QingGong",months.size()+"");
        assertEquals(0,months.size());

        //测试用例2013年
        periodUtil=new PeriodUtil(2013);
        months = new TreeMap<String, List<String>>();
        month=new ArrayList<String>();
        months=periodUtil.lastYear();
        LogUtil.d("QingGong",months.size()+"");
        assertEquals(0,months.size());


        //测试用例2016年
        periodUtil=new PeriodUtil(2016);
        months = new TreeMap<String, List<String>>();
        month=new ArrayList<String>();
        months=periodUtil.lastYear();
        LogUtil.d("QingGong",months.size()+"");
        assertEquals(2,months.size());
        Iterator<String> key=months.keySet().iterator();
        key=months.keySet().iterator();
        if(key.hasNext())
        {
            String skey=key.next();
            assertEquals("2015",skey);
            month=months.get(skey);
            LogUtil.d("QingGong",skey);
            LogUtil.d("QingGong",month.size()+"");
            assertEquals(9,month.size());
            assertEquals("four",month.get(0));
            assertEquals("five",month.get(1));
            assertEquals("six",month.get(2));
            assertEquals("seven",month.get(3));
            assertEquals("eight",month.get(4));
            assertEquals("nine",month.get(5));
            assertEquals("ten",month.get(6));
            assertEquals("eleven",month.get(7));
            assertEquals("twelve",month.get(8));
        }

        if(key.hasNext())
        {
            month.clear();
            String skey=key.next();
            assertEquals("2016",skey);
            month=months.get(skey);
            LogUtil.d("QingGong",skey);
            LogUtil.d("QingGong",month.size()+"");
            assertEquals(3,month.size());
            assertEquals("one",month.get(0));
            assertEquals("two",month.get(1));
            assertEquals("three",month.get(2));
        }
    }

    public void testGetMonth() throws Exception
    {

        PeriodUtil periodUtil=new PeriodUtil(2012);
        assertEquals(2014,periodUtil.getCurrentYear());
        TreeMap<String, List<String>> months=new TreeMap<String, List<String>>();
        months=periodUtil.getMonth();
        assertEquals(0,months.size());
        assertEquals(true,periodUtil.errorUtil.HasError());
        assertEquals("不能设置以前的日期，请选择以后的时间",periodUtil.errorUtil.getError());


        periodUtil=new PeriodUtil(2014);
        periodUtil.setCurrentMonth(1);//这里设置月份为1月份，主要用来测试
        months.clear();
        months=periodUtil.getMonth();
        assertEquals(1,months.size());
        assertEquals(false,periodUtil.errorUtil.HasError());
        assertEquals("OK",periodUtil.errorUtil.getError());
        Iterator<String> key=months.keySet().iterator();
        if(key.hasNext())
        {
            String str_key=key.next();
            List<String> month=months.get(str_key);
            assertEquals("2014",str_key);
            assertEquals(3,month.size());
            assertEquals("one",month.get(0));
            assertEquals("two",month.get(1));
            assertEquals("three",month.get(2));
        }
        //assertEquals(0,months.size());
        //assertEquals(true,periodUtil.errorUtil.HasError());
        //assertEquals("没有足够的时间怀孕，请选择更长的时间",periodUtil.errorUtil.getError());


        periodUtil=new PeriodUtil(2015);
        months.clear();
        months=periodUtil.getMonth();
        assertEquals(2,months.size());
        assertEquals(false,periodUtil.errorUtil.HasError());
        assertEquals("OK",periodUtil.errorUtil.getError());

        //Iterator<String> key=months.keySet().iterator();
        key=months.keySet().iterator();
        if(key.hasNext())
        {
            String str_key=key.next();
            List<String> month=months.get(str_key);
            assertEquals("2014",str_key);
            assertEquals(1,month.size());
            //assertEquals("eleven",month.get(0));
            assertEquals("twelve",month.get(0));
        }
        if(key.hasNext())
        {
            String str_key=key.next();
            List<String> month=months.get(str_key);
            assertEquals("2015",str_key);
            assertEquals(3,month.size());
            assertEquals("one",month.get(0));
            assertEquals("two",month.get(1));
            assertEquals("three",month.get(2));
        }

        periodUtil=new PeriodUtil(2017);
        months.clear();
        months=periodUtil.getMonth();
        assertEquals(2,months.size());
        assertEquals(false,periodUtil.errorUtil.HasError());
        assertEquals("OK",periodUtil.errorUtil.getError());
        key=months.keySet().iterator();
        if(key.hasNext())
        {
            String str_key=key.next();
            List<String> month=months.get(str_key);
            month=months.get(str_key);
            assertEquals("2016",str_key);
            assertEquals(9,month.size());
            assertEquals("four",month.get(0));
            assertEquals("five",month.get(1));
            assertEquals("six",month.get(2));
            assertEquals("seven",month.get(3));
            assertEquals("eight",month.get(4));
            assertEquals("nine",month.get(5));
            assertEquals("ten",month.get(6));
            assertEquals("eleven",month.get(7));
            assertEquals("twelve",month.get(8));
        }

        if(key.hasNext())
        {
            String str_key=key.next();
            List<String> month=months.get(str_key);
            month=months.get(str_key);
            assertEquals("2017",str_key);
            assertEquals(3,month.size());
            assertEquals("one",month.get(0));
            assertEquals("two",month.get(1));
            assertEquals("three",month.get(2));
        }
    }
}
