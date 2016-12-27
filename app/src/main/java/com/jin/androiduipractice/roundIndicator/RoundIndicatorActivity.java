package com.jin.androiduipractice.roundIndicator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jin.androiduipractice.R;

public class RoundIndicatorActivity extends AppCompatActivity {
    private EditText mCreditEdt;
    private Button mBtn;
    private RoundIndicatorView mRoundIndicatorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_indicator);
        mBtn = (Button) findViewById(R.id.btn);
        mCreditEdt = (EditText) findViewById(R.id.credit);
        mRoundIndicatorView = (RoundIndicatorView) findViewById(R.id.round_indicator);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mCreditEdt.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    Toast.makeText(RoundIndicatorActivity.this, "输入值不能为空", Toast.LENGTH_LONG).show();
                }
              //  mRoundIndicatorView.setCurrentNumAnim(Integer.valueOf(mCreditEdt.getText().toString()));
            }
        });
    }
}
