package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Script;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_one,
    btn_two, btn_three, btn_four, btn_five,
    btn_six, btn_seven, btn_eight, btn_nine,
    btn_zero;

    Button btn_plus,
    btn_minus, btn_multiply, btn_divide, btn_dot,
    btn_clear, btn_equal;

    TextView text_display;
    TextView text_history;

    String history = new String("");

    // This is to evaluate the math expression
    ScriptEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        engine = new ScriptEngineManager().getEngineByName("rhino");

        btn_one = (Button) findViewById(R.id.btn_one);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_three = (Button) findViewById(R.id.btn_three);
        btn_four = (Button) findViewById(R.id.btn_four);
        btn_five = (Button) findViewById(R.id.btn_five);
        btn_six = (Button) findViewById(R.id.btn_six);
        btn_seven = (Button) findViewById(R.id.btn_seven);
        btn_eight = (Button) findViewById(R.id.btn_eight);
        btn_nine = (Button) findViewById(R.id.btn_nine);
        btn_zero = (Button) findViewById(R.id.btn_zero);

        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_dot  = (Button) findViewById(R.id.btn_dot);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_equal = (Button) findViewById(R.id.btn_equal);

        text_display = (TextView) findViewById(R.id.textview_display);
        text_history = (TextView) findViewById(R.id.textView_history);

        setClickListeners();
    }

    private void setClickListeners(){
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_seven.setOnClickListener(this);
        btn_eight.setOnClickListener(this);
        btn_nine.setOnClickListener(this);
        btn_zero.setOnClickListener(this);

        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_one:
                addNumber("1");
                break;
            case R.id.btn_two:
                addNumber("2");
                break;
            case R.id.btn_three:
                addNumber("3");
                break;
            case R.id.btn_four:
                addNumber("4");
                break;
            case R.id.btn_five:
                addNumber("5");
                break;
            case R.id.btn_six:
                addNumber("6");
                break;
            case R.id.btn_seven:
                addNumber("7");
                break;
            case R.id.btn_eight:
                addNumber("8");
                break;
            case R.id.btn_nine:
                addNumber("9");
                break;
            case R.id.btn_zero:
                addNumber("0");
                break;
            case R.id.btn_plus:
                addNumber("+");
                break;
            case R.id.btn_minus:
                addNumber("-");
                break;
            case R.id.btn_multiply:
                addNumber("*");
                break;
            case R.id.btn_divide:
                addNumber("/");
                break;
            case R.id.btn_dot:
                addNumber(".");
                break;
            case R.id.btn_clear:
                clear_display();
                break;
            case R.id.btn_equal:
                String result = null;
                try {
                    result = evaluate(text_display.getText().toString());
                    if(result.endsWith(".00")){ // Removes the .00 from the result
                        result = result.replace(".00", "");
                    }
                    text_display.setText(result);

                    history = history+"\n"+result; // Adds result to history
                    text_history.setText(history);

                } catch (ScriptException e) {
                    text_display.setText("ERROR");
                }
                break;
        }
    }

    private String evaluate(String expression) throws ScriptException {
        String result = engine.eval(expression).toString();
        BigDecimal decimal = new BigDecimal(result);
        return decimal.setScale(2, RoundingMode.HALF_UP).toPlainString(); // Switched out BigDecimal.ROUND_HALF_UP to fix error
    }

    private void addNumber(String number){
        text_display.setText(text_display.getText()+number);

        history = history+number;  // Adds equations to history
        text_history.setText(history);
    }

    private void clear_display(){
        text_display.setText("");

        history = "";  // Clears history
        text_history.setText(history);
    }
}