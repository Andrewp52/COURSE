package ru.geekbrains.system_patterns.orm;

import java.sql.Connection;

public abstract class SqlConnector {
    protected final String url;
    protected final String login;
    protected final String password;

    public SqlConnector(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public abstract Connection getConnection();

}
