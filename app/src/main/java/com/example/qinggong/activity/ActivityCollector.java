package com.example.qinggong.activity;

/**
 * Created by ZJGJK03 on 2014/11/24.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.qinggong.model.MyApplication;

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

    public static void finishAll()
    {
        // TODO Auto-generated method stub
        for (Activity activity:activities)
        {
            if(!activity.isFinishing())
            {
                activity.finish();
            }
        }
    }
    /**
     *结束所有的Activity
     * 这里需要弹出确认框，所以必须传入当然的Context才可以，不然会报错
     */
    public static void finishAll(Context context)
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setTitle("退出");
        dialog.setMessage("是否要真的退出？");
        dialog.setCancelable(true);
        //按下确定按钮
        dialog.setPositiveButton("退出", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                finishAll();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                //不做任务处理
            }
        });
        dialog.show();
    }
}

