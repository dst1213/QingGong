package com.example.qinggong.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.adsmogo.adapters.AdsMogoCustomEventPlatformEnum;
import com.adsmogo.adview.AdsMogoLayout;
import com.adsmogo.controller.listener.AdsMogoListener;
import com.example.qinggong.R;

/**
 * Created by ZJGJK03 on 2014/12/18.
 */
public class Banner
{
    String mogoID="";
    Activity activity;
    AdsMogoLayout adsMogoLayout;
    /**
     * 加载广告错误时出现错误信息
     * @param activity
     */
    String error="";

    public String getError() {
        return error;
    }

    public Banner(Activity activity) {
        this.activity = activity;
        mogoID=this.activity.getResources().getString(R.string.MogoID);
    }

    public void initBanner()
    {
        adsMogoLayout = new AdsMogoLayout(activity,mogoID);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        // 设置广告出现的位置(悬浮于底部)
        params.bottomMargin = 0;
        adsMogoLayout.setAdsMogoListener(new AdsMogoListener() {
            @Override
            public void onInitFinish() {

            }

            @Override
            public void onRequestAd(String s) {

            }

            @Override
            public void onRealClickAd() {

            }

            @Override
            public void onReceiveAd(ViewGroup viewGroup, String s) {

            }

            @Override
            public void onFailedReceiveAd() {
                error="加载广告失改";
            }

            @Override
            public void onClickAd(String s) {

            }

            @Override
            public boolean onCloseAd() {
                return false;
            }

            @Override
            public void onCloseMogoDialog() {

            }

            @Override
            public Class getCustomEvemtPlatformAdapterClass(AdsMogoCustomEventPlatformEnum adsMogoCustomEventPlatformEnum) {
                return null;
            }
        });
        params.gravity = Gravity.BOTTOM;
        activity.addContentView(adsMogoLayout, params);

    }

    /**
     * 请理当前线程所产生的广告
     */
    public void clearThread()
    {
        if (adsMogoLayout != null)
            adsMogoLayout.clearThread();
    }

    /**
     * 清理所有广告
     */
    public void clear()
    {
        AdsMogoLayout.clear();
    }
}

