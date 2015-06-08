package com.Cadient.QMobile.utils;

import com.Cadient.QMobile.Environment;
import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.model.remote.dynamicserver.Configuration;
import com.Cadient.QMobile.model.remote.dynamicserver.DynamicServer;

/**
 * Created by Alexey Vereshchaga on 08.12.14.
 */
public class ServerHelpUtils {

    public static final String SERVER_URL_KEY = "com.Cadient.QMobile.server_url";
    public static final String SERVER_FORGOT_PASSWORD_URL_KEY = "com.Cadient.QMobile.forgot_password_URL";
    public static final String SERVER_SIGNUP_URL_KEY = "com.Cadient.QMobile.signup_URL";
    public static final String SERVER_ABOUT_URL_KEY = "com.Cadient.QMobile.about_URL";
    public static final String SERVER_MY_ACCOUNT_URL_KEY = "com.Cadient.QMobile.my_account_URL";

    public static void writeCurrentServer(DynamicServer dynamicServer) {
        String serverUrl = null;
        if (dynamicServer != null) {
            switch (Environment.SERVER) {
                case LIVE:
                    serverUrl = dynamicServer.getConfigurationLive().getApiServer();
                    setServerUrl(dynamicServer.getConfigurationLive());
                    break;
                case DEV:
                    serverUrl = dynamicServer.getConfigurationDev().getApiServer();
                    setServerUrl(dynamicServer.getConfigurationDev());
                    break;
            }
        }
        if (serverUrl != null && !serverUrl.isEmpty()) {
            TheApplication.getInstance().setServerUrl(ServerHelpUtils.SERVER_URL_KEY, serverUrl);
        }
    }

    private static void setServerUrl(Configuration configuration) {
        TheApplication application = TheApplication.getInstance();
        application.setServerUrl(ServerHelpUtils.SERVER_ABOUT_URL_KEY, configuration.getAboutUrl());
        application.setServerUrl(ServerHelpUtils.SERVER_MY_ACCOUNT_URL_KEY, configuration.getMyAccountUrl());
        application.setServerUrl(ServerHelpUtils.SERVER_SIGNUP_URL_KEY, configuration.getSignUpUrl());
        application.setServerUrl(ServerHelpUtils.SERVER_FORGOT_PASSWORD_URL_KEY, configuration.getForgotPasswordURL());
    }

    public static void deleteAllServerUrls() {
        TheApplication application = TheApplication.getInstance();
        application.deleteServerUrl(ServerHelpUtils.SERVER_URL_KEY);
        application.deleteServerUrl(ServerHelpUtils.SERVER_FORGOT_PASSWORD_URL_KEY);
        application.deleteServerUrl(ServerHelpUtils.SERVER_SIGNUP_URL_KEY);
        application.deleteServerUrl(ServerHelpUtils.SERVER_ABOUT_URL_KEY);
        application.deleteServerUrl(ServerHelpUtils.SERVER_MY_ACCOUNT_URL_KEY);
    }
}
