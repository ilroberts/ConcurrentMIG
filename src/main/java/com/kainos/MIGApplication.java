package com.kainos;

import com.codahale.metrics.JmxReporter;
import com.github.mtakaki.dropwizard.circuitbreaker.jersey.CircuitBreakerBundle;
import com.github.mtakaki.dropwizard.circuitbreaker.jersey.CircuitBreakerConfiguration;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.kainos.config.MIGConfiguration;
import com.kainos.job.CacheManager;
import com.kainos.resource.CountryResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class MIGApplication extends Application<MIGConfiguration> {

    private GuiceBundle<MIGConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new MIGApplication().run(args);
    }

    private final CircuitBreakerBundle<MIGConfiguration> circuitBreakerBundle = new CircuitBreakerBundle<MIGConfiguration>() {
        @Override
        protected CircuitBreakerConfiguration getConfiguration(
                final MIGConfiguration configuration) {
            return configuration.getCircuitBreaker();
        }
    };

    @Override
    public void initialize(Bootstrap<MIGConfiguration> bootstrap) {

        guiceBundle = GuiceBundle.<MIGConfiguration>newBuilder()
                .addModule(new MIGModule())
                .setConfigClass(MIGConfiguration.class)
                .build();

        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(this.circuitBreakerBundle);
    }

    @Override
    public void run(MIGConfiguration configuration, Environment environment) {

        if (configuration.metricsEnabled()) {

            final JmxReporter reporter = JmxReporter.forRegistry(environment.metrics()).build();
            reporter.start();
        }

        environment.jersey().register(new CountryResource(environment.metrics()));
        environment.lifecycle().manage(guiceBundle.getInjector().getInstance(CacheManager.class));
    }
}
