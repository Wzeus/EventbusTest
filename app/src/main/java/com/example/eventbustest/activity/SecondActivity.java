package com.example.eventbustest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventbustest.R;
import com.example.eventbustest.event.TestMessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {

    public static String file_name = "xxx.txt";
    public static String name;
    private static String TAG = "WWWWWSecondActivity";
    private Button sendButton;
    private Button jumpToThirdBtn;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "Created");
        int pid = android.os.Process.myPid();
        Log.d(TAG, "pid:" + pid);
        Toast.makeText(this, pid + "", Toast.LENGTH_LONG).show();

        sendButton = findViewById(R.id.SendBtn);
        jumpToThirdBtn = findViewById(R.id.ToThirdBtn);
        mTv = findViewById(R.id.SecTextview);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Post Successfully");
                EventBus.getDefault().postSticky(new TestMessageEvent("Hello MainActivity"));
                Toast.makeText(getApplicationContext(), "Post Successfully", Toast.LENGTH_LONG).show();
            }
        });
        jumpToThirdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        mTv.setText(name);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Destoryed");
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventMainThread(TestMessageEvent event) {
        Log.d(TAG, "Receive event successfully");
//        mTv.setText(event.getTestMessage());
    }
}