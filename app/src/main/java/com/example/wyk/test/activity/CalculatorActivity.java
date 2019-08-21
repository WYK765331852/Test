package com.example.wyk.test.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wyk.test.MainActivity;
import com.example.wyk.test.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonPlus, buttonMinus, buttonMulti, buttonDiv;
    Button buttonResult, buttonClear;
    TextView tvRes;

    StringBuffer num1, num2;
    String flag = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_calculator);

        initView();

    }

    private void initView() {
        button0 = findViewById(R.id.test_class_calculator_0);
        button1 = findViewById(R.id.test_class_calculator_1);
        button2 = findViewById(R.id.test_class_calculator_2);
        button3 = findViewById(R.id.test_class_calculator_3);
        button4 = findViewById(R.id.test_class_calculator_4);
        button5 = findViewById(R.id.test_class_calculator_5);
        button6 = findViewById(R.id.test_class_calculator_6);
        button7 = findViewById(R.id.test_class_calculator_7);
        button8 = findViewById(R.id.test_class_calculator_8);
        button9 = findViewById(R.id.test_class_calculator_9);

        buttonPlus = findViewById(R.id.test_class_calculator_plus);
        buttonMinus = findViewById(R.id.test_class_calculator_minus);
        buttonMulti = findViewById(R.id.test_class_calculator_multi);
        buttonDiv = findViewById(R.id.test_class_calculator_divide);

        buttonClear = findViewById(R.id.test_class_calculator_clear);
        buttonResult = findViewById(R.id.test_class_calculator_result);

        tvRes = findViewById(R.id.test_class_calculator_res);

        button0.setOnClickListener(CalculatorActivity.this);
        button1.setOnClickListener(CalculatorActivity.this);
        button2.setOnClickListener(CalculatorActivity.this);
        button3.setOnClickListener(CalculatorActivity.this);
        button4.setOnClickListener(CalculatorActivity.this);
        button5.setOnClickListener(CalculatorActivity.this);
        button6.setOnClickListener(CalculatorActivity.this);
        button7.setOnClickListener(CalculatorActivity.this);
        button8.setOnClickListener(CalculatorActivity.this);
        button9.setOnClickListener(CalculatorActivity.this);
        buttonPlus.setOnClickListener(CalculatorActivity.this);
        buttonMulti.setOnClickListener(CalculatorActivity.this);
        buttonMinus.setOnClickListener(CalculatorActivity.this);
        buttonDiv.setOnClickListener(CalculatorActivity.this);
        buttonClear.setOnClickListener(CalculatorActivity.this);
        buttonResult.setOnClickListener(CalculatorActivity.this);

        num1 = new StringBuffer();
        num2 = new StringBuffer();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_class_calculator_plus:
                flag = "+";
                break;
            case R.id.test_class_calculator_minus:
                flag = "-";
                break;
            case R.id.test_class_calculator_multi:
                flag = "×";
                break;
            case R.id.test_class_calculator_divide:
                flag = "/";
                break;

            case R.id.test_class_calculator_result:
                //计算结果
                calculate(flag);
                break;

            case R.id.test_class_calculator_clear:
                clear();
                break;

            default:
                productNum(view.getId());
        }
    }

    private void calculate(String flag) {
        float n1 = Float.parseFloat(num1.toString());
        float n2 = Float.parseFloat(num2.toString());

        if (flag == null) {
            Toast.makeText(CalculatorActivity.this, "No result", Toast.LENGTH_SHORT).show();
        } else if (flag.equals("+")) {
            tvRes.setText(String.valueOf(n1 + n2));
        } else if (flag.equals("-")) {
            tvRes.setText(String.valueOf(n1 - n2));
        } else if (flag.equals("×")) {
            tvRes.setText(String.valueOf(n1 * n2));
        } else if (flag.equals("/")) {
            tvRes.setText(String.valueOf(n1 / n2));
        }
    }

    private void clear() {
        tvRes.setText("");
        num1 = new StringBuffer();
        num2 = new StringBuffer();
        flag = "";
        Log.d("aaaa", "clear");
    }

    private void productNum(int id) {
        Button bt = findViewById(id);
        if (flag == null) {
            num1.append(bt.getText());
            tvRes.setText(num1);
        } else {
            num2.append(bt.getText());
            tvRes.setText(num2);
        }
        Log.d("aaaa", "num1=" + num1 + " num2=" + num2);
    }
}
