package com.Cadient.QMobile.view.fragment.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.controller.transit.FragmentAction;
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

/**
 * Created by Alexey Vereshchaga on 05.09.14.
 */
public class LoginFragment extends AbstractFragment<StartActivity> implements View.OnClickListener {

    public static final String LOGIN_BUTTON_STATE_KEY = "com.Cadient.QMobile.login_button_state";
    private final static int REQUEST_TIMEOUT = 15000;

    private Boolean isLoginButtonClicked;
    private String login;
    private String password;
    private RetrofitSpiceRequest request;
    private long mLastClickTime = 0;
    private boolean isFail;

    private Button btnLogin;
    private TextView tvForgotPassword;
    private TextView tvRegistration;
    private EditText etPass;
    private EditText etLogin;
    private RelativeLayout ivScale;
    private ImageView slidingImage;
    private ProgressBar progressBar;

    @Override
    protected int getViewLayout() {
        return R.layout.fragm_login;
    }

    @Override
    protected void assignViews(View view) {
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        tvForgotPassword = (TextView) view.findViewById(R.id.tv_forgot_pass);
        tvRegistration = (TextView) view.findViewById(R.id.tv_sign_up);
        etPass = (EditText) view.findViewById(R.id.et_pass);
        etLogin = (EditText) view.findViewById(R.id.et_login);
        ivScale = (RelativeLayout) view.findViewById(R.id.rl_title);
        slidingImage = (ImageView) view.findViewById(R.id.iv_sliding_image);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        HelpUtils.animateAndSlideShow(slidingImage);
    }

    @Override
    protected void initView(View view) {
        isLoginButtonClicked = false;
        if (TheApplication.getInstance().getPasswordKey() != null
                && TheApplication.getInstance().getUserKey() != null
                && !(getArguments() != null
                && getArguments().getBoolean(HelpUtils.AUTHORISATION_FAIL_KEY))
                && !isFail) {
            startSplashFragment();
        } else {
            getBaseActivity().getDataManager().readDynamicServer(new Callback<DynamicServer>() {
                @Override
                public void success(DynamicServer dynamicServer, Response response) {
                    ServerHelpUtils.writeCurrentServer(dynamicServer);
                    getBaseActivity().getObservable().notifyObservers();
                }

                @Override
                public void failure(RetrofitError error) {
                    Crouton.makeText(getActivity(), createErrorMessage(error.getMessage()), Style.ALERT).show();
                }
            });
        }
        isFail = false;
        btnLogin.setOnClickListener(this);
        tvRegistration.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        tvForgotPassword.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.VISIBLE);
        etPass.setText(TheApplication.getInstance().getPasswordKey());
        etLogin.setText(TheApplication.getInstance().getUserKey());
        view.findViewById(R.id.ll_sign_up).setVisibility(View.VISIBLE);
        view.findViewById(R.id.ll_fields).setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                isLoginButtonClicked = true;
                login = etLogin.getText().toString();
                password = etPass.getText().toString();
                setLoadingState(true);
                if (TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_URL_KEY) != null
                        && !TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_URL_KEY).isEmpty()) {
                    logIn(login, password);
                    HelpUtils.hideKeyBoard(getActivity());
                } else {
                    getBaseActivity().getDataManager().readDynamicServer(new Callback<DynamicServer>() {
                        @Override
                        public void success(DynamicServer dynamicServer, Response response) {
                            ServerHelpUtils.writeCurrentServer(dynamicServer);
                            logIn(login, password);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            setLoadingState(false);
                            isFail = true;
                            Crouton.makeText(getActivity(), createErrorMessage(error.getMessage()), Style.ALERT).show();
                        }
                    });
                }
                break;
            case R.id.tv_sign_up:
                getTransitManager().switchFragment(RegistrationFragment.class, createBundle(
                        TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_SIGNUP_URL_KEY),
                        getResources().getString(R.string.registration_header)));
                break;
            case R.id.tv_forgot_pass:
                getTransitManager().switchFragment(RegistrationFragment.class, createBundle(
                        TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_FORGOT_PASSWORD_URL_KEY),
                        getResources().getString(R.string.forgot_password_header)));
                break;
        }
    }

    private Bundle createBundle(String url, String header) {
        Bundle bundle = new Bundle();
        bundle.putString(HelpUtils.REGISTRATION_KEY, url);
        bundle.putString(HelpUtils.REGISTRATION_HEADER_VALUE, header);
        return bundle;
    }

    private void logIn(String loginStr, String passStr) {
        int result = HelpUtils.validateLogin(loginStr);
        if (result == 0) {
            result = HelpUtils.validatePass(passStr);
        }
        if (result == 0) {
            TheApplication.getInstance().setPasswordKey(passStr);
            TheApplication.getInstance().setUserKey(loginStr);
            sendRequest();
//            startSplashFragment();
        } else {
            isLoginButtonClicked = false;
            setLoadingState(false);
            Crouton.makeText(getActivity(), getResources().getString(result), Style.ALERT).show();
        }
    }

    private void sendRequest() {
        request = getBaseActivity().getDataManager().readUserProfile(new BaseHandler<UserProfile>(
                new RemoteCallbackListener(getActivity()), UserProfile.class));
    }

    private void startSplashFragment() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(LOGIN_BUTTON_STATE_KEY, isLoginButtonClicked);
        if (getTransitManager() != null
                && getTransitManager().getCurrentFragment().getClass().getSimpleName().equals(LoginFragment.class.getSimpleName())) {
            getTransitManager().switchFragment(this, FragmentAction.SPLASH_ACTION, bundle);
        }
    }

    private class RemoteCallbackListener extends BaseRemoteListener<UserProfile> {

        protected RemoteCallbackListener(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onStartTask() {
            //cancel request after REQUEST_TIMEOUT
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    request.cancel();
                }
            }, REQUEST_TIMEOUT);
        }

        @Override
        public void onSuccess(UserProfile result) {
//            progressBar.setVisibility(View.GONE);
            if (result != null) {
                GAUtils.trackEvent(GAUtils.LOGIN_ACTION);
                startSplashFragment();
            } else {
                super.onFailure(404);
            }
        }

        @Override
        public void onFailure(Integer failureCode) {
            isFail = true;
            setLoadingState(false);
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
            isFail = true;
            setLoadingState(false);
//            Crouton.makeText(getActivity(), getResources().getString(R.string.login_no_internet_connection), Style.ALERT).show();
        }
    }

    private void setLoadingState(Boolean isItLoading) {
        btnLogin.setEnabled(!isItLoading);
        btnLogin.setClickable(!isItLoading);
        etLogin.setEnabled(!isItLoading);
        etPass.setEnabled(!isItLoading);
        progressBar.setVisibility(isItLoading ? View.VISIBLE : View.GONE);
        btnLogin.setText(isItLoading ? "" : getResources().getString(R.string.login_button));
        tvForgotPassword.setClickable(!isItLoading);
        tvRegistration.setClickable(!isItLoading);
    }

    private String createErrorMessage(String message) {
        return message.matches("(Unable to resolve).*") ? getResources().getString(R.string.login_no_internet_connection) : message;
    }
}
