package com.kainos.resource;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.annotation.Timed;
import com.github.mtakaki.dropwizard.circuitbreaker.jersey.CircuitBreaker;
import com.kainos.api.Countries;
import com.kainos.api.CountryCurrencyCode;
import com.kainos.cache.CurrencyDescriptionCache;
import com.kainos.job.CacheManagerImpl;
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
import java.util.stream.Collectors;

@Path("/countries")
@Produces(MediaType.APPLICATION_JSON)
public class CountryResource {

    @Inject
    private CountryService countryService;

    @Inject
    private CurrencyDescriptionCache codeDescriptionCache;

    private final Meter requests;

    public CountryResource(final MetricRegistry mr) {
        this.requests = mr.meter("requests");
    }

    @GET
    @Timed
    @CircuitBreaker(name = "countries-get")
    public Countries getCountries() throws Exception {

//        requests.mark();
//        Countries countries = new Countries();
//        List<String> countryList = Arrays.asList("United Kingdom", "Canada", "New Zealand", "Vietnam", "Sudan");
//        countries.setCountries(countryList);

        throw new Exception("broken!");
        // yup return countries;
    }

    @POST
    @Timed
    @Path("/add")
    @CircuitBreaker(name = "countries-post")
    public List<CountryCurrencyCode> postCountries(Countries countries) {

        requests.mark();
        List<Pair<String, String>> result = countryService.getCurrencies(countries.getCountries());
        result.forEach(r -> System.out.println("country: " + r.getValue0() + " currency code: " + r.getValue1()));

        return result.stream()
                .map(r -> new CountryCurrencyCode(r.getValue0(), r.getValue1(), codeDescriptionCache.get(r.getValue1()).orElse("no description")))
                .collect(Collectors.toList());
    }
}
