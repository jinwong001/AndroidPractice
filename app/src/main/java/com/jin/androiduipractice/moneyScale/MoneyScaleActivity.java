package com.jin.androiduipractice.moneyScale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jin.androiduipractice.R;

public class MoneyScaleActivity extends AppCompatActivity {
    private TextView mTextView;
    private MoneyScaleView mMoneyScaleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_scale);
        mTextView = (TextView) findViewById(R.id.money_account);
        mMoneyScaleView = (MoneyScaleView) findViewById(R.id.money_scale);

        mMoneyScaleView.setValueChangeListener(new MoneyScaleView.onValueChangeListener() {
            @Override
            public void getValue(int value) {
                mTextView.setText(String.valueOf(value));
            }
        });
    }
}
