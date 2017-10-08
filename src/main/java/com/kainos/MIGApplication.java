package com.kainos;

import com.kainos.config.MIGConfiguration;
import com.kainos.resource.CountryResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class MIGApplication extends Application<MIGConfiguration> {

    public static void main(String[] args) throws Exception {
        new MIGApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<MIGConfiguration> bootstrap) {

    }

    @Override
    public void run(MIGConfiguration configuration, Environment environment) {

        environment.jersey().register(new CountryResource());
    }
}
