package com.jin.androiduipractice.scheme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jin.androiduipractice.R;

public class SchemeStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_start);
    }

    public void onStartScheme(View view) {
        startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse("practice://scheme1")), 100);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Toast.makeText(this, "返回内容为空", Toast.LENGTH_LONG).show();
        }
        if (requestCode == 200) {
            Toast.makeText(this, "返回内容为空2", Toast.LENGTH_LONG).show();
        }
    }

    public void onStartScheme2(View view) {
        startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse("practice://scheme2")), 200);
    }
}
