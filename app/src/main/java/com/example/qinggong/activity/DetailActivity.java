package com.example.qinggong.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.adsmogo.adapters.AdsMogoCustomEventPlatformEnum;
import com.adsmogo.adview.AdsMogoLayout;
import com.adsmogo.controller.listener.AdsMogoListener;
import com.example.qinggong.R;
import com.example.qinggong.db.QingGongDB;
import com.example.qinggong.util.Banner;
import com.example.qinggong.util.MonthUtil;

import java.sql.Array;
import java.util.List;

/**
 * Created by ZJGJK03 on 2014/12/8.
 */
public class DetailActivity extends BaseActivity {
    Spinner sp_age;
    ListView listView;
    QingGongDB qingGongDB;
    Button btn_return;
    Banner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        /** 代码方式添加广告，如果您使用XML配置方式添加广告，不需要以下代码 **/
        banner=new Banner(DetailActivity.this);
        banner.initBanner();
        /*********************** 代码添加广告结束 ************************/

        qingGongDB=QingGongDB.getInstance();
        listView=(ListView)findViewById(R.id.detail_lv_qinggong);
        btn_return=(Button)findViewById(R.id.detail_btn_return);
        btn_return.setOnClickListener(listener1);
        sp_age=(Spinner)findViewById(R.id.detail_sp_age);
        if(sp_age!=null) {
            initAge();
            sp_age.setOnItemSelectedListener(listener);
        }

    }
    AdapterView.OnItemSelectedListener listener =new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(DetailActivity.this,sp_age.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(DetailActivity.this,android.R.layout.simple_list_item_1,qingGongDB.getMonths(MonthUtil.getAge(sp_age.getSelectedItem().toString())+""));
            listView.setAdapter(adapter);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    View.OnClickListener listener1=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    void initAge() {
        String[] ages = getResources().getStringArray(R.array.ages);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailActivity.this, android.R.layout.simple_spinner_dropdown_item, ages);
        sp_age.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        banner.clearThread();
        super.onDestroy();
    }
}
