package com.app.lc.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.HostNameResolver;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;


public class MyActivity extends Activity {

    private  EditText userNameText;
    private  EditText passwordText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userNameText = (EditText) findViewById(R.id.userName);
        passwordText = (EditText) findViewById(R.id.password);
        Button loginBtn = (Button) findViewById(R.id.login);
        Button cancelBtn = (Button) findViewById(R.id.cancel);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = userNameText.getText().toString();
                final String password = passwordText.getText().toString();

                if(userName == null || password == null || "".equals(userName.trim()    ) || "".equals(password.trim())){
                    Toast.makeText(MyActivity.this,"用户名或者密码不能为空!",Toast.LENGTH_LONG).show();
                }else{
                    String url = "http://192.168.0.100:8088/Project_web_all/LoginServlet";

                    final String[] result = new String[1];
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            result[0] =  send( userName, password);
                        }
                    });
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(result[0]);


                    Intent intent = new Intent();
                    intent.setClass(MyActivity.this,LoginTomcat.class);
                    intent.putExtra("result",result[0]);
                    startActivity(intent);
                    MyActivity.this.finish();
                }

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("cancel","Login Cancel");
                userNameText.setText("");
                passwordText.setText("");
                Toast.makeText(MyActivity.this,"用户名和密码已经重置!",Toast.LENGTH_LONG).show();
            }
        });

    }

    public String send(String userName,String password){
        String target = "http://192.168.0.100:8088/Project_web_all/LoginServlet";
        target = target+"?userName="+userName+"&password="+password;
        URL url ;
        try {
            url = new URL(target);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader buffer = new BufferedReader(in);
            String inputLine = null;
            StringBuilder sb = new StringBuilder();
            while((inputLine= buffer.readLine()) != null){
                sb.append(inputLine);
                sb.append("\n");
            }

            in.close();
            conn.disconnect();

            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private void calculator(){
        Button btnPlus = (Button) findViewById(R.id.plus);
        Button btnMinus = (Button) findViewById(R.id.minus);
        Button btnMultiply = (Button) findViewById(R.id.multiply);
        Button btnDivide = (Button) findViewById(R.id.divide);

        Button btn0 = (Button) findViewById(R.id.zero);
        Button btn1 = (Button) findViewById(R.id.one);
        Button btn2 = (Button) findViewById(R.id.two);
        Button btn3 = (Button) findViewById(R.id.three);
        Button btn4 = (Button) findViewById(R.id.four);
        Button btn5 = (Button) findViewById(R.id.five);
        Button btn6 = (Button) findViewById(R.id.six);
        Button btn7 = (Button) findViewById(R.id.seven);
        Button btn8 = (Button) findViewById(R.id.eight);
        Button btn9 = (Button) findViewById(R.id.nine);

        TextView view = (TextView) findViewById(R.id.result);
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
