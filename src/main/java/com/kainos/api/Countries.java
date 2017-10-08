package com.kainos.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Countries {

    private List<String> countries;

    @JsonProperty
    public List<String> getCountries() {
        return countries;
    }

    @JsonProperty
    public void setCountries(List<String> countryList) {
        this.countries = countryList;
    }
}
