package com.kson.ksonmvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kson.ksonmvp.R;
import com.kson.ksonmvp.contract.user.ILoginContract;
import com.kson.ksonmvp.entity.UserEntity;
import com.kson.ksonmvp.net.OkhttpUtils;
import com.kson.ksonmvp.presenter.LoginPresenter;
import com.kson.ksonmvp.view.IloginView;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements ILoginContract.IloginView {

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        presenter = new LoginPresenter(this);
    }

    /**
     * 点击，登录
     *
     * @param view
     */
    public void login(View view) {

        HashMap<String, String> params = new HashMap<>();
        params.put("mobile", "18612991023");
        params.put("password", "222222");

        if (presenter != null) {
            presenter.login(params);
        }

    }

    @Override
    public void mobileError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(UserEntity userEntity) {
//        String uid = userEntity.data.uid;
        Toast.makeText(this, userEntity.msg + "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void successMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        OkhttpUtils.getInstance().cancelAllTask();//取消任务
    }
}
