package com.Cadient.QMobile;

import com.Cadient.QMobile.utils.Server;

import retrofit.RestAdapter;

public class Environment {
    public static final RestAdapter.LogLevel RETROFIT_LOG = RestAdapter.LogLevel.FULL;

    public static final String DYNAMIC_SERVER_URL = "https://qsymia.com/oysterlabs";
    public static final Server SERVER = Server.LIVE;
}
