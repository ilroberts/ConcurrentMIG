package com.kainos;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.kainos.config.MIGConfiguration;
import com.kainos.job.CacheManagerImpl;
import com.kainos.resource.CountryResource;
import de.spinscale.dropwizard.jobs.GuiceJobsBundle;
import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.JobsBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class MIGApplication extends Application<MIGConfiguration> {

    private GuiceBundle<MIGConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new MIGApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<MIGConfiguration> bootstrap) {

        guiceBundle = GuiceBundle.<MIGConfiguration>newBuilder()
                .addModule(new MIGModule())
                .setConfigClass(MIGConfiguration.class)
                .build();
        bootstrap.addBundle(guiceBundle);

        Job cacheJob = new CacheManagerImpl();
        bootstrap.addBundle(new JobsBundle(cacheJob));

        GuiceJobsBundle guiceJobsBundle = new GuiceJobsBundle(guiceBundle.getInjector());
        bootstrap.addBundle(guiceJobsBundle);
    }

    @Override
    public void run(MIGConfiguration configuration, Environment environment) {

        environment.jersey().register(new CountryResource());
    }
}
