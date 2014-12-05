package com.example.qinggong.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.qinggong.R;
import com.example.qinggong.db.QingGongDB;
import com.example.qinggong.util.LogUtil;

/**
 * Created by ZJGJK03 on 2014/12/5.
 */
public class DoneActivity extends BaseActivity {
    Spinner sp_ages,sp_months;
    Button btn_return,btn_result;
    QingGongDB qingGongDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.done);
        qingGongDB=QingGongDB.getInstance();
        btn_return=(Button)findViewById(R.id.done_btn_return);
        btn_result=(Button)findViewById(R.id.done_btn_result);
        if(btn_return!=null)
            btn_return.setOnClickListener(listener);
        if(btn_result!=null)
            btn_result.setOnClickListener(listener);
        sp_ages=(Spinner)findViewById(R.id.done_sp_ages);
        if(sp_ages!=null)
            initSpinnerAge();
        sp_months=(Spinner)findViewById(R.id.done_sp_months);
        if(sp_months!=null)
            initSpinnerMonth();
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.done_btn_return:
                    finish();
                    break;
                case R.id.done_btn_result:
                    int age=getAge(sp_ages.getSelectedItem().toString());
                    String month=getMonth(sp_months.getSelectedItem().toString());
                    String result=qingGongDB.getQingGong(month,age);
                    new AlertDialog.Builder(DoneActivity.this).setTitle("预测结果是："+result+"孩").setView(getImage(result)).setPositiveButton("确定",null).show();

                    //Toast.makeText(DoneActivity.this,value,Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;

            }
        }
    };
    ImageView getImage(String sex)
    {
//        int tmp=sex.indexOf("男");
//        LogUtil.d("QingGong",tmp+"");
//        tmp=sex.indexOf("女");
//        LogUtil.d("QingGong",tmp+"");
//        LogUtil.d("QingGong","=================");
//        LogUtil.d("QingGong",sex);
        ImageView imageView=new ImageView(DoneActivity.this);
        if(sex.indexOf("男")>=0)
            imageView.setImageResource(R.drawable.boy);
        else if(sex.indexOf("女")>=0)
            imageView.setImageResource(R.drawable.girl);
        else
            imageView.setImageResource(R.drawable.ic_launcher);
        return imageView;
    }
    int getAge(String age)
    {
        String tmp=age.substring(0,age.length()-1);
        int iAge=Integer.parseInt(tmp);
        LogUtil.d("QingGong",iAge+"");
        return iAge;
    }
    String getMonth(String month)
    {
        String tmp=month.substring(0,month.length()-1);
        LogUtil.d("QingGong",tmp);
        return tmp;
    }
    void initSpinnerAge()
    {
        //String[] ages={"19岁","20岁","21岁","22岁","23岁","24岁","25岁","26岁","27岁","28岁","29岁","30岁","31岁","32岁","33岁","34岁","35岁","36岁","37岁","38岁","39岁","40岁","41岁"};
        String[] ages=getResources().getStringArray(R.array.ages);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter.setDropDownViewResource(R.layout.drop_down_item);//这里可以加载一个简单布局
        sp_ages.setAdapter(adapter);
    }
    void initSpinnerMonth()
    {
        //String[] months={"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
        String[] months=getResources().getStringArray(R.array.months);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,months);
        sp_months.setAdapter(adapter);
    }
}
