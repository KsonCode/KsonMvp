package com.kson.ksonmvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kson.ksonmvp.R;
import com.kson.ksonmvp.api.UserApi;
import com.kson.ksonmvp.entity.UserEntity;
import com.kson.ksonmvp.net.OkhttpCallback;
import com.kson.ksonmvp.net.OkhttpUtils;
import com.kson.ksonmvp.net.RequestCallback;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
    }

    /**
     * get
     *
     * @param view
     */
    public void getRequest(View view) {


//        OkhttpUtils.getInstance().doGet(UserApi.USER_GET_GETUSERINNFO, new OkhttpCallback() {
//            @Override
//            public void failure(String msg) {
//
//            }
//
//
//
//            @Override
//            public void success(String result) {
//
//            }
//        });


    }

    /**
     * post
     *
     * @param view
     */
    public void postRequest(View view) {


    }

    /**
     * 上传图片，文件
     *
     * @param view
     */
    public void uploadFile(View view) {
    }

    /**
     * 下载文件
     *
     * @param view
     */
    public void downloadFile(View view) {
    }
}
