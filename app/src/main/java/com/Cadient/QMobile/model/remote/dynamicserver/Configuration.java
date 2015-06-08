package com.Cadient.QMobile.model.remote.dynamicserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alexey Vereshchaga on 08.12.14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {
    @JsonProperty("API_server")
    private String ApiServer;
    @JsonProperty("forgot_password_URL")
    private String forgotPasswordURL;
    @JsonProperty("signup_URL")
    private String signUpUrl;
    @JsonProperty("about_URL")
    private String aboutUrl;
    @JsonProperty("my_account_URL")
    private String myAccountUrl;

    public String getMyAccountUrl() {
        return myAccountUrl;
    }

    public void setMyAccountUrl(String myAccountUrl) {
        this.myAccountUrl = myAccountUrl;
    }

    public String getApiServer() {
        return ApiServer;
    }

    public void setApiServer(String apiServer) {
        ApiServer = apiServer;
    }

    public String getForgotPasswordURL() {
        return forgotPasswordURL;
    }

    public void setForgotPasswordURL(String forgotPasswordURL) {
        this.forgotPasswordURL = forgotPasswordURL;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public String getAboutUrl() {
        return aboutUrl;
    }

    public void setAboutUrl(String aboutUrl) {
        this.aboutUrl = aboutUrl;
    }
}
