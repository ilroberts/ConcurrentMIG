package com.kainos;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.kainos.cache.CurrencyDescriptionCache;
import com.kainos.cache.CurrencyDescriptionCacheImpl;
import com.kainos.service.CountryService;
import com.kainos.service.CountryServiceImpl;

public class MIGModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(CurrencyDescriptionCache.class).to(CurrencyDescriptionCacheImpl.class);
    }

    @Provides
    public CountryService getCountryService() {
        return new CountryServiceImpl();
    }

}
