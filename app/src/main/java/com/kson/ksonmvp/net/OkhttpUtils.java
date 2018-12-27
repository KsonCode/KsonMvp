package com.kson.ksonmvp.net;

import android.os.Handler;

import com.kson.ksonmvp.api.UserApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpUtils {

    Handler handler = new Handler();

    private OkHttpClient okHttpClient;

    private static OkhttpUtils mInstance;//私有属性


    private OkhttpUtils() {//私有构造方法

        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();//链式调用，构建者模式


    }


    /**
     * 创建实例,双重检验锁
     *
     * @return
     */
    public static OkhttpUtils getInstance() {

        if (mInstance == null) {
            synchronized (OkhttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkhttpUtils();
                }
            }
        }

        return mInstance;

    }

    /**
     * get请求方式
     */
    public void doGet(String url, final RequestCallback requestCallback) {


        Request request = new Request.Builder().url(url)
                .get().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallback.failure("网络异常");
                        }
                    });

                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (requestCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                requestCallback.success(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });


    }

    /**
     * Post请求方式
     */
    public void doPost(String url, HashMap<String, String> parmas) {

        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String, String> map : parmas.entrySet()) {
            builder.add(map.getKey(), map.getValue());

        }

        RequestBody requestBody = builder.build();

        Request request = new Request.Builder().url(url)
                .post(requestBody).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });


    }


}
