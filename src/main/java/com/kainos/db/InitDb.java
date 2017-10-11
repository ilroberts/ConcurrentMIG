package com.kainos.db;

import org.javatuples.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InitDb {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:./currencydb;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void initialize() throws SQLException {
    }

    public static Optional<List<Pair<String, String>>> getCodeDescriptions() throws SQLException {

        Connection connection = getDBConnection();
        Statement stmt;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();

            List<Pair<String, String>> results = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("select * from CURRENCY_CODE");
            while (rs.next()) {
                results.add(Pair.with(rs.getString("code"), rs.getString("description")));
            }
            stmt.close();
            connection.commit();
            return Optional.of(results);
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return Optional.empty();
    }

    public static void teardown() throws SQLException {

    }

    private static Connection getDBConnection() {

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
