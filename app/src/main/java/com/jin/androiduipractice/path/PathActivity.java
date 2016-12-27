package com.jin.androiduipractice.path;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jin.androiduipractice.R;

import java.util.List;

public class PathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

        TextView textView = (TextView) findViewById(R.id.memory);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView, "rotation", 0, 30);
        objectAnimator.setDuration(3000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();

        leakFun();

    }

    private void leakFun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //检查一个服务是否在运行
    public static boolean isServiceRunning(Context context, String serviceName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> infos = am.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo info : infos) {
            String className = info.service.getClassName();
            if (className.equals(serviceName)) {
                return true;
            }
        }
        return false;
    }


    public static int getMemoryLimit(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //当manifest 中设置android:largeHeap="true"时内存限制
        // return am.getLargeMemoryClass();

        //获取app 的内存限制
        return am.getMemoryClass();
    }


}
