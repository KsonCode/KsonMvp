package com.kson.ksonmvp.view;

import com.kson.ksonmvp.entity.UserEntity;

public interface IloginView {
    void mobileError(String msg);
    void pwdError(String msg);
    void failure(String msg);
    void success(UserEntity userEntity);
    void successMsg(String msg);
}
