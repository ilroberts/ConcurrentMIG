package com.kainos.db;

import com.kainos.db.tables.daos.CurrencyCodeDao;
import com.kainos.db.tables.pojos.CurrencyCode;
import org.javatuples.Pair;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class DatabaseManagerImpl implements DatabaseManager {

    Connection connection;

    private String username = "postgres";
    private String password = "";
    private String url = "jdbc:postgresql://localhost:32768/currencydb";

    public DatabaseManagerImpl() {

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  Optional<Map<String,String>> getCodeDescriptions()  {

        Configuration configuration = new DefaultConfiguration().set(connection).set(SQLDialect.POSTGRES_9_5);
        CurrencyCodeDao currencyCodeDao = new CurrencyCodeDao(configuration);
        System.out.println("descriptions refreshed!");

        Map<String, String> result =
                Collections.unmodifiableMap(new LinkedHashMap<>(
                        currencyCodeDao.findAll().stream().collect(Collectors.toMap(CurrencyCode::getCode, CurrencyCode::getDescription)
                )));


        return Optional.of(result);
    }
}
