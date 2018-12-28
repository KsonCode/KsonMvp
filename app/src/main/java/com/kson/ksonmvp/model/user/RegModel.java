package com.kson.ksonmvp.model.user;

import android.os.Handler;

import com.kson.ksonmvp.api.UserApi;
import com.kson.ksonmvp.contract.user.IRegContract;
import com.kson.ksonmvp.net.OkhttpCallback;
import com.kson.ksonmvp.net.OkhttpUtils;

import java.util.HashMap;

public class RegModel implements IRegContract.IRegModle {
    Handler handler = new Handler();
    private RegCallback mRegCallback;
    /**
     * 请求数据，注册接口
     * @param parmas
     */
    @Override
    public void reg(HashMap<String, String> parmas) {

        OkhttpUtils.getInstance().doPost(UserApi.USER_REG, parmas, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                if (mRegCallback!=null){
                    //产品经理定文案

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mRegCallback.onFailure("网络异常，请稍后再试");
                        }
                    });
                }
            }

            @Override
            public void success(final String result) {
                if (mRegCallback!=null){

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mRegCallback.onResponse(result);//主线程
                        }
                    });
                }
            }
        });

//        //okhttp网络框架的管理类
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(5, TimeUnit.SECONDS)
//                .connectTimeout(5, TimeUnit.SECONDS)
//                .writeTimeout(5, TimeUnit.SECONDS)
//                .build();
//
//
//        //对请求体，构建数据的过程，建造者模式
//        FormBody.Builder formBody =new FormBody.Builder();
//        for (Map.Entry<String, String> p : parmas.entrySet()) {
//
//            formBody.add(p.getKey(),p.getValue());
//
//        }
//
//
//
//        //创建请求信息对象
//        final Request request = new Request.Builder().url(UserApi.USER_REG).post(formBody.build()).build();
//
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//                if (mRegCallback!=null){
//                    //产品经理定文案
//
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            mRegCallback.onFailure("网络异常，请稍后再试");
//                        }
//                    });
//                }
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                if (mRegCallback!=null){
//                        final String result = response.body().string();
//
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            mRegCallback.onResponse(result);//主线程
//                        }
//                    });
//                    }
//
//            }
//        });

    }

    @Override
    public void login(HashMap<String, String> parmas) {

    }

    public void setmRegCallback(RegCallback mRegCallback) {
        this.mRegCallback = mRegCallback;
    }

    public interface RegCallback{
        void onFailure(String msg);
        void onResponse(String result);
    }
}
