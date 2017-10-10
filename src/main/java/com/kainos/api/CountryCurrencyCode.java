package com.kainos.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryCurrencyCode {

    @JsonProperty
    private String country;

    @JsonProperty
    private String currencyCode;
    @JsonProperty
    private String currencyCodeDescription;

    public CountryCurrencyCode(String country, String code, String currencyCodeDescription) {
        this.country = country;
        this.currencyCode = code;
        this.currencyCodeDescription = currencyCodeDescription;
    }

    public void setCurrencyCodeDescription(String currencyCodeDescription) {
        this.currencyCodeDescription = currencyCodeDescription;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
