package com.bwie.caolei.myapp.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.bwie.caolei.cllibrary.bean.CoreBaseActivity;
import com.bwie.caolei.myapp.R;

import java.util.Timer;
/**
 * autour: 曹磊
 * date: 2017/1/5 8:32
 * update: 2017/1/5
 */

public class FlashActivity extends CoreBaseActivity {


    private Timer mTimer = new Timer();
    private int time = 3;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            if (msg.arg1 == 0) {
                startActivity(new Intent(FlashActivity.this, MainActivity.class));
                finish();
            } else {
                setTime();
            }

        }
    };
    private TextView mTv;




    @Override
    public int getLayoutId() {
        return R.layout.activity_flash;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setTime();
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    private void setTime() {
        Message message = Message.obtain();
        time = time - 1;
        message.arg1 = time;
        mHandler.sendMessageDelayed(message, 1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

}
