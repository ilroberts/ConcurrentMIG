package com.kainos.cache;

import java.util.Optional;

public interface CurrencyDescriptionCache {

    Optional<String> get(String key);
    void rebuild();
}
