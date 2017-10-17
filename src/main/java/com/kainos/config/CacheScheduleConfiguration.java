package com.kainos.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class CacheScheduleConfiguration extends Configuration {

    @NotNull
    private int delay;

    @NotNull
    private int period;

    @JsonProperty
    public int getDelay() {
        return delay;
    }

    @JsonProperty
    public void setDelay(int delay) {
        this.delay = delay;
    }

    @JsonProperty
    public int getPeriod() {
        return period;
    }

    @JsonProperty
    public void setPeriod(int period) {
        this.period = period;
    }
}
