package com.jin.androiduipractice.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.jin.androiduipractice.R;

public class ClipboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipboard);

//        ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//
//        //将内容复制到粘贴板
//        ClipData data = ClipData.newPlainText("text", "粘贴测试内容");
//        manager.setPrimaryClip(data);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //支付宝 吱口令，检测ClipboardManager有否内容，当有内容，解析口令，当特殊口令，进入特殊页面，
        ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        ClipData clipData = manager.getPrimaryClip();
        CharSequence content = clipData.getItemAt(0).getText();
        if (!TextUtils.isEmpty(content)) {
            Toast.makeText(this, content, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "粘贴板无内容", Toast.LENGTH_LONG).show();
        }
    }
}
