package com.example.qinggong.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qinggong.R;
import com.example.qinggong.activity.ActivityCollector;

/**
 * Created by ZJGJK03 on 2014/12/7.
 * 并不是继承了Dialog，仅仅是对Dialog的外观作了一些更改
 * 也没有重写Dialog里一些方法。
 */
public class CustomAlertDialog {

    public CustomAlertDialog() {
        isCancel = true;
        isOk = true;
        isImage = false;
        isContent = true;
        titleText = "";
        isCancelable = true;
        content = "";
    }

    boolean isCancel, isOk, isImage, isContent, isCancelable;
    int resourceId;
    String content, cancalText, okText, titleText;

    /**
     * 设置返回键是否有效，默认为真，即有效，可以通过按返回键来关闭此对话框
     *
     * @param cancelable
     */
    public void setCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
    }

    /**
     * 设置标题文本
     *
     * @param titleText 需设置的标题文本
     */
    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    /**
     * 是否显示Cancel按钮
     *
     * @param isCancel   如果是真，则显示，不然不显示
     * @param cancelText 如果是真，则设置Cancel按钮的文本，如果是假，此值不起作用
     */
    public void setCancel(boolean isCancel, String cancelText) {
        this.isCancel = isCancel;
        this.cancalText = cancelText;
    }

    /**
     * 是否在对话框里里显示一段文字
     *
     * @param isContent 如果是真，则显示，不然不显示
     * @param content   如果是真，则显示设置的文本，如果是假，此值不起作用
     */
    public void setContent(boolean isContent, String content) {
        this.isContent = isContent;
        this.content = content;
    }

    /**
     * 是否显示OK按钮
     *
     * @param isOk   如果是真，则显示，不然不显示
     * @param okText 如果是真，则设置OK按钮的文本，如果是假，此值不起作用
     */
    public void setOk(boolean isOk, String okText) {
        this.isOk = isOk;
        this.okText = okText;
    }

    /**
     * 是否一个图片
     *
     * @param isImage    如果是真，则显示，不然不显示
     * @param resourceId 如果是真，显示传入的图片资源，如果是假，此值不起作用
     */
    public void setImage(boolean isImage, int resourceId) {
        this.isImage = isImage;
        this.resourceId = resourceId;
    }

    /**
     * 显示一个自定义的AlertDialog框
     *
     * @param context  必须传入当然调用的Context
     * @param listener 如果要处理Cancel和OK，必须定义此接口
     */
    public void showDialog(Context context, final CustomAlertDialogCallbackListener listener) {
        final AlertDialog dlg = new AlertDialog.Builder(context).create();
        dlg.setCancelable(isCancelable);
        dlg.show();
        Window window = dlg.getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.alpha = 1f;
//        window.setAttributes(lp);
        window.setContentView(R.layout.exit);
        TextView titleTv = (TextView) window.findViewById(R.id.exit_tv_title);
        titleTv.setText(titleText);

        TextView textView = (TextView) window.findViewById(R.id.exit_tv_content);
        textView.setText(content);
        if (isContent) {
            textView.setVisibility(View.VISIBLE);
        } else
            textView.setVisibility(View.GONE);

        ImageView imageView = (ImageView) window.findViewById(R.id.exit_iv_image);
        if (isImage) {
            imageView.setImageResource(resourceId);
            imageView.setVisibility(View.VISIBLE);
        } else
            imageView.setVisibility(View.GONE);

        Button btn_cancel = (Button) window.findViewById(R.id.exit_btn_cancel);
        btn_cancel.setText(cancalText);


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
                if (listener != null) {
                    listener.onClickCancel();
                }
            }
        });

        if (isCancel) {
            btn_cancel.setVisibility(View.VISIBLE);
        } else
            btn_cancel.setVisibility(View.GONE);

        Button btn_ok = (Button) window.findViewById(R.id.exit_btn_ok);
        btn_ok.setText(okText);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
                if (listener != null) {
                    listener.onClickOk();
                }
            }
        });
        if (isOk) {
            btn_ok.setVisibility(View.VISIBLE);
        } else
            btn_ok.setVisibility(View.GONE);

    }
}
