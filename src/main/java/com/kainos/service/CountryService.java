package com.kainos.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CountryService {

    public static void main(String... args) {

        List<String> countries = Arrays.asList("United Kingdom", "Canada", "New Zealand", "Vietnam", "Sudan");
        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<GetCurrencyTask> tasks = countries.stream().map(GetCurrencyTask::new).collect(Collectors.toList());
        List<CompletableFuture<String>> futures = tasks.stream().map(t -> CompletableFuture.supplyAsync(t::getCurrency, executor))
            .collect(Collectors.toList());

        List<String> result = futures.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }
}
