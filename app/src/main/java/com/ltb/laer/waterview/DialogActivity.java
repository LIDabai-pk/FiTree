package com.ltb.laer.waterview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class DialogActivity extends Dialog {
    public Context context;
    private View view;

    public DialogActivity(Context context) {
        super(context);
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.activity_welcome, null);
    }

    protected DialogActivity(Context context, int cancelable) {
        super(context, cancelable);
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.activity_welcome, null);
    }

    public void close() {
        DialogActivity.this.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(view);
        // 获取屏幕宽、高用
        Window dialogWindow = this.getWindow();
        DisplayMetrics dm = new DisplayMetrics();
        dialogWindow.getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        // 屏幕的高0.5
        p.height = (int) (dm.heightPixels * 0.5);
        // 屏幕的宽0.8
        p.width = (int) (dm.widthPixels * 0.8);
        dialogWindow.setAttributes(p);

        //按返回键是否可以退出  默认true可以退出  false禁止
        this.setCancelable(true);
    }

}
