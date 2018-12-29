package com.kson.ksonmvp.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();//得到开发者创建的request对象


        //得到原来的response对象
        Response response = chain.proceed(request);


        return response.newBuilder().removeHeader("pragma")
                .header("Cache-Control","max-age=60").build();
    }
}
