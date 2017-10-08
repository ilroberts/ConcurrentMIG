package com.kainos.resource;

import com.codahale.metrics.annotation.Timed;
import com.kainos.api.Countries;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/countries")
@Produces(MediaType.APPLICATION_JSON)
public class CountryResource {

    @GET
    @Timed
    public Countries getCountries() {

        Countries countries = new Countries();
        List<String> countryList = Arrays.asList("United Kingdom", "Sudan");
        countries.setCountries(countryList);

        return countries;
    }

    @POST
    @Timed
    @Path("/add")
    public Countries postCountries(Countries countries) {


        return new Countries();
    }


}
