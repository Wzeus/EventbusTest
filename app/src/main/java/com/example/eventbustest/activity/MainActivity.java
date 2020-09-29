package com.example.eventbustest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventbustest.R;
import com.example.eventbustest.event.TestMessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private static String TAG ="WWWWWMainActivity";
    private Button jumpButton;
    private TextView mTestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"Created");

        jumpButton = findViewById(R.id.ToSecondBtn);
        mTestView = findViewById(R.id.thetextview);

        jumpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondActivity.name="HaHa";
                EventBus.getDefault().postSticky(new TestMessageEvent("Hello Second Activity"));
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

//        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Stoped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Destoryed");
//        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(TestMessageEvent event) {
        Log.d(TAG,"Receive event successfully");
        String msg =  "We have received a message : " + event.getTestMessage();
        mTestView.setText(msg);
    }
}