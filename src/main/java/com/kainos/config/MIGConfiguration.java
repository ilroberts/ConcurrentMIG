package com.kainos.config;

import io.dropwizard.Configuration;

import javax.validation.Valid;

public class MIGConfiguration extends Configuration  {

    @Valid
    private CacheScheduleConfiguration cacheScheduleConfiguration;

    public CacheScheduleConfiguration getCacheScheduleConfiguration() {
        return cacheScheduleConfiguration;
    }

    public void setCacheScheduleConfiguration(CacheScheduleConfiguration cacheScheduleConfiguration) {
        this.cacheScheduleConfiguration = cacheScheduleConfiguration;
    }
}
