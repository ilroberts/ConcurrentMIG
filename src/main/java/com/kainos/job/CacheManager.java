package com.kainos.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface CacheManager  {

    void doJob(JobExecutionContext e) throws JobExecutionException;
}
