package com.kainos.job;

import com.kainos.cache.CurrencyDescriptionCache;
import com.kainos.config.CacheScheduleConfiguration;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Singleton
public final class CacheManagerImpl implements CacheManager {

    @Inject
    private CurrencyDescriptionCache cache;

    @Inject
    private CacheScheduleConfiguration configuration;

    private ScheduledExecutorService scheduledExecutorService;
    private ScheduledFuture scheduledFuture;

    @Override
    public void start() throws Exception {
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new CacheUpdater(), configuration.getDelay(), configuration.getPeriod(), TimeUnit.SECONDS);
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
