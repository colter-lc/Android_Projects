package com.dialogs.app.lc.dialogsapplication;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by LC on 10/4/2015.
 */
public class CustomDialog extends Dialog {
    private Context context;

    public CustomDialog(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init(){
   //     View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog,null);
        setContentView(R.layout.custom_dialog);
        setTitle("自定义对话框");
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText("你好，自定义");
        textView.setTextColor(Color.BLUE);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageResource(R.drawable.girl);
    }

    public void show(){
        super.show();
    }
}
