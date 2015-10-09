package com.lc.app.guessnumbers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Random;


public class GuessNumberActivity extends ActionBarActivity {

    private static int result;

    private static String[] numArray;

    private AlertDialog sucDia;

    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_number);
        resources = getResources();

        int deMax = 100;

        Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据
        String str=bundle.getString(resources.getString(R.string.key_user_define));//getString()返回指定key的值

        if(str != null && !"".equals(str)){

        }

        result = randomNumber(deMax);
        Log.i("guess result:",String.valueOf(result));

        Toast toast = Toast.makeText(getApplicationContext(),resources.getString(R.string.game_start),Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        numArray = new String[deMax+1];
        for(int i = 0; i< numArray.length;i++){
            numArray[i] = String.valueOf(i);
        }

        int deSelect = 0;

        //开始游戏
        AlertDialog.Builder builder = new AlertDialog.Builder(GuessNumberActivity.this);
        builder.setTitle(resources.getString(R.string.game_select));
        builder.setSingleChoiceItems(numArray, deSelect, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int choise = Integer.parseInt(numArray[i]);
                if (result > choise) {
                    String message = resources.getString(R.string.game_prefix)+ choise + resources.getString(R.string.game_big);
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (result < choise) {
                    String message = resources.getString(R.string.game_prefix)+ choise + resources.getString(R.string.game_small);
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    final DialogInterface dia = dialogInterface;
                    AlertDialog.Builder sucBuilder = new AlertDialog.Builder(GuessNumberActivity.this);
                    sucBuilder.setTitle(resources.getString(R.string.game_cong));
                    sucBuilder.setMessage(resources.getString(R.string.game_answer) + choise);
                    sucBuilder.setNegativeButton(resources.getString(R.string.game_mainmenu), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface2, int i) {
                            dia.dismiss();
                            dialogInterface2.dismiss();

                            Intent intent = new Intent();
                            intent.setClass(GuessNumberActivity.this, MainActivity.class);
                            GuessNumberActivity.this.finish();
                            GuessNumberActivity.this.startActivity(intent);

                        }
                    });

                    sucBuilder.setPositiveButton(resources.getString(R.string.game_again), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface2, int i) {
                            dia.dismiss();
                            dialogInterface2.dismiss();

                            Intent intent = getIntent();
                            overridePendingTransition(0, 0);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            finish();

                            overridePendingTransition(0, 0);
                            startActivity(intent);
                        }
                    });
                    sucDia = sucBuilder.create();
                    sucDia.show();
                }
            }
        });

        builder.setNegativeButton(resources.getString(R.string.game_mainmenu), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                Intent intent = new Intent();
                intent.setClass(GuessNumberActivity.this, MainActivity.class);
                GuessNumberActivity.this.finish();
                GuessNumberActivity.this.startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        if (!GuessNumberActivity.this.isFinishing() && !dialog.isShowing()) {
            dialog.show();
        }
    }


    public int randomNumber(int max){
        Random random = new Random(System.currentTimeMillis());
        int temp = random.nextInt(max+1);
        return temp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guess_number, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
