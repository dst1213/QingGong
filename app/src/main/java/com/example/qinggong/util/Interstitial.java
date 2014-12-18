package com.example.qinggong.util;

import android.app.Activity;
import android.view.View;

import com.adsmogo.adapters.AdsMogoCustomEventPlatformEnum;
import com.adsmogo.interstitial.AdsMogoInterstitialListener;
import com.adsmogo.interstitial.AdsMogoInterstitialManager;
import com.example.qinggong.R;

/**
 * Created by ZJGJK03 on 2014/12/18.
 */
public class Interstitial
{
    String mogoID="";
    Activity activity;
    /**
     * 是否开启去广告
     */
    boolean hideAd=false;
    /**
     * 去广告的状态
     */
    boolean isHideAd=false;

    /**
     * 加载广告错误时出现错误信息
     * @param activity
     */
    String error="";

    public String getError() {
        return error;
    }

    public Interstitial(Activity activity) {
        this.activity = activity;
        mogoID=this.activity.getResources().getString(R.string.MogoID);
    }

    public Interstitial( Activity activity,boolean hideAd) {
        this.hideAd = hideAd;
        //如果开启了去广告，则判断去广告的状态
        if(this.hideAd)
        {
            isHideAd=HideAdUtil.isHideAd();
        }
        this.activity = activity;
        mogoID=this.activity.getResources().getString(R.string.MogoID);
    }

    /**
     * 初始化广告
     */
    public void initInterstitial() {

        if(checkHideAd())
            return;
        if (activity == null) {
            LogUtil.d("QingGong", "Activity为空，无法初始化插屏广告");
            return;
        }
        /**
         * 初始化全插屏的mogoID(全插屏全局有效)
         */
        AdsMogoInterstitialManager.setDefaultInitAppKey(mogoID);
        /**
         * 设置全插屏展示的activity
         */
        AdsMogoInterstitialManager.setInitActivity(activity);
        /**
         * 初始化对象
         */
        AdsMogoInterstitialManager.shareInstance().initDefaultInterstitial();
        /**
         * 获取全插屏对象并设置监听
         * *********************
         * 获取全插屏对象两种方式：
         * 方式一：AdsMogoInterstitialManager.shareInstance().adsMogoInterstitialByAppKey(mogoID) ，指定mogoID的全插屏对象
         * 方式二：AdsMogoInterstitialManager.shareInstance().defaultInterstitial() ，默认的全插屏对象，mogoID为AdsMogoInterstitialManager.setDefaultInitAppKey(mogoID)设置的值
         * demo中采用第二种方式
         * *********************
         */
        //AdsMogoInterstitialManager.shareInstance().adsMogoInterstitialByAppKey(mogoID).setAdsMogoInterstitialListener(adsmogoFullListener);
        AdsMogoInterstitialManager.shareInstance().defaultInterstitial().setAdsMogoInterstitialListener(adsMogoInterstitialListener);
    }

    /**
     * 开启去广告的功能。前提是必须在初始化时，请hideAd参数设置为真，才会开启去广告的功能，否则不起作用
     * @return
     */
    public boolean hideAd()
    {
        if(!hideAd)
            return false;
        return HideAdUtil.hideAd();
    }

    /**
     * 检查去广告的状态，前提是开启去广告，不然一直假
     * @return
     */
    public boolean checkHideAd()
    {
        boolean ok=false;
        if (hideAd) {
            if (isHideAd) {
                //如果去广告为真，则直接返回
                ok=true;
            }
        }
        return ok;
    }
    /**
     * AdsMogo的全屏/插屏广告的回调
     */
    AdsMogoInterstitialListener adsMogoInterstitialListener=new AdsMogoInterstitialListener() {
        @Override
        public void onInitFinish() {
            showInterstitial();
        }

        @Override
        public void onShowInterstitialScreen(String s) {

        }

        @Override
        public void onInterstitialClickAd(String s) {

        }

        @Override
        public void onInterstitialRealClickAd(String s) {

        }

        @Override
        public void onInterstitialCloseAd(boolean b) {

        }

        @Override
        public boolean onInterstitialClickCloseButton() {
            return false;
        }

        @Override
        public boolean onInterstitialStaleDated(String s) {
            return false;
        }

        @Override
        public View onInterstitialGetView() {
            return null;
        }

        @Override
        public Class getCustomEvemtPlatformAdapterClass(AdsMogoCustomEventPlatformEnum adsMogoCustomEventPlatformEnum) {
            return null;
        }
    };
    public void showInterstitial()
    {
        if(checkHideAd())
            return;
        if(AdsMogoInterstitialManager.shareInstance().containDefaultInterstitia())
        {
            /**
             * 展示全插屏
             * 参数解释：是否等待全插屏广告的展示，true表示等待，false不等待
             * 等待的逻辑：检查是否有全插屏缓存，若有则直接展示，若没有则等待请求到广告后立即展示(注意：在请求广告期间，没有调用interstitialCancel()取消全插屏等待方法时会展示，反则不会展示)。
             * 不等待逻辑：检查是否有全插屏缓存，若有则直接展示，若没有则不等待。
             */
            AdsMogoInterstitialManager.shareInstance().defaultInterstitial().interstitialShow(true);
        }
    }
    /**
     *退出展示时机
     *如果您之前进入了展示时机,并且isWait参数设置为YES,那么在需要取消等待广告展示的
     *时候调用方法interstitialCancel();来通知SDK
     */
    public void cancelShow() {
        if(checkHideAd())
            return;
        if (AdsMogoInterstitialManager.shareInstance().containDefaultInterstitia()) {
            /**
             * 取消全插屏等待展示
             * 解释：把等待请求到广告后展示的状态取消。
             * 场景：调用interstitialShow(true)展示广告，为等待状态，若目前还没有全插屏广告展示出来，调用interstitialCancel()方法后取消等待状态，即不再等待请求到广告后立即展示。
             */
            AdsMogoInterstitialManager.shareInstance().defaultInterstitial().interstitialCancel();
        }
    }

    /**
     10 / 16
     * 改变当前栈顶的Activity对象
     * 如果当前栈顶Activit发生变化，如果未调用该方法改变Activit对象，
     * 有可能会导致广告无法展示或者崩溃
     */
    public void changeCurrentActivity(){
        if(checkHideAd())
            return;
        if (AdsMogoInterstitialManager.shareInstance().containDefaultInterstitia())
        {
            AdsMogoInterstitialManager.shareInstance().defaultInterstitial().changeCurrentActivity(activity);
            AdsMogoInterstitialManager.shareInstance().defaultInterstitial().setAdsMogoInterstitialListener(adsMogoInterstitialListener);
        }
    }
    /**
     * remove销毁全插屏对象
     */
    public void removeInterstitial(){
        if(checkHideAd())
            return;
        AdsMogoInterstitialManager.shareInstance().removeDefaultInterstitialInstance();
    }
}
