package com.app.lc.xiaololidejsq;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

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

        Button btnDel = (Button) findViewById(R.id.delete);
        Button btnCancel = (Button) findViewById(R.id.empty);
        Button btnDot = (Button) findViewById(R.id.dot);
        Button btnEquals = (Button) findViewById(R.id.equals);

         view = (TextView) findViewById(R.id.result);

        String result = "";
        final StringBuilder expression = new StringBuilder();

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append(0);
                view.setText(expression.toString());
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append(1);
                view.setText(expression.toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append(2);
                view.setText(expression.toString());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append(3);
                view.setText(expression.toString());
            }
        });


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append(4);
                view.setText(expression.toString());
            }
        });


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append(5);
                view.setText(expression.toString());
            }
        });


        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append(6);
                view.setText(expression.toString());
            }
        });


        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append(7);
                view.setText(expression.toString());
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append(8);
                view.setText(expression.toString());
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append(9);
                view.setText(expression.toString());
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append("+");
                view.setText(expression.toString());
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append("-");
                view.setText(expression.toString());
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append("*");
                view.setText(expression.toString());
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append("/");
                view.setText(expression.toString());
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append(".");
                view.setText(expression.toString());
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expression.length() != 0){
                    expression.deleteCharAt(expression.length()-1);
                }
                view.setText(expression.toString());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.delete(0, expression.length())   ;
                view.setText(expression.toString());
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expression.length() != 0){
                    String expressionResult = expression.toString();
                    Calculator cal = new Calculator();
                    try {
                        Object[] objs = cal.changeToPostFixExpression(expressionResult);
                        double value = cal.getValue(objs);
                        view.setText(String.valueOf(value));
                        expression.delete(0, expression.length());
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "表达式不正确，请检查!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
