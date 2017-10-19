package com.kainos.cache;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kainos.db.DatabaseManager;

import java.util.Map;
import java.util.Optional;

@Singleton
public class CurrencyDescriptionCacheImpl implements CurrencyDescriptionCache {

    private Optional<Map<String, String>> cache;
    DatabaseManager databaseManager;

    @Inject
    public CurrencyDescriptionCacheImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public Optional<String> get(String key) {
        return cache.map(c -> c.get(key));
    }

    public void rebuild() {
        cache = databaseManager.getCodeDescriptions();
    }
}
