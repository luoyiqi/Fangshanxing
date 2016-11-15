package com.example.huanglei.fangshanxing;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    private int[] res = {R.id.image_l,R.id.image_c,R.id.image_e,R.id.image_h
    ,R.id.image_a,R.id.image_p,R.id.image_t};

    //因为imageview比较多，所以用for循环初始化，然后放到list中，等下直接通过i拿到。
    private List<ImageView> imageList = new ArrayList<>();

    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < res.length; i++) {
            ImageView image = (ImageView) findViewById(res[i]);
            image.setOnClickListener(this);
            imageList.add(image);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_l:
                if (flag){
                    openAnima();
                }else {
                    closeAnima();
                }
                break;
        }
    }

    private void closeAnima() {
        for (int i = 1; i <res.length; i++) {
            //关闭很简单，把动画的起始和终点对调就行了。
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageList.get(i),"translationY",i*130,0F);
            animator.setDuration(300);
            animator.setStartDelay(i*300);
            animator.setInterpolator(new BounceInterpolator());
            animator.start();
            flag = true;
        }

    }

    private void openAnima() {
        //这里注意从1开始，因为第一张是红的按钮，一次弹出的是覆盖在下面的6张。
        for (int i = 1; i <res.length; i++) {
            //这里，从0到i＊100！！！100即每个之间的间隔，每次增大100！！这里重点掌握！！！
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageList.get(i),"translationY",0F,i*130);
            // 动画持续时间
            animator.setDuration(500);
            //这里同理！！每次增加300ms，刚好每个间隔都是300ms。i＊300！！！
            animator.setStartDelay(i*500);
            //设置动画效果
            animator.setInterpolator(new BounceInterpolator());
            animator.start();
            flag = false;

        }
    }
}
