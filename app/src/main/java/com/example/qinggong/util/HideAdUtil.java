package com.example.qinggong.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.qinggong.model.MyApplication;

/**
 * Created by ZJGJK03 on 2014/12/17.
 */
public class HideAdUtil {

    static SharedPreferences.Editor editor;
    static SharedPreferences preferences;
    public HideAdUtil() {
    }

    /**
     * 去掉广告的代码，把去掉广告的标志位存储起来
     * @return 如果成功，返回真
     */
    public static boolean hideAd() {
        boolean ok = false;
        try {
            editor = MyApplication.getContext().getSharedPreferences("Ad", Context.MODE_PRIVATE).edit();
            if (editor != null) {
                editor.putBoolean("hide", true);
                editor.commit();
                ok = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    /**
     * 是否去掉过广告
     * @return 如果为真，则除去过广告，广告不再显示
     */
    public static boolean isHideAd()
    {
        boolean ok=false;
        try
        {
            preferences=MyApplication.getContext().getSharedPreferences("Ad",Context.MODE_PRIVATE);
            ok=preferences.getBoolean("hide",false);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  ok;
    }
}
