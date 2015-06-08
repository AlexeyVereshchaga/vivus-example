package com.Cadient.QMobile.events;

/**
 * Created by ivan on 8/26/14.
 */
public interface IRemoteCallbackListener<T> {

    void onStartTask();

    void onSuccess(T result);

    void onFailure(String message);

    void onFailure(Integer failureCode);

    void onError(T error);

    /**
     * End remote call process, call in any case after onStart,onSuccess,onFailure,onServerError
     */
    void onFinishTask();

}
