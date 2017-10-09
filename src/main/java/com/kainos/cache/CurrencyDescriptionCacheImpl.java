package com.kainos.cache;

import com.google.inject.Singleton;
import com.kainos.db.InitDb;
import org.javatuples.Pair;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Singleton
public class CurrencyDescriptionCacheImpl implements CurrencyDescriptionCache {

    private Optional<List<Pair<String, String>>> cache;

    public CurrencyDescriptionCacheImpl() {

        try {
            InitDb.initialize();
            cache = InitDb.getCodeDescriptions();
            InitDb.teardown();

        } catch (SQLException e) {
            cache = Optional.empty();
        }

    }

    public Optional<String> get(String key) {

        if(!cache.isPresent()) {
            return Optional.empty();
        }
        List<Pair<String, String>> actualCache = cache.get();

        Optional<Pair<String, String>> result = actualCache.stream().filter(p -> p.getValue0().equals(key)).findFirst();
        if (result.isPresent()) {
            return Optional.of(result.get().getValue1());
        } else {
            return Optional.empty();
        }
    }
}
