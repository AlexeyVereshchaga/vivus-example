package com.Cadient.QMobile.model.remote.dynamicserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alex on 30.11.2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicServer {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("live")
    private String liveProxyURL;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("dev")
    private String devProxyURL;
    @JsonProperty("configuration_AndroidLive")
    private Configuration configurationLive;
    @JsonProperty("configuration_AndroidDev")
    private Configuration configurationDev;

    public String getDevProxyURL() {
        return devProxyURL;
    }

    public void setDevProxyURL(String devProxyURL) {
        this.devProxyURL = devProxyURL;
    }

    public String getLiveProxyURL() {
        return liveProxyURL;
    }

    public void setLiveProxyURL(String liveProxyURL) {
        this.liveProxyURL = liveProxyURL;
    }

    public Configuration getConfigurationLive() {
        return configurationLive;
    }

    public void setConfigurationLive(Configuration configurationLive) {
        this.configurationLive = configurationLive;
    }

    public Configuration getConfigurationDev() {
        return configurationDev;
    }

    public void setConfigurationDev(Configuration configurationDev) {
        this.configurationDev = configurationDev;
    }
}
