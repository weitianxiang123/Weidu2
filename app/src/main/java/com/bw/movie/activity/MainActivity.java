package com.bw.movie.activity;

import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.mvp.base.BaseActivity;
import com.bw.movie.presenter.MainActivityPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainActivityPresenter> {
    @BindView(R.id.test0)
    TextView test0;

    @Override
    public Class<MainActivityPresenter> getClassDelegate() {
        return MainActivityPresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        delegate.initView(test0);
    }
}
