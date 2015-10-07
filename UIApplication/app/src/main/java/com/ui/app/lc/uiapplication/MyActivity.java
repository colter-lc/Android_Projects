package com.ui.app.lc.uiapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class MyActivity extends Activity {

    private TextView textView1,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showActivityText();
    }

    private void showActivityText(){
        setContentView(R.layout.activity_text);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        String html = "<font color='red'>I love android.</font><br/>";
        html += "<font color='#fff000' ><big>I also Love you.</font><br/>";
        html += "<font color='#000ff'><small>I love three.</font><br/>";
        html += "<big><a href='www.baidu.com' >百度一下，你就知道</a>";
        CharSequence ch = Html.fromHtml(html);
        textView1.setText(ch);

        String text ="我的网站：www.sina.cn \n";
        text += "我的邮箱： 348722306@qq.com \n";
        text += "我的手机号： +86 020-6557566 \n";
        textView2.setText(text);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
