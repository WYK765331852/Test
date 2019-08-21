package com.example.wyk.test.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wyk.test.MainActivity;
import com.example.wyk.test.R;
import com.example.wyk.test.util.DataUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class MenuActivity extends AppCompatActivity {
    TextView calculator;
    TextView note;
    TextView showLog;

    CalculatorListener calculatorListener;
    NoteListener noteListener;
    ShowLogListener showLogListener;

    String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_menu);

        Intent intent = getIntent();
        name = intent.getStringExtra("userName");
        setTitle(name);

        initView();
    }

    private void initView() {
        calculator = findViewById(R.id.test_class_cal_tv);
        note = findViewById(R.id.test_class_note_tv);
        showLog = findViewById(R.id.test_class_show_log_tv);

        calculatorListener = new CalculatorListener();
        noteListener = new NoteListener();
        showLogListener = new ShowLogListener();

        calculator.setOnClickListener(calculatorListener);
        note.setOnClickListener(noteListener);
        showLog.setOnClickListener(showLogListener);

        saveLog();
    }

    public class CalculatorListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MenuActivity.this, CalculatorActivity.class);
            startActivity(intent);

        }
    }

    public class NoteListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MenuActivity.this, NoteActivity.class);
            startActivity(intent);

        }
    }

    public class ShowLogListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MenuActivity.this, ShowLogActivity.class);
            startActivity(intent);

        }
    }
    private void saveLog() {
        PrintWriter printWriter = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput("record.log", Context.MODE_APPEND);
            printWriter = new PrintWriter(fileOutputStream);
            printWriter.println(DataUtil.getDataString(new Date()) + "-->" + name + ":登录app\n");
            printWriter.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                printWriter.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.test_menu_item_back:
                SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.clear();
                editor.commit();

                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
