package com.Cadient.QMobile.view.fragment.login;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.handler.BaseHandler;
import com.Cadient.QMobile.handler.BaseRemoteListener;
import com.Cadient.QMobile.model.remote.UserProfile;
import com.Cadient.QMobile.model.remote.dynamicserver.DynamicServer;
import com.Cadient.QMobile.utils.ErrorCode;
import com.Cadient.QMobile.utils.GAUtils;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.utils.ServerHelpUtils;
import com.Cadient.QMobile.view.activity.StartActivity;
import com.Cadient.QMobile.view.fragment.AbstractFragment;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.Cadient.QMobile.controller.transit.FragmentAction.AUTHORISATION_FAIL_ACTION;

/**
 * Created by Alexey Vereshchaga on 10.09.14.
 */
public class SplashFragment extends AbstractFragment<StartActivity> {

    private final static int SPLASH_SCREEN_DELAY = 3000;
    private final static int REQUEST_TIMEOUT = 15000;

    private RetrofitSpiceRequest request;

    private ImageView slidingImage;

    @Override
    protected int getViewLayout() {
        return R.layout.fragm_splash;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().getBoolean(LoginFragment.LOGIN_BUTTON_STATE_KEY, false)) {
            startMainActivityAfterPause(0);
        } else if (TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_URL_KEY) != null
                && !TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_URL_KEY).isEmpty()) {

            request = getBaseActivity().getDataManager().readUserProfile(new BaseHandler<UserProfile>(
                    new RemoteCallbackListener(getActivity()), UserProfile.class));
        } else {
            getBaseActivity().getDataManager().readDynamicServer(new Callback<DynamicServer>() {
                @Override
                public void success(DynamicServer dynamicServer, Response response) {
                    ServerHelpUtils.writeCurrentServer(dynamicServer);
                    request = getBaseActivity().getDataManager().readUserProfile(new BaseHandler<UserProfile>(
                            new RemoteCallbackListener(getActivity()), UserProfile.class));
                }

                @Override
                public void failure(RetrofitError error) {
                    switchStartFragment();
                    Crouton.makeText(getActivity(), getResources().getString(R.string.login_no_internet_connection), Style.ALERT).show();
                }
            });
        }
    }

    @Override
    protected void assignViews(View view) {
        slidingImage = (ImageView) view.findViewById(R.id.iv_sliding_image);
        HelpUtils.animateAndSlideShow(slidingImage);
    }

    private void switchStartFragment() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(HelpUtils.AUTHORISATION_FAIL_KEY, true);
        getTransitManager().switchFragment(SplashFragment.this, AUTHORISATION_FAIL_ACTION, bundle);
    }

    private void startMainActivityAfterPause(long periodOfTime) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                getBaseActivity().startMainActivity();
            }
        }, SPLASH_SCREEN_DELAY - periodOfTime);
    }

    private class RemoteCallbackListener extends BaseRemoteListener<UserProfile> {

        private long startRequestTime;

        protected RemoteCallbackListener(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onStartTask() {
            startRequestTime = System.currentTimeMillis();
            //cancel request after REQUEST_TIMEOUT
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    request.cancel();
                }
            }, REQUEST_TIMEOUT);
        }

        @Override
        public void onSuccess(UserProfile result) {
            long periodOfTime = System.currentTimeMillis() - startRequestTime;
            if (result != null) {
                if (periodOfTime < SPLASH_SCREEN_DELAY) {
                    startMainActivityAfterPause(periodOfTime);
                } else {
                    getBaseActivity().startMainActivity();
                }
            } else {
                super.onFailure(404);
            }
        }

        @Override
        public void onFailure(Integer failureCode) {
            if (getTransitManager().getCurrentFragment().getClass().getSimpleName().equals(SplashFragment.class.getSimpleName())) {
                switchStartFragment();
            }
            super.onFailure(failureCode);
            if (failureCode.equals(ErrorCode.NOT_FOUND.getCode()) || failureCode.equals(ErrorCode.UNAUTHORIZED.getCode())) {
                Crouton.makeText(getActivity(), ErrorCode.UNAUTHORIZED.getMessage(), Style.ALERT).show();
            }
            if (failureCode.equals(ErrorCode.REQUEST_CANCELLED.getCode())) {
                Crouton.makeText(getActivity(), getResources().getString(R.string.login_no_internet_connection), Style.ALERT).show();
            }
        }

        @Override
        public void onFailure(String message) {
            switchStartFragment();
        }

//        private void startMainActivity() {
//            if (getArguments() != null && getArguments().getBoolean(LoginFragment.LOGIN_BUTTON_STATE_KEY, false)) {
//                GAUtils.trackEvent(GAUtils.LOGIN_ACTION);
//            }
//            getBaseActivity().startMainActivity();
//        }
    }
}

