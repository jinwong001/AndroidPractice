package com.jin.androiduipractice;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jin.androiduipractice.AudioBar.AudioBarActivity;
import com.jin.androiduipractice.LinearGradient.LinearGradientActivity;
import com.jin.androiduipractice.drawer.DrawerActivity;
import com.jin.androiduipractice.move.MoveActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setData();
        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mList));
    }

    private void setData() {
        mList.clear();
        mList.add("LinearGradient");
        mList.add("音频条");
        mList.add("滑动");
        mList.add("ViewDragHelper");
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String item = mList.get(position);
        if (TextUtils.isEmpty(item)) {
            return;
        }
        switch (item) {
            case "LinearGradient":
                startActivity(new Intent(this, LinearGradientActivity.class));
                break;
            case "音频条":
                startActivity(new Intent(this, AudioBarActivity.class));
                break;
            case "滑动":
                startActivity(new Intent(this, MoveActivity.class));
                break;
            case "ViewDragHelper":
                startActivity(new Intent(this, DrawerActivity.class));
                break;
            default:
                break;
        }
    }
}
