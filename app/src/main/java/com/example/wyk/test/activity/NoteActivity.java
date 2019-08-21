package com.example.wyk.test.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wyk.test.MainActivity;
import com.example.wyk.test.R;
import com.example.wyk.test.adapter.NoteChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener {
    ListView mListView;
    Button mSend;
    EditText mEditText;

    List<String> data = new ArrayList<>();

    NoteChatAdapter mNoteChatAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_note);
        initView();


    }
    private void initView(){
        mListView = findViewById(R.id.test_class_note_listview);
        mSend = findViewById(R.id.test_class_note_bt);
        mEditText = findViewById(R.id.test_class_note_et);

        mSend.setOnClickListener(NoteActivity.this);

        mNoteChatAdapter = new NoteChatAdapter(NoteActivity.this, data, R.layout.test_note_chat_list);
        mListView.setAdapter(mNoteChatAdapter);

    }

    @Override
    public void onClick(View view) {
        String text = mEditText.getText().toString();
        if (text != null) {
            data.add(text);
            //提示adapter数据发生改变
//            mListAdapter.notifyDataSetChanged();
            mNoteChatAdapter.notifyDataSetChanged();
            mEditText.setText("");
        }

    }
}
