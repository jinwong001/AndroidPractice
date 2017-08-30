package com.jin.androiduipractice.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.jin.androiduipractice.R;

public class DrawableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置透明
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.girl);

        ImageView circleImg=(ImageView)findViewById(R.id.girl_circle);
        ImageView roundImg=(ImageView)findViewById(R.id.girl_round);

        circleImg.setImageDrawable(new CircleImageDrawable(bitmap));
        roundImg.setImageDrawable(new RoundImageDrawable(bitmap));


    }
}
