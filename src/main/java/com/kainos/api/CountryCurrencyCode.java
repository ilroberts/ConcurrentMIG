package com.kainos.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryCurrencyCode {

    @JsonProperty
    private String country;

    @JsonProperty
    private String currencyCode;

    public String getCountry() {
        return country;
    }

    public CountryCurrencyCode(String country, String code) {
        this.country = country;
        this.currencyCode = code;
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
