package com.xyz.newsh;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.newsh.activity.Guide;
import com.xyz.newsh.utlis.CacheUitls;

/**
 * 启动页面
 */
public class Launcher extends AppCompatActivity {

    LinearLayout rl_splahs_root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        rl_splahs_root = findViewById(R.id.rl_splahs_root);

        /**
         * 缩放动画
         */
        RotateAnimation rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF
                ,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setFillAfter(true);
        /**
         * 缩放动画：大小从0~1变大,缩放中心：界面中心
         */
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);//停留在动画结束后

        /**
         * 渐变动画：透明度0~1变大
         */
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setFillAfter(true);
        AnimationSet animationSet = new AnimationSet(false);

        animationSet.setDuration(2000);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        animationSet.setAnimationListener(new MyAnimationListener());
        rl_splahs_root.startAnimation(animationSet);


    }


    class MyAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

            Toast.makeText(Launcher.this, "动画执行完", Toast.LENGTH_SHORT).show();
            //判断应用是否第一次启动
            Boolean startMain = CacheUitls.getBoolean(Launcher.this, "startMain");
            if(startMain){
                Intent intent = new Intent(Launcher.this, MainActivity.class);
                startActivity(intent);
            }else {

                Intent intent = new Intent(Launcher.this, Guide.class);
                startActivity(intent);
            }
            //把当前页面从栈顶移除
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}