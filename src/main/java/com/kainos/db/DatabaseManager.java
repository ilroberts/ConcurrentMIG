package com.kainos.db;

import java.util.Map;
import java.util.Optional;

public interface DatabaseManager {

    Optional<Map<String,String>> getCodeDescriptions();

}
