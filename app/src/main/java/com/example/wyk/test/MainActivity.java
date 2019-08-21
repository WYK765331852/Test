package com.example.wyk.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wyk.test.activity.MenuActivity;
import com.example.wyk.test.database.DataBaseOpenHelper;
import com.example.wyk.test.dialog.RegisterDialog;

public class MainActivity extends AppCompatActivity {
    private Button mLoginButton;
    private Button mRegisterButton;
    private CheckBox mCheckBox;
    private EditText mName;
    private EditText mPassword;
    private String name;
    private String password;
    private TextView mTvRegister;
    private DataBaseOpenHelper helper; //打开数据库
    private SQLiteDatabase database;  //数据库

    private static String PREFS_NAME = "account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_login);
        initView();

        String n = checkLogin();
        if (n.length() > 0) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("userName", n);
            startActivity(intent);
            finish();
        }

    }

    private void initView() {
        mTvRegister = findViewById(R.id.test_login_register_hint);
        mLoginButton = findViewById(R.id.test_login_bt);
        mRegisterButton = findViewById(R.id.test_login_register);
        mName = findViewById(R.id.test_login_et_name);
        mPassword = findViewById(R.id.test_login_et_password);
        mCheckBox = findViewById(R.id.test_class_login_checkbox);

        LoginBtListener loginBtListener = new LoginBtListener();
        mLoginButton.setOnClickListener(loginBtListener);

        RegisterListener registerListener = new RegisterListener();
        mRegisterButton.setOnClickListener(registerListener);

        helper = new DataBaseOpenHelper(MainActivity.this, "user.database3", null, 1);
        database = helper.getWritableDatabase(); //写操作，获取可写数据库
    }

    public class LoginBtListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            name = mName.getText().toString();
            password = mPassword.getText().toString();

            Intent intent = new Intent(MainActivity.this, MenuActivity.class);

//            if (name.equals("admin") && password.equals("123")) {
//                Toast.makeText(MainActivity.this, "login response OK", Toast.LENGTH_SHORT).show();
//                if (mCheckBox.isChecked()) {
//                    saveAccount(name, password);
//                }
//                startActivity(intent);
//                finish();
//            } else {
//                Toast.makeText(MainActivity.this, "密码有误！", Toast.LENGTH_SHORT).show();
//            }

            if (loginCheck(name,password)!=null){
                Toast.makeText(MainActivity.this, "login response OK", Toast.LENGTH_SHORT).show();
                if (mCheckBox.isChecked()) {
                    saveAccount(name, password);
                }
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(MainActivity.this, "密码有误！", Toast.LENGTH_SHORT).show();
            }

        }
    }


    public class RegisterListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "register response OK", Toast.LENGTH_SHORT).show();
            RegisterDialog registerDialog = new RegisterDialog(MainActivity.this, database);
            registerDialog.showDialog();
        }
    }


    private void saveAccount(String name, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("username", name);
        editor.putString("password", password);
        editor.commit();
        Toast.makeText(MainActivity.this, "save successfully", Toast.LENGTH_SHORT).show();

    }

    private void getUserInfo() {
        SharedPreferences userInfo = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String username = userInfo.getString("username", null);
        String password = userInfo.getString("password", null);

        if (username.equals("admin") && password.equals("123")) {
            Toast.makeText(MainActivity.this, "response OK", Toast.LENGTH_SHORT).show();
        }
    }

    private String checkLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String n = sharedPreferences.getString("username", null);
        String p = sharedPreferences.getString("password", null);
        if (n == null | p == null) {
            return "";
        } else {
            return n;
        }

    }
    private String loginCheck(String name, String pwd){
        //游标遍历数据
        Cursor cursor=database.query("userAccount", new String[]{"userName", "userPwd"}, "userName=? and userPwd=?", new String[]{name, pwd},null,null,null);
        if (cursor.moveToNext()){
            return name;
        }else {
            return null;
        }

    }

}
