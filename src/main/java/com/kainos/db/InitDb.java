package com.kainos.db;

import com.kainos.db.tables.daos.CurrencyCodeDao;
import com.kainos.db.tables.pojos.CurrencyCode;
import org.javatuples.Pair;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InitDb {

    public static Optional<Map<String,String>> getCodeDescriptions()  {

        String username = "postgres";
        String password = "";
        String url = "jdbc:postgresql://localhost:32768/currencydb";

        try (Connection c = DriverManager.getConnection(url, username, password)) {

            Configuration configuration = new DefaultConfiguration().set(c).set(SQLDialect.POSTGRES_9_5);
            CurrencyCodeDao currencyCodeDao = new CurrencyCodeDao(configuration);
            return Optional.of(currencyCodeDao.findAll().stream().collect(Collectors.toMap(CurrencyCode::getCode, CurrencyCode::getDescription)));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
