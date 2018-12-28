package com.kson.ksonmvp.contract.user;

import com.kson.ksonmvp.entity.UserEntity;
import com.kson.ksonmvp.net.RequestCallback;

import java.util.HashMap;

public interface ILoginContract {


    /**
     * p层的抽象方法
     */
    public abstract class LoginPresenter{
        public abstract void login(HashMap<String,String> params);
    }
    interface IloginModel {
        void login(HashMap<String,String> params, RequestCallback requestCallback);
    }
    interface IloginView {
        void mobileError(String msg);
        void pwdError(String msg);
        void failure(String msg);
        void success(UserEntity userEntity);
        void successMsg(String msg);
    }
}
