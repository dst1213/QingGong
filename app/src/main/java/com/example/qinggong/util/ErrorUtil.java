package com.example.qinggong.util;

/**
 * Created by ZJGJK03 on 2014/11/26.
 */
public class ErrorUtil {
    boolean isError=false,b_isAgo,b_isEnough,b_hasMonth;

    public ErrorUtil() {
        isError = false;
        b_isAgo=false;
        b_hasMonth=false;
        b_isEnough=false;
    }

    public boolean HasError() {
        return isError;
    }

    public void set_IsAgo() {
        b_isAgo = true;
        isError=true;
    }

    public void set_NotEnough() {
        b_isEnough = true;
        isError=true;
    }

    public void set_NotHasMonth() {
        b_hasMonth = true;
        isError=true;
    }

    final String isAgo="不能设置以前的日期，请选择以后的时间",isEnough="没有足够的时间怀孕，请选择更长的时间",hasMonth="没有合适的月份，请选择其它年份或性别试试";
    public String getError() {
        String errorMsg = "";
        if (isError) {
            if (b_isAgo)
                errorMsg = isAgo;
            if (b_isEnough)
                errorMsg = isEnough;
            if (b_hasMonth)
                errorMsg = hasMonth;
        } else
            errorMsg = "OK";
        return errorMsg;
    }
}
