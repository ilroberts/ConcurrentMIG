package com.kainos.service;

import com.kainos.task.GetCurrencyTask;
import org.javatuples.Pair;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CountryServiceImpl implements CountryService {

    public List<Pair<String, String>> getCurrencies(List<String> countries) {

        ExecutorService executor = Executors.newFixedThreadPool(countries.size());

        List<GetCurrencyTask> tasks = countries.stream().map(GetCurrencyTask::new).collect(Collectors.toList());
        List<CompletableFuture<Pair<String, String>>> futures = tasks.stream().map(t -> CompletableFuture.supplyAsync(t::execute, executor))
            .collect(Collectors.toList());

        List<Pair<String, String>> result = futures.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
