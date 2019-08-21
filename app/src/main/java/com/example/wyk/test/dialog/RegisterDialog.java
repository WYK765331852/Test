package com.example.wyk.test.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wyk.test.MainActivity;
import com.example.wyk.test.R;

public class RegisterDialog {

    private Activity activity;
    private SQLiteDatabase database;
    private EditText etName, etPassword, etSure, etPhoneNum;

    public RegisterDialog(Activity activity, SQLiteDatabase sqLiteDatabase) {
        this.activity = activity;
        this.database = sqLiteDatabase;
    }

    public void showDialog() {
        final View view = LayoutInflater.from(activity).inflate(R.layout.test_register_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setIcon(R.drawable.test_food_cake)
                .setTitle("注册账户")
                .setView(view)
                .setPositiveButton("注册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        etName = view.findViewById(R.id.test_reg_name_et);
                        etPassword = view.findViewById(R.id.test_reg_password_et);
                        etSure = view.findViewById(R.id.test_reg_sure_et);
                        etPhoneNum = view.findViewById(R.id.test_reg_phone_et);

                        String name = etName.getText().toString();
                        String password = etPassword.getText().toString();
                        String phone = etPhoneNum.getText().toString();

                        insertAccount(name, password, phone);
                    }
                })
                .show();

    }

    protected void insertAccount(String name, String pwd, String phone){
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName",name);
        contentValues.put("userPwd",pwd);
        contentValues.put("userPhone",phone);

        database.insert("userAccount",null,contentValues);
        database.insert("", null, contentValues);

        Toast.makeText(activity, "注册完成", Toast.LENGTH_SHORT).show();

    }

}
