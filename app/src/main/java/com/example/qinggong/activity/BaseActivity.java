package com.example.qinggong.activity;

/**
 * Created by ZJGJK03 on 2014/11/24.
 */

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.qinggong.util.LogUtil;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//不显示标题栏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//不允许横竖屏切换
        ActivityCollector.addActivity(this);
        LogUtil.d("BaseActivity", getClass().getSimpleName());//打印出当前运行的Activity的名字
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /**void changeFronts(ViewGroup root)
    {
        Typeface typeface=Typeface.createFromAsset(this.getAssets(),"fonts/A-OTF-OutaiKaiStd-Light.otf");
        for(int i=0;i<root.getChildCount();i++)
        {
            View view=root.getChildAt(i);
            if(view instanceof TextView)
            {
                ((TextView)view).setTypeface(typeface);
            }
            else if(view instanceof Button)
            {
                ((Button)view).setTypeface(typeface);
            }
            else if(view instanceof ViewGroup)
            {
                changeFronts((ViewGroup)view);
            }
        }
    }
     */
}
