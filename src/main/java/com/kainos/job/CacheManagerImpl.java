package com.kainos.job;

import com.kainos.cache.CurrencyDescriptionCache;
import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.DelayStart;
import de.spinscale.dropwizard.jobs.annotations.Every;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.inject.Inject;

@Every("30s")
@DelayStart("5s")
public final class CacheManagerImpl extends Job implements CacheManager {

    @Inject
    CurrencyDescriptionCache cache;

    @Override
    public void doJob(JobExecutionContext e) throws JobExecutionException {
        System.out.println("ping - cache is " + cache);
        cache.rebuild();
    }
}
