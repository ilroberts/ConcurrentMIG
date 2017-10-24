package com.kainos.misc;

public class Person {

    private final String id;
    private final String forename;
    private final String surname;

    public Person(String id, String forename, String surname) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }
}
