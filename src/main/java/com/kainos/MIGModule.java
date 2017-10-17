package com.kainos;

import com.google.inject.AbstractModule;
import com.kainos.cache.CurrencyDescriptionCache;
import com.kainos.cache.CurrencyDescriptionCacheImpl;
import com.kainos.db.DatabaseManager;
import com.kainos.db.DatabaseManagerImpl;
import com.kainos.service.CountryService;
import com.kainos.service.CountryServiceImpl;

public class MIGModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(DatabaseManager.class).to(DatabaseManagerImpl.class);
        bind(CurrencyDescriptionCache.class).to(CurrencyDescriptionCacheImpl.class);
        bind(CountryService.class).to(CountryServiceImpl.class);
    }
}
