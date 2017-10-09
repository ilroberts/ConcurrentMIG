package com.kainos.db;

import org.javatuples.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InitDb {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public static void initialize() throws SQLException {

        Connection connection = getDBConnection();
        Statement stmt;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE CURRENCY_CODE(code varchar(10) primary key, description varchar(255))");
            stmt.execute("INSERT INTO CURRENCY_CODE(code, description) VALUES('GBP', 'Pound Sterling')");
            stmt.execute("INSERT INTO CURRENCY_CODE(code, description) VALUES('CAD', 'Canadian Dollar')");
            stmt.execute("INSERT INTO CURRENCY_CODE(code, description) VALUES('NZD', 'New Zealand Dollar')");
            stmt.execute("INSERT INTO CURRENCY_CODE(code, description) VALUES('VND', 'Vietnamese Dong')");
            stmt.execute("INSERT INTO CURRENCY_CODE(code, description) VALUES('SDD', 'Sudanese Dollar')");

            ResultSet rs = stmt.executeQuery("select * from CURRENCY_CODE");
            while (rs.next()) {
                System.out.println("code: " + rs.getString("code") + " description: " + rs.getString("description"));
            }
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
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

        Connection connection = getDBConnection();
        Statement stmt;

        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("DROP TABLE CURRENCY_CODE");
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
        System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
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
