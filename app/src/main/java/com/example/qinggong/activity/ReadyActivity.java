package com.example.qinggong.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.adsmogo.adapters.AdsMogoCustomEventPlatformEnum;
import com.adsmogo.adview.AdsMogoLayout;
import com.adsmogo.controller.listener.AdsMogoListener;
import com.example.qinggong.R;
import com.example.qinggong.db.QingGongDB;
import com.example.qinggong.util.CustomAlertDialog;
import com.example.qinggong.util.CustomAlertDialogCallbackListener;
import com.example.qinggong.util.LogUtil;
import com.example.qinggong.util.MonthUtil;

import java.util.Calendar;
import java.util.List;

/**
 * Created by ZJGJK03 on 2014/12/5.
 */
public class ReadyActivity extends BaseActivity {

    QingGongDB qingGongDB;
    Spinner sp_year, sp_sex, sp_age;
    Button btn_return, btn_result;
    AdsMogoLayout adsMogoLayoutCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready);

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

        qingGongDB = QingGongDB.getInstance();
        btn_return = (Button) findViewById(R.id.ready_btn_return);
        btn_result = (Button) findViewById(R.id.ready_btn_result);
        if (btn_return != null)
            btn_return.setOnClickListener(listener);
        if (btn_result != null)
            btn_result.setOnClickListener(listener);
        sp_year = (Spinner) findViewById(R.id.ready_sp_year);
        if (sp_year != null)
            initYear();
        sp_sex = (Spinner) findViewById(R.id.ready_sp_sex);
        if (sp_sex != null)
            initSex();
        sp_age = (Spinner) findViewById(R.id.ready_sp_age);
        if (sp_age != null)
            initAge();

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ready_btn_return:
                    finish();
                    break;
                case R.id.ready_btn_result:
                    int year = MonthUtil.getYear(sp_year.getSelectedItem().toString());
                    int age = MonthUtil.getAge(sp_age.getSelectedItem().toString());
                    String sex = MonthUtil.getSex(sp_sex.getSelectedItem().toString());
                    //age = getRealAge(year, age);//此句代码其实是多余，会更加上用户弄不清楚
                    //LogUtil.d("QingGong",year+"");
                    //LogUtil.d("QingGong",sex);
                    List<String> value = qingGongDB.getMonths(year, age, sex);
                    String result = "";
                    if (value.size() > 0) {
                        int i = 1;
                        for (String str : value) {
                            result += str;
                            if (i % 2 == 0)
                                result += "\n";
                            i++;
                        }
                        result = "\t\t根据您所提供的数据，使用清宫表预测，得出以下的几个月份怀孕生【" + sex + "孩】的机率比较高，它们分别是：\n" + result;
                    } else
                        result = qingGongDB.periodUtil.errorUtil.getError();
                    //new AlertDialog.Builder(ReadyActivity.this).setTitle("预测结果").setMessage(result).setPositiveButton("确定", null).show();
                    showDialog(result);
                    break;
                default:
                    break;

            }
        }
    };
    void showDialog(String result)
    {
        CustomAlertDialog alertDialog=new CustomAlertDialog();
        alertDialog.setCancel(false,"");
        alertDialog.setOk(true,getResources().getString(R.string.ready_alertdialog_btn_ok_text));
        alertDialog.setContent(true,result);
        alertDialog.setTitleText(getResources().getString(R.string.ready_alertdialog_title_text));
        alertDialog.setCancelable(false);
        alertDialog.showDialog(ReadyActivity.this,null);
    }
    /**
     * 这里只能设置从今年开始往后10年，所以在初始化时，从今年开始，往后推10年
     */
    void initYear() {
        Time time = new Time("GMT+8");
        time.setToNow();
        int iyear = time.year;
        int imonth=time.month+1;
        String[] years = new String[10];
        if (imonth > 3)//3月份以后再怀孕，正常情况下孩子将于下一年出生。
            iyear+=1;
        for (int i=0; i < 10; i++) {
            years[i] = (iyear +i)+ "年";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, years);
        sp_year.setAdapter(adapter);
    }

    void initSex() {
        String[] sexs = new String[]{"男孩", "女孩"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sexs);
        sp_sex.setAdapter(adapter);
    }

    void initAge() {
        String[] ages = getResources().getStringArray(R.array.ages);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ages);
        sp_age.setAdapter(adapter);
    }

    /**
     * 获得实际年龄。因为从界面输入的是今年的年龄，但设置的年份可能是后面的年份，这样就需要计算出到设置的年份进，真正的岁数。
     * 比如：今年是2014年，她26岁，如果设置为计划2017年生孩子的话，这样到2017年时，她的岁数就是29岁
     *
     * @param year
     * @param age
     * @return
     */
    int getRealAge(int year, int age) {
        int realAge = age;
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        if (year > currentYear) {
            realAge += (year - currentYear);
        }
        return realAge;
    }

    @Override
    protected void onDestroy() {
        if (adsMogoLayoutCode != null)
            adsMogoLayoutCode.clearThread();
        super.onDestroy();
    }
}
