package com.android.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;

/**
 * 开屏页（图片待换）
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_splash, null);
        setContentView(view);

        //渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimation.setDuration(3000);
        view.setAnimation(alphaAnimation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //设置休眠时间
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //从开屏页跳转到主界面
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                //销毁开屏页
                SplashActivity.this.finish();
            }
        }).start();
    }
}
