package com.example.qinggong.util;

import java.util.TreeMap;

/**
 * Created by ZJGJK03 on 2014/11/26.
 */
public class MonthUtil {
    public static String getMonth(int month) {
        String monthName = "";

        if (month == 1) {
            monthName = "one";
        } else if (month == 2) {

            monthName = "two";
        } else if (month == 3) {
            monthName = "three";
        } else if (month == 4) {
            monthName = "four";
        } else if (month == 5) {
            monthName = "five";
        } else if (month == 6) {
            monthName = "six";
        } else if (month == 7) {
            monthName = "seven";
        } else if (month == 8) {
            monthName = "eight";
        } else if (month == 9) {
            monthName = "nine";
        } else if (month == 10) {
            monthName = "ten";
        } else if (month == 11) {
            monthName = "eleven";
        } else if (month == 12) {
            monthName = "twelve";
        }
        return monthName;
    }
    public static String getMonth(String monthName) {
        String month = "1";
        if (monthName == "one") {
            month = "1";
        } else if (monthName == "two") {

            month = "2";
        } else if (monthName == "three") {
            month = "3";
        } else if (monthName == "four") {
            month = "4";
        } else if (monthName == "five") {
            month = "5";
        } else if (monthName == "six") {
            month = "6";
        } else if (monthName == "seven") {
            month = "7";
        } else if (monthName == "eight") {
            month = "8";
        } else if (monthName == "nine") {
            month = "9";
        } else if (monthName == "ten") {
            month = "10";
        } else if (monthName == "eleven") {
            month = "11";
        } else if (monthName == "twelve") {
            month = "12";
        }
        return month;
    }
}
