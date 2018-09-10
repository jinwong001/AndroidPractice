package com.jin.androiduipractice.waveView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jin.androiduipractice.R;

public class WaveViewActivity extends AppCompatActivity {
    private ImageView imageView1;
    private WaveView1 waveView1;

    private ImageView imageView2;
    private WaveView2 waveView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_view);

        imageView1 = (ImageView) findViewById(R.id.image1);
        waveView1 = (WaveView1) findViewById(R.id.wave_view1);

        imageView2 = (ImageView) findViewById(R.id.image2);
        waveView2 = (WaveView2) findViewById(R.id.wave_view2);


        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2, -2);
        lp.gravity = Gravity.BOTTOM | Gravity.CENTER;
        waveView1.setOnWaveAnimationListener(new WaveView1.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                lp.setMargins(0, 0, 0, (int) y + 2);
                imageView1.setLayoutParams(lp);
            }
        });

        waveView2.setOnWaveAnimationListener(new WaveView2.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                lp.setMargins(0, 0, 0, (int) y + 2);
                imageView2.setLayoutParams(lp);
            }
        });


    }
}
