package com.genuinecoder.datafetch.model;

public class CovidDataModel {
    private GlobalData globalData;
    private CountryData countryData;

    public CovidDataModel(GlobalData globalData, CountryData countryData) {
        this.globalData = globalData;
        this.countryData = countryData;
    }

    public GlobalData getGlobalData() {
        return globalData;
    }

    public CountryData getCountryData() {
        return countryData;
    }

    @Override
    public String toString() {
        return "CovidDataModel{" +
                "globalData=" + globalData +
                ", countryData=" + countryData +
                '}';
    }
}
