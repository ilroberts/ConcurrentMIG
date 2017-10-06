package com.kainos.service;

import org.javatuples.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CountryService {

    public static void main(String... args) {

        List<String> countries = Arrays.asList("United Kingdom", "Canada", "New Zealand", "Vietnam", "Sudan");
        ExecutorService executor = Executors.newFixedThreadPool(countries.size());

        List<GetCurrencyTask> tasks = countries.stream().map(GetCurrencyTask::new).collect(Collectors.toList());
        List<CompletableFuture<Pair<String, String>>> futures = tasks.stream().map(t -> CompletableFuture.supplyAsync(t::getCurrency, executor))
            .collect(Collectors.toList());

        List<Pair<String, String>> result = futures.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());

        result.forEach(r -> System.out.println("country: " + r.getValue0() + " currency code: " + r.getValue1()));
        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
