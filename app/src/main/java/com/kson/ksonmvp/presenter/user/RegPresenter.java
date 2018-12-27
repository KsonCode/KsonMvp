package com.kson.ksonmvp.presenter.user;

import com.kson.ksonmvp.contract.user.IRegContract;
import com.kson.ksonmvp.model.user.RegModel;
import com.kson.ksonmvp.utils.ValidatorUtil;

import java.util.HashMap;

public class RegPresenter extends IRegContract.RegPresenter {

    private RegModel mRegModel;
    private IRegContract.IRegView iRegView;

    /**
     * 绑定view
     * @param iRegView
     */
    public RegPresenter(IRegContract.IRegView iRegView) {
        this.mRegModel = new RegModel();
        this.iRegView = iRegView;
    }


    /**
     * @param params
     */
    @Override
    public void register(HashMap<String, String> params) {
        String mobile = params.get("mogile");
        if (!ValidatorUtil.isMobile(mobile)) {
            if (iRegView != null) {
                iRegView.mobileError("手机号不合法");
            }
            return;
        }

        if (mRegModel != null) {
            mRegModel.reg(params);
            mRegModel.setmRegCallback(new RegModel.RegCallback() {
                @Override
                public void onFailure(String msg) {

                    if (iRegView != null) {
                        iRegView.failure(msg);
                    }


                }

                @Override
                public void onResponse(String result) {
                    if (iRegView != null) {
                        iRegView.success(result);
                    }

                }
            });

        }
    }

    @Override
    public void login(HashMap<String, String> params) {

    }

    /**
     * 销毁方法，解决内存泄漏,解绑
     */
    public void destroy(){

        if (iRegView!=null){
            iRegView = null;
        }

    }
}
