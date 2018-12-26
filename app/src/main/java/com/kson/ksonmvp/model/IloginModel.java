package com.kson.ksonmvp.model;

import com.kson.ksonmvp.net.RequestCallback;

import java.util.HashMap;

public interface IloginModel {

     void login(HashMap<String,String> params, RequestCallback requestCallback);
}
