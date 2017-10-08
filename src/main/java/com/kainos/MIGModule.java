package com.kainos;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.kainos.service.CountryService;
import com.kainos.service.CountryServiceImpl;

public class MIGModule extends AbstractModule {

    @Override
    protected void configure() {
        // anything you'd like to configure
    }

    @Provides
    public CountryService getCountryService() {
        return new CountryServiceImpl();
    }

}
