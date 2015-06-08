package com.Cadient.QMobile.network;

import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.utils.ServerHelpUtils;

import retrofit.Endpoint;

/**
 * Created by Alexey Vereshchaga on 03.12.14.
 */
public class CustomEndpoint implements Endpoint {

    public static final String DEFAULT_URL = "http://dev.qandmesupport.com/";

    @Override
    public String getUrl() {
        return (TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_URL_KEY) != null
                && !TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_URL_KEY).isEmpty())
                ? TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_URL_KEY)
                : DEFAULT_URL;
    }

    @Override
    public String getName() {
        return "default";
    }
}
