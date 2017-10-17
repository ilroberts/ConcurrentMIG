package com.kainos.service;

import com.google.inject.Singleton;
import com.kainos.task.GetCurrencyTask;
import org.javatuples.Pair;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Singleton
public class CountryServiceImpl implements CountryService {

    private ExecutorService executor;

    public CountryServiceImpl() {
        executor = Executors.newFixedThreadPool(10);
    }

    public List<Pair<String, String>> getCurrencies(List<String> countries) {

        List<GetCurrencyTask> tasks = countries.stream().map(GetCurrencyTask::new).collect(Collectors.toList());
        List<CompletableFuture<Pair<String, String>>> futures = tasks.stream().map(t -> CompletableFuture.supplyAsync(t::execute, executor))
            .collect(Collectors.toList());

        return futures.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
