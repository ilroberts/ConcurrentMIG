package com.kainos.job;

import com.kainos.cache.CurrencyDescriptionCache;
import com.kainos.config.CacheScheduleConfiguration;
import io.dropwizard.lifecycle.Managed;

import javax.inject.Inject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class CacheManagerImpl implements Managed, CacheManager {

    @Inject
    private CurrencyDescriptionCache cache;

    private ScheduledExecutorService scheduledExecutorService;
    private ScheduledFuture scheduledFuture;

    @Inject
    public CacheManagerImpl(CacheScheduleConfiguration c) {
        c.getDelay();
    }

    @Override
    public void start() throws Exception {
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new CacheUpdater(), 5, 10, TimeUnit.SECONDS);
    }

    @Override
    public void stop() throws Exception {

    }

    public class CacheUpdater implements Runnable {

        @Override
        public void run() {
            cache.rebuild();
        }
    }

}
