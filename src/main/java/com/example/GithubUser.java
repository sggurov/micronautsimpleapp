package com.example;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class GithubUser {
    private final String login;
    private final String name;
    private final String email;

    public GithubUser(String login, String name, String email) {
        this.login = login;
        this.name = name;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
