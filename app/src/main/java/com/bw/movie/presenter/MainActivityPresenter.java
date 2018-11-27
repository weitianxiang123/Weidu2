package com.bw.movie.presenter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

public class MainActivityPresenter extends AppDelegate{
    private Context context;
    private TextView test0;
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.context = context;
    }

    public void initView(TextView test0) {
        this.test0 = test0;
    }

    @Override
    public void initData() {
        super.initData();
        test0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 测试
                test0.setText("点击");
            }
        });
    }
}
