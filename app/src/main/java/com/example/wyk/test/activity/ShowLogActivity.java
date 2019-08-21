package com.example.wyk.test.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wyk.test.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShowLogActivity extends AppCompatActivity {
    TextView tvShowLog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_show_log);

        initView();

        readLog();
    }
    private void initView(){
        tvShowLog = findViewById(R.id.test_class_show_log_tv);

    }

    private void readLog() {
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        String line;
        try {
            fileInputStream = openFileInput("record.log");
            //字节流-->字符流
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((line = bufferedReader.readLine()) != null) {
                tvShowLog.append("\n" + line);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
