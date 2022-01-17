package ru.geekbrains.system_patterns.orm;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class SqliteConnector extends SqlConnector{
    private SQLiteDataSource dataSource;
    public SqliteConnector(String url, String login, String password) throws SQLException {
        super(url, login, password);
    }

    @Override
    public Connection getConnection() {
        try {
            if (dataSource != null){
                return this.dataSource.getConnection(super.login, super.password);
            }
            dataSource = new SQLiteDataSource();
            dataSource.setUrl(super.url);
            return this.dataSource.getConnection(super.login, super.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
