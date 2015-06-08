package com.Cadient.QMobile.network;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;

/**
 * Created by Alexey Vereshchaga on 01.12.14.
 */
public class RetrofitClient extends UrlConnectionClient {

    private final static int REQUEST_TIMEOUT = 15000;

    @Override
    protected HttpURLConnection openConnection(Request request) throws IOException {
        HttpURLConnection connection = super.openConnection(request);

        connection.setConnectTimeout(REQUEST_TIMEOUT);

        return connection;
    }
}
