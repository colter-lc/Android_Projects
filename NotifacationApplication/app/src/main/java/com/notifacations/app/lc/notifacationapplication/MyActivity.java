package com.notifacations.app.lc.notifacationapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity {

    private Button noti1;
    private Button noti2;
    private Button noti3;
    private Button noti4;


    private NotificationManager manager;
    private Notification.Builder builder;
    private Notification.Builder builder2;

    private RemoteViews contentView;
    private Notification notification2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        noti1 = (Button) findViewById(R.id.noti1);
        noti2 = (Button) findViewById(R.id.noti2);
        noti3 = (Button) findViewById(R.id.noti3);

        noti1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "这是一个简单的通知", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.LEFT,0,0);
                toast.show();

            }
        });

        noti2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.custom_notification1, null);

                Toast toast = new Toast(MyActivity.this);
                ImageView imageView = (ImageView) view.findViewById(R.id.image1);
                imageView.setImageResource(R.drawable.publish);

                TextView textView = (TextView) view.findViewById(R.id.text1);
                textView.setTextColor(Color.CYAN);
                textView.setBackgroundColor(Color.DKGRAY);
                textView.setText("This is a custom notification.");
                toast.setView(view);

                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new Notification.Builder(this);

        noti3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this,MyActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MyActivity.this,0,intent,0);
                builder.setContentIntent(pendingIntent);
                builder.setContentTitle("新的通知来啦。。。");
                builder.setContentText("测试一下你");
                builder.setSmallIcon(R.drawable.publish);
                manager.notify(100,builder.build());
            }
        });

        noti4 = (Button) findViewById(R.id.noti4);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        builder2 = new Notification.Builder(MyActivity.this);
//        contentView = new RemoteViews(MyActivity.this.getPackageName(), R.layout.custom_notification2);
//        contentView.setImageViewResource(R.id.image, R.drawable.custon_notification);
//        contentView.setTextViewText(R.id.title, "Custom notification");
//        contentView.setTextViewText(R.id.text, "This is a custom layout");
//        Intent intent = new Intent(MyActivity.this,MyActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(MyActivity.this,0,intent,0);
//        builder2.setContentIntent(pendingIntent);
//        builder2.setContent(contentView);
//        builder2.setContentText("11111111111111111");
//        builder2.setContentTitle("222222222222222");
//        builder2.setSmallIcon(R.drawable.icon_add);
//        notification2 = builder2.build();

        manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification2=new Notification(R.drawable.custon_notification,"图标边的文字",System.currentTimeMillis());
        notification2.contentView = new RemoteViews(getPackageName(),R.layout.custom_notification2);
        //使用notification.xml文件作VIEW
        notification2.contentView.setProgressBar(R.id.pb, 100,0, false);
        //设置进度条，最大值 为100,当前值为0，最后一个参数为true时显示条纹
        //（就是在Android Market下载软件，点击下载但还没获取到目标大小时的状态）
        Intent notificationIntent = new Intent(MyActivity.this,MyActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(MyActivity.this,0,notificationIntent,0);
        notification2.contentIntent = contentIntent;
        noti4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.notify(99,notification2);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        MenuItem item1 = menu.add(1000,1,10,"Menu A");
        item1.setIcon(R.drawable.icon_add);
        MenuItem item2 = menu.add(1000,2,9,"Menu B");
        item2.setIcon(R.drawable.icon_right);
        MenuItem item3 = menu.add(1000,3,8,"Menu C");
        item3.setIcon(R.drawable.icon_wrong);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String info = "";
        switch(item.getItemId()){
            case 1:
                info = "Menu A";
                break;
            case 2:
                info = "Menu B";
                break;
            case 3:
                info = "Menu C";
                break;
        }
        Toast.makeText(MyActivity.this,info,Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
