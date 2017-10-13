package com.kainos.cache;

import com.google.inject.Singleton;
import com.kainos.db.InitDb;

import java.util.Map;
import java.util.Optional;

@Singleton
public class CurrencyDescriptionCacheImpl implements CurrencyDescriptionCache {

    private Optional<Map<String, String>> cache;

    public CurrencyDescriptionCacheImpl() {
            cache = InitDb.getCodeDescriptions();
    }

    public Optional<String> get(String key) {

        if(!cache.isPresent()) {
            return Optional.empty();
        }
        Map<String, String> actualCache = cache.get();
        return Optional.ofNullable(actualCache.get(key));
    }
}
