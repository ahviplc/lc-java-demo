package com.lc.offer.callbackDiy;

/**
 * myCallBack 接口
 */
public interface MyCallBack {
    void onSuccess(String data);

    void onFailed(String error);

    void onFinally();
}
