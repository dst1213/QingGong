package com.example.qinggong.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.adsmogo.adapters.AdsMogoCustomEventPlatformEnum;
import com.adsmogo.adview.AdsMogoLayout;
import com.adsmogo.controller.listener.AdsMogoListener;
import com.adsmogo.interstitial.AdsMogoInterstitialListener;
import com.adsmogo.interstitial.AdsMogoInterstitialManager;
import com.adsmogo.util.AdsMogoLayoutPosition;
import com.adsmogo.util.AdsMogoSize;
import com.example.qinggong.R;
import com.example.qinggong.util.CustomAlertDialog;
import com.example.qinggong.util.CustomAlertDialogCallbackListener;

/**
 * Created by ZJGJK03 on 2014/12/3.
 */
public class MainActivity extends BaseActivity {

    Button btn_done, btn_ready,btn_detail,btn_show;
    AdsMogoLayout adsMogoLayoutCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initMogoBanner();

        initMogoInterstitial();

        btn_done = (Button) findViewById(R.id.main_btn_done);
        btn_ready = (Button) findViewById(R.id.main_btn_ready);
        btn_detail=(Button)findViewById(R.id.main_btn_detail);

        if (btn_done != null)
            btn_done.setOnClickListener(listener);
        if (btn_ready != null)
            btn_ready.setOnClickListener(listener);
        if(btn_detail!=null)
            btn_detail.setOnClickListener(listener);
        btn_show=(Button)findViewById(R.id.main_btn_ShowAd);
        if(btn_show!=null)
            btn_show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //判断是否存在默认全插屏对象
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
            });
    }


    void initMogoBanner()
    {
        /** 代码方式添加广告，如果您使用XML配置方式添加广告，不需要以下代码 **/
        //adsMogoLayoutCode = new AdsMogoLayout(this, getResources().getString(R.string.MogoID), AdsMogoLayoutPosition.CENTER_BOTTOM, AdsMogoSize.AdsMoGoBanner,false);
        adsMogoLayoutCode = new AdsMogoLayout(this, getResources().getString(R.string.MogoID));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        // 设置广告出现的位置(悬浮于底部)
        params.bottomMargin = 0;
        adsMogoLayoutCode.setAdsMogoListener(new AdsMogoListener() {
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
        addContentView(adsMogoLayoutCode, params);
        /*********************** 代码添加广告结束 ************************/
    }

    void initMogoInterstitial()
    {
        /**
         * 初始化全插屏的mogoID(全插屏全局有效)
         */
        AdsMogoInterstitialManager.setDefaultInitAppKey(getResources().getString(R.string.MogoID));
        /**
         * 设置全插屏展示的activity
         */
        AdsMogoInterstitialManager.setInitActivity(MainActivity.this);
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
     * AdsMogo的全屏/插屏广告的回调
     */
    AdsMogoInterstitialListener adsMogoInterstitialListener=new AdsMogoInterstitialListener() {
        @Override
        public void onInitFinish() {
            //判断是否存在默认全插屏对象
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
            else
            {
                Toast.makeText(MainActivity.this,"无法显示全屏广告",Toast.LENGTH_SHORT).show();
            }
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

    @Override
    protected void onDestroy() {
        AdsMogoLayout.clear();
        if (adsMogoLayoutCode != null)
            adsMogoLayoutCode.clearThread();
        AdsMogoInterstitialManager.shareInstance().removeDefaultInterstitialInstance();
        super.onDestroy();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.main_btn_done:
                    intent = new Intent(MainActivity.this, DoneActivity.class);
                    startActivity(intent);
                    break;
                case R.id.main_btn_ready:
                    intent = new Intent(MainActivity.this, ReadyActivity.class);
                    startActivity(intent);
                    break;
                case R.id.main_btn_detail:
                    intent=new Intent(MainActivity.this,DetailActivity.class);
                    startActivity(intent);
                default:
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //ActivityCollector.finishAll(MainActivity.this);
        exitApp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (AdsMogoInterstitialManager.shareInstance().containDefaultInterstitia())
        {
            AdsMogoInterstitialManager.shareInstance().defaultInterstitial().changeCurrentActivity(MainActivity.this);
            AdsMogoInterstitialManager.shareInstance().defaultInterstitial().setAdsMogoInterstitialListener(adsMogoInterstitialListener);
        }
    }

    void exitApp() {
        CustomAlertDialog alertDialog=new CustomAlertDialog();
        alertDialog.setCancel(true,getResources().getString(R.string.exit_btn_cancel_text));
        alertDialog.setOk(true,getResources().getString(R.string.exit_btn_ok_text));
        alertDialog.setContent(true,getResources().getString(R.string.exit_tv_content_text));
        alertDialog.setTitleText(getResources().getString(R.string.exit_tv_title_text));
        alertDialog.setCancelable(true);
        alertDialog.showDialog(MainActivity.this,new CustomAlertDialogCallbackListener() {
            @Override
            public void onClickOk() {
                ActivityCollector.finishAll();
            }

            @Override
            public void onClickCancel() {

            }
        });
    }
}
