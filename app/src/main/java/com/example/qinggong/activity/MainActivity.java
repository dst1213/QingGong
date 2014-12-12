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

import com.adsmogo.adapters.AdsMogoCustomEventPlatformEnum;
import com.adsmogo.adview.AdsMogoLayout;
import com.adsmogo.controller.listener.AdsMogoListener;
import com.adsmogo.util.AdsMogoLayoutPosition;
import com.adsmogo.util.AdsMogoSize;
import com.example.qinggong.R;
import com.example.qinggong.util.CustomAlertDialog;
import com.example.qinggong.util.CustomAlertDialogCallbackListener;

/**
 * Created by ZJGJK03 on 2014/12/3.
 */
public class MainActivity extends BaseActivity {

    Button btn_done, btn_ready,btn_detail;
    AdsMogoLayout adsMogoLayoutCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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

        btn_done = (Button) findViewById(R.id.main_btn_done);
        btn_ready = (Button) findViewById(R.id.main_btn_ready);
        btn_detail=(Button)findViewById(R.id.main_btn_detail);
        if (btn_done != null)
            btn_done.setOnClickListener(listener);
        if (btn_ready != null)
            btn_ready.setOnClickListener(listener);
        if(btn_detail!=null)
            btn_detail.setOnClickListener(listener);
    }

    @Override
    protected void onDestroy() {
        if (adsMogoLayoutCode != null)
            adsMogoLayoutCode.clearThread();
        AdsMogoLayout.clear();
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
