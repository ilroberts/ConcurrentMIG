package com.kainos;

import com.codahale.metrics.ConsoleReporter;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.kainos.config.MIGConfiguration;
import com.kainos.job.CacheManager;
import com.kainos.resource.CountryResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.concurrent.TimeUnit;


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
    }

    @Override
    public void run(MIGConfiguration configuration, Environment environment) {

        if (configuration.metricsEnabled()) {
            final ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(environment.metrics()).build();
            consoleReporter.start(30, TimeUnit.SECONDS);
        }

        environment.jersey().register(new CountryResource(environment.metrics()));
        environment.lifecycle().manage(guiceBundle.getInjector().getInstance(CacheManager.class));
    }
}
