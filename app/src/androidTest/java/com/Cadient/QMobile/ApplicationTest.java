package com.Cadient.QMobile;

import android.test.InstrumentationTestCase;

import com.Cadient.QMobile.network.NutrioApi;
import com.Cadient.QMobile.network.RetrofitService;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ApplicationTest extends InstrumentationTestCase {

    //External User ID asdf123  Internal User ID test_user@nutrio.com 5b6b224e-5350-4757-97da-a0e82beaa4ed
//    protected String userApiKey = "68E9AB11-256C-40BE-86FF-DDFB7D9F82FE";
    private String userEmail = "mail1@gmail.com";
    private String userPass = "11111";
    protected NutrioApi service;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        TheApplication.getInstance().setUserKey(userEmail);
        TheApplication.getInstance().setPasswordKey(userPass);
        service = new RetrofitService().createService().create(NutrioApi.class);
    }

    protected static String createRandomEmail() {
        SecureRandom random = new SecureRandom();
        StringBuilder builder = new StringBuilder();
        builder.append(new BigInteger(40, random).toString(32));
        return builder.append("@mail.com").toString();
    }
}