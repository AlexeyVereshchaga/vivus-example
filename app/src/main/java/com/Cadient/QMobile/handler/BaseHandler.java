package com.Cadient.QMobile.handler;

import android.util.Log;

import com.Cadient.QMobile.events.IRemoteCallbackListener;
import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.utils.ErrorCode;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.listener.RequestProgress;
import com.octo.android.robospice.request.listener.RequestProgressListener;
import com.octo.android.robospice.request.listener.RequestStatus;

import java.util.Map;

import retrofit.RetrofitError;

/**
 * Created by ivan on 8/26/14.
 */
public class BaseHandler<T> implements RequestListener<T>, RequestProgressListener {

    private static final String TAG = BaseHandler.class.getSimpleName();

    protected IRemoteCallbackListener callbackListener;

    private final Class<T> clazz;

    public BaseHandler(IRemoteCallbackListener callbackListener, Class<T> clazz) {
        this.callbackListener = callbackListener;
        this.clazz = clazz;
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        String spiceExceptionMessage = spiceException.getMessage();
        Log.i(TAG, "Error: " + spiceExceptionMessage);
        Integer errorCode = ErrorCode.getCodeByMessage(spiceExceptionMessage);
        if (spiceException.getCause() != null) {
            RetrofitError retrofitError = null;
            T body = null;
            try {
                retrofitError = (RetrofitError) spiceException.getCause();
                body = (T) retrofitError.getBodyAs(clazz);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            callbackListener.onFailure(body != null ? getErrorBodyMessage(body) : spiceExceptionMessage);
            if (retrofitError != null) {
                Integer statusCode = null;
                if (retrofitError.getResponse() != null) {
                    statusCode = retrofitError.getResponse().getStatus();
                }

                if (statusCode != null) {
                    errorCode = statusCode;
                }
            }
        }
        if (errorCode != null) {
            callbackListener.onFailure(errorCode);
        }
        callbackListener.onFinishTask();
    }

    @Override
    public void onRequestSuccess(T response) {
        Log.i(TAG, "Response: " + response.toString());
        if (response != null) {
            callbackListener.onSuccess(response);
        }
        callbackListener.onFinishTask();
    }

    protected String getErrorBodyMessage(T body) {
        return null;
    }

    protected StringBuilder getErrorMessage(Map<String, String[]> map, StringBuilder builder) {
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            builder.append(entry.getKey()).append(": ");
            for (String s : entry.getValue()) {
                builder.append(s).append("\n");
            }
        }
        return builder;
    }

    protected StringBuilder getBaseClassError(BaseModel body) {
        StringBuilder builder = new StringBuilder();
        if (body.getError() != null && !body.getError().isEmpty()) {
            builder.append(body.getClass().getSimpleName()).append(": \n");
            getErrorMessage(body.getError(), builder);
        }
        return builder;
    }

    @Override
    public void onRequestProgressUpdate(RequestProgress progress) {
        if (progress.getStatus() == RequestStatus.PENDING) {
            callbackListener.onStartTask();
        }
    }
}
