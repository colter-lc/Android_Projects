package com.dialogs.app.lc.dialogsapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MyActivity extends Activity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minitus;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = new ProgressDialog(MyActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("正在努力加载中...");
                dialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);

                dialog.show();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog(MyActivity.this);
                dialog.show();
            }
        });

        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minitus = cal.get(Calendar.MINUTE);

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(MyActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(getApplicationContext(),year +"-"+monthOfYear+"-"+dayOfMonth,Toast.LENGTH_SHORT).show();
                    }
                },year,month,day);
                dialog.show();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              TimePickerDialog dialog = new TimePickerDialog(MyActivity.this,new TimePickerDialog.OnTimeSetListener() {
                  @Override
                  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                      Toast.makeText(getApplicationContext(),hourOfDay +"-"+minute,Toast.LENGTH_SHORT).show();
                  }
              },hour,minitus,true);
              dialog.show();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                showDelete();
                break;
            case R.id.btn2:
                showSelect();
                break;
            case R.id.btn3:
                showSingle();
                break;
            case R.id.btn4:
                showMulti();
                break;
        }

    }

    private void showDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);
        builder.setTitle("删除确认");
        builder.setMessage("你真的要删除么？");
        builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn1.setText("你已经确认删除了，所以这是新的.");
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn1.setText("你取消了删除，所以还是原来的按钮.");
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSelect(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);
        builder.setTitle("选择你的程度");
        builder.setItems(R.array.love, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),getResources().getStringArray(R.array.love)[which],Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSingle(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);
        builder.setTitle("选择你喜欢的城市");
        builder.setSingleChoiceItems(R.array.cities,2,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"你喜欢的是："+getResources().getStringArray(R.array.cities)[which],Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showMulti(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);
        builder.setTitle("选择你喜欢的城市");
        final List<String> list = new ArrayList<String>();
        builder.setMultiChoiceItems(R.array.cities, new boolean[]{false, false, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                String[] cs = getResources().getStringArray(R.array.cities);
                if (isChecked) {
                    list.add(cs[which]);
                } else {
                    list.remove(cs[which]);
                }

            }
        });

        builder.setPositiveButton("我选好了",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder sb = new StringBuilder();
                if(list.size() > 0){
                    sb.append("你选择的城市是：");
                    for(String s :list){
                        sb.append(s);
                        sb.append(" ");
                    }
                }else{
                    sb.append("你没有选择城市.");
                }
                Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
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
