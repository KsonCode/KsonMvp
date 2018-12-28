package com.kson.ksonmvp.presenter;

import com.kson.ksonmvp.contract.user.ILoginContract;
import com.kson.ksonmvp.entity.UserEntity;
import com.kson.ksonmvp.model.LoginModel;
import com.kson.ksonmvp.net.RequestCallback;
import com.kson.ksonmvp.utils.ValidatorUtil;

import java.util.HashMap;

public class LoginPresenter extends ILoginContract.LoginPresenter {

    private LoginModel loginModel;
    private ILoginContract.IloginView iloginView;

    public LoginPresenter(ILoginContract.IloginView iloginView){
        loginModel = new LoginModel();
        this.iloginView = iloginView;

    }


    public void login(HashMap<String,String> params) {
        //正则表达式校验

        String mobile =params.get("mobile");
        String password = params.get("password");
        if (!ValidatorUtil.isMobile(mobile)){
            if (iloginView!=null){
                iloginView.mobileError("请输入合法手机号");
            }
            return;//返回
        }

        //调用loginmodel的数据处理的方法，登录的方法
        if (loginModel!=null){
            loginModel.login(params, new RequestCallback() {
                @Override
                public void failure(String msg) {
                    if (iloginView!=null)
                    iloginView.failure(msg);
                }

                @Override
                public void successMsg(String msg) {
                    if (iloginView!=null)
                    iloginView.successMsg(msg);

                }

                @Override
                public void success(UserEntity userEntity) {
                    if (iloginView!=null)
                    iloginView.success(userEntity);

                }

                @Override
                public void success(String result) {

                }
            });
        }
    }
}
