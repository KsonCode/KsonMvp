package com.kson.ksonmvp.model;

import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.kson.ksonmvp.api.UserApi;
import com.kson.ksonmvp.contract.user.ILoginContract;
import com.kson.ksonmvp.entity.UserEntity;
import com.kson.ksonmvp.net.OkhttpCallback;
import com.kson.ksonmvp.net.OkhttpUtils;
import com.kson.ksonmvp.net.RequestCallback;

import java.util.HashMap;

public class LoginModel implements ILoginContract.IloginModel {

    Handler handler = new Handler();


    @Override
    public void login(HashMap<String, String> params, final RequestCallback callback) {


        OkhttpUtils.getInstance().doPost(UserApi.USER_LOGIN, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                if (callback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.failure("网络可能不稳定，请稍后再试");
                        }
                    });

                }
            }

            @Override
            public void success(String result) {
                if (!TextUtils.isEmpty(result)) {
                    paserResult(result, callback);
                }
            }
        });



    }

    /**
     * 解析数据变成对象
     *
     * @param result
     * @param callback
     */
    private void paserResult(String result, final RequestCallback callback) {


        final UserEntity userEntity = new Gson().fromJson(result, UserEntity.class);

        if (callback != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                        callback.success(userEntity);
                }
            });

        }


    }


}
