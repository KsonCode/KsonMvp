package com.kson.ksonmvp.api;

import static com.kson.ksonmvp.api.Api.BASE_URL;

public class UserApi {
    public static final String USER_REG = "http://120.27.23.105/user/reg";
    public static final String USER_LOGIN = "http://120.27.23.105/user/login";
    public static final String USER_GET_GETUSERINNFO = BASE_URL+"user/getUserInfo?uid=71";//get
    public static final String USER_POST_GETUSERINNFO = BASE_URL+"user/getUserInfo";//post
}
