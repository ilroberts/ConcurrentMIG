package com.kainos.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mtakaki.dropwizard.circuitbreaker.jersey.CircuitBreakerConfiguration;
import io.dropwizard.Configuration;

import javax.validation.Valid;

public class MIGConfiguration extends Configuration  {

    @Valid
    private CacheScheduleConfiguration cacheScheduleConfiguration;

    public CircuitBreakerConfiguration getCircuitBreaker() {
        return circuitBreaker;
    }

    private CircuitBreakerConfiguration circuitBreaker;

    public CacheScheduleConfiguration getCacheScheduleConfiguration() {
        return cacheScheduleConfiguration;
    }

    public void setCacheScheduleConfiguration(CacheScheduleConfiguration cacheScheduleConfiguration) {
        this.cacheScheduleConfiguration = cacheScheduleConfiguration;
    }

    @JsonProperty("metrics_enabled")
    private Boolean metricsEnabled = true;

    public Boolean metricsEnabled() {
        return metricsEnabled;
    }
}
