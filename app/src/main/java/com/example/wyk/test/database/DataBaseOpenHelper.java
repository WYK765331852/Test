package com.example.wyk.test.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    public DataBaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建时
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = " create table userAccount(_id integer primary key autoincrement, userName varchar(20), userPwd varchar(20), userPhone varchar(11))";
        try {
            sqLiteDatabase.execSQL(sql);
            Log.i("Msg", "table create Ok");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    //更新时
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
