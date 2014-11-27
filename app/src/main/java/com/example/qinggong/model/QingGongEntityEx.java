package com.example.qinggong.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ZJGJK03 on 2014/11/26.
 */
public class QingGongEntityEx {
    HashMap<String,List<String>> months=new HashMap<String, List<String>>();

    public QingGongEntityEx(HashMap<String, List<String>> months) {
        this.months = months;
    }

    public QingGongEntityEx() {
    }

    public HashMap<String, List<String>> getMonths() {
        return months;
    }

    public void setMonths(HashMap<String, List<String>> months) {
        this.months = months;
    }
}
