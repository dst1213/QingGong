package com.example.qinggong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.qinggong.R;

/**
 * Created by ZJGJK03 on 2014/12/3.
 */
public class StartActivity extends BaseActivity {
    Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        btn_start=(Button)findViewById(R.id.start_btn_start);
        if(btn_start!=null)
        {
            btn_start.setOnClickListener(listener);
        }
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(StartActivity.this,MainActivity.class);
            startActivity(intent);
        }
    };
}
