package com.kainos.db;


import com.kainos.db.tables.daos.CurrencyCodeDao;
import com.kainos.db.tables.pojos.CurrencyCode;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class CurrencyCodeTest {

    @Test
    @Ignore
    public void testCurrencyCode() throws SQLException {

        String username = "postgres";
        String password = "";
        String url = "jdbc:postgresql://localhost:32768/currencydb";

        try (Connection c = DriverManager.getConnection(url, username, password)) {

            Configuration configuration = new DefaultConfiguration().set(c).set(SQLDialect.POSTGRES_9_5);
            CurrencyCodeDao currencyCodeDao = new CurrencyCodeDao(configuration);

            List<CurrencyCode> codes = currencyCodeDao.findAll();
            codes.forEach(code -> System.out.println("code: " + code.getCode() + " description: " + code.getDescription()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
