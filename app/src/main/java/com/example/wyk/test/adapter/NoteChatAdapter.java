package com.example.wyk.test.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wyk.test.R;

import java.util.List;

public class NoteChatAdapter extends BaseAdapter {
    private Activity activity;
    private List<String> dataList;
    private int layout;

    private TextView left;
    private TextView mes;
    private TextView right;

    public NoteChatAdapter(Activity activity, List<String> list, int layout){
        this.dataList = list;
        this.activity = activity;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view1 = layoutInflater.inflate(layout, null);

        left = view1.findViewById(R.id.test_class_note_chat_left);
        right = view1.findViewById(R.id.test_class_note_chat_right);
        mes = view1.findViewById(R.id.test_class_note_chat_mes);

        if (mes != null) {
            mes.setText(dataList.get(i));
            right.setVisibility(View.GONE);
        }


        return view1;
    }
}
