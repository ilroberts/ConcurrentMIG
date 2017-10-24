package com.kainos;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.kainos.cache.CurrencyDescriptionCache;
import com.kainos.cache.CurrencyDescriptionCacheImpl;
import com.kainos.config.CacheScheduleConfiguration;
import com.kainos.config.MIGConfiguration;
import com.kainos.db.DatabaseManager;
import com.kainos.db.DatabaseManagerImpl;
import com.kainos.job.CacheManager;
import com.kainos.job.CacheManagerImpl;
import com.kainos.resource.CountryResource;
import com.kainos.resource.CountryResourceImpl;
import com.kainos.service.CountryService;
import com.kainos.service.CountryServiceImpl;
import io.dropwizard.setup.Environment;


public class MIGModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(DatabaseManager.class).to(DatabaseManagerImpl.class);
        bind(CurrencyDescriptionCache.class).to(CurrencyDescriptionCacheImpl.class);
        bind(CacheManager.class).to(CacheManagerImpl.class);
        bind(CountryService.class).to(CountryServiceImpl.class);
        bind(CountryResource.class).to(CountryResourceImpl.class);
    }

    @Provides
    public MetricRegistry providesMetrics(Environment e) {
        return e.metrics();
    }

    @Provides
    public CacheScheduleConfiguration getCacheScheduleConfiguration(MIGConfiguration c) {
        return c.getCacheScheduleConfiguration();
    }
}
