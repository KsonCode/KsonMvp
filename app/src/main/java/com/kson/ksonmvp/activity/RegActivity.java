package com.kson.ksonmvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kson.ksonmvp.R;
import com.kson.ksonmvp.contract.user.IRegContract;
import com.kson.ksonmvp.presenter.user.RegPresenter;
import com.kson.ksonmvp.view.user.IRegView;

import java.util.HashMap;

public class RegActivity extends AppCompatActivity implements IRegContract.IRegView {
    private RegPresenter regPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initData();

    }

    private void initData() {
        regPresenter = new RegPresenter(this);//this代表什么，就是当前类的对象实例
    }

    /**
     * 注册
     * @param view
     */
    public void regBtn(View view) {

        HashMap<String,String> params = new HashMap<>();
        params.put("mobile","18623456789");
        params.put("password","111111");

        regPresenter.register(params);

    }

    @Override
    public void mobileError(String error) {

    }

    @Override
    public void success(String result) {

    }

    @Override
    public void failure(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (regPresenter!=null){
            regPresenter.destroy();
            //作业：如何通过弱引用方式，解决mvp内存泄漏
        }
    }
}
