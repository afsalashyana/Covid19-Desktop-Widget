package com.genuinecoder.config;

public class ConfigModel {
    private int refreshIntervalInSeconds;
    private String countryName;
    private String countryCode;

    public ConfigModel() {
        refreshIntervalInSeconds = 300;
        countryName = "India";
        countryCode = "IN";
    }

    public int getRefreshIntervalInSeconds() {
        return refreshIntervalInSeconds;
    }

    public void setRefreshIntervalInSeconds(int refreshIntervalInSeconds) {
        this.refreshIntervalInSeconds = refreshIntervalInSeconds;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
