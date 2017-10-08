package com.kainos.resource;

import com.codahale.metrics.annotation.Timed;
import com.kainos.api.Countries;
import com.kainos.service.CountryService;
import org.javatuples.Pair;

import javax.inject.Inject;
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

    @Inject
    private CountryService countryService;

    @GET
    @Timed
    public Countries getCountries() {

        Countries countries = new Countries();
        List<String> countryList = Arrays.asList("United Kingdom", "Canada", "New Zealand", "Vietnam", "Sudan");
        countries.setCountries(countryList);
        return countries;
    }

    @POST
    @Timed
    @Path("/add")
    public void postCountries(Countries countries) {

        List<Pair<String, String>> result = countryService.getCurrencies(countries.getCountries());
        result.forEach(r -> System.out.println("country: " + r.getValue0() + " currency code: " + r.getValue1()));
    }


}
