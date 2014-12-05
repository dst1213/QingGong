package com.example.qinggong.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.qinggong.R;

/**
 * Created by ZJGJK03 on 2014/12/3.
 */
public class MainActivity extends BaseActivity {

    Button btn_done,btn_ready;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn_done=(Button)findViewById(R.id.main_btn_done);
        btn_ready=(Button)findViewById(R.id.main_btn_ready);
        if(btn_done!=null)
            btn_done.setOnClickListener(listener);
        if(btn_ready!=null)
            btn_ready.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId())
            {
                case R.id.main_btn_done:
                    intent=new Intent(MainActivity.this,DoneActivity.class);
                    startActivity(intent);
                    break;
                case R.id.main_btn_ready:
                    intent=new Intent(MainActivity.this,ReadyActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        ActivityCollector.finishAll(MainActivity.this);
    }
}
