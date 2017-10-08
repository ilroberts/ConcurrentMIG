package com.kainos.service;

import org.javatuples.Pair;
import java.util.List;

public interface CountryService {

    List<Pair<String, String>> getCurrencies(List<String> countries);
}
