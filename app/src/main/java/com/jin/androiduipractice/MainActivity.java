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
import com.jin.androiduipractice.clipboard.ClipboardActivity;
import com.jin.androiduipractice.constraint.ConstraintActivity;
import com.jin.androiduipractice.drawable.DrawableActivity;
import com.jin.androiduipractice.drawer.DrawerActivity;
import com.jin.androiduipractice.moneyScale.MoneyScaleActivity;
import com.jin.androiduipractice.move.MoveActivity;
import com.jin.androiduipractice.path.PathActivity;
import com.jin.androiduipractice.redPoint.RedPointActivity;
import com.jin.androiduipractice.roundIndicator.RoundIndicatorActivity;
import com.jin.androiduipractice.weatherCurve.WeatherCurveActivity;

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
        mList.add("芝麻信用积分轮盘");
        mList.add("选择金钱刻度线");
        mList.add("path");
        mList.add("气温变化曲线");
        mList.add("QQ小红点");
        mList.add("自定义Drawable");
        mList.add("Constraint");
        mList.add("Clipboard");
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
            case "芝麻信用积分轮盘":
                startActivity(new Intent(this, RoundIndicatorActivity.class));
                break;
            case "选择金钱刻度线":
                startActivity(new Intent(this, MoneyScaleActivity.class));
                break;
            case "path":
                startActivity(new Intent(this, PathActivity.class));
                break;
            case "气温变化曲线":
                startActivity(new Intent(this, WeatherCurveActivity.class));
                break;
            case "QQ小红点":
                startActivity(new Intent(this, RedPointActivity.class));
                break;
            case "自定义Drawable":
                startActivity(new Intent(this, DrawableActivity.class));
                break;
            case "Constraint":
                startActivity(new Intent(this, ConstraintActivity.class));
                break;
            case "Clipboard":
                startActivity(new Intent(this, ClipboardActivity.class));
                break;
            default:
                break;
        }
    }
}
