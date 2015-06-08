package com.Cadient.QMobile.network;

import android.util.Base64;

import com.Cadient.QMobile.Environment;
import com.Cadient.QMobile.TheApplication;
import com.octo.android.robospice.retrofit.RetrofitJackson2SpiceService;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;

/**
 * Created by Alexey Vereshchaga on 12.08.14.
 */
public class RetrofitService extends RetrofitJackson2SpiceService {

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(NutrioApi.class);
    }

    @Override
    protected String getServerUrl() {
        return CustomEndpoint.DEFAULT_URL;
    }

    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        final RestAdapter.Builder result = super.createRestAdapterBuilder();
        result.setLogLevel(Environment.RETROFIT_LOG);
        result.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader(
                        "Authorization",
                        encodeCredentials(TheApplication.getInstance().getUserKey() + ":" + TheApplication.getInstance().getPasswordKey()));
                request.addHeader("Accept", "application/json");
                request.addHeader("Content-Type", "application/json");
            }
        });
        result.setEndpoint(new CustomEndpoint());
        return result;
    }

    protected String encodeCredentials(String key) {
        return "Basic " + Base64.encodeToString(key.getBytes(), Base64.NO_WRAP);
    }

//    @Override
//    public CacheManager createCacheManager(Application application) throws CacheCreationException {
//        CacheManager cacheManager = new CacheManager();
//        List<Class<?>> classCollection = new ArrayList<Class<?>>();
//
//        // add persisted classes to class collection
//        classCollection.add(UserRemote.class);
//        classCollection.add(MealRating.class);
//
//        // init
//        cacheManager.addPersister(new InDatabaseObjectPersisterFactory(application,
//                new RoboSpiceDatabaseHelper(application, DataBaseUtils.DATABASE_NAME, DataBaseUtils.DATABASE_VERSION), classCollection));
//        return cacheManager;
//    }

    public RestAdapter createService() {
        return createRestAdapterBuilder().build();
    }
}
