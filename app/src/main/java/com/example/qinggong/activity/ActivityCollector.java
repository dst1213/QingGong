package com.example.qinggong.activity;

/**
 * Created by ZJGJK03 on 2014/11/24.
 */

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    public static List<Activity> activities=new ArrayList<Activity>();

    /**
     * 添加一个Activity
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (!activities.contains(activity))
            activities.add(activity);
    }

    /**
     *删除一个Activity
     * @param activity
     */
    public static void removeActivity(Activity activity)
    {
        if(activities.contains(activity))
            activities.remove(activity);
    }

    /**
     *结束所有的Activity
     */
    public static void finishAll()
    {
        for (Activity activity:activities)
        {
            if(!activity.isFinishing())
            {
                activity.finish();
            }
        }
    }
}

