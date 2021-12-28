package ru.geekbrains.system_patterns.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    private final Connection conn;

    private final List<User> newUsers = new ArrayList<>();
    private final List<User> updateUsers = new ArrayList<>();
    private final List<User> deleteUsers = new ArrayList<>();

    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;

    public UnitOfWork(Connection conn) {
        this.conn = conn;
        initStatements();
    }

    private void initStatements() {
        try {
            this.update = this.conn.prepareStatement("UPDATE users SET username = ?, password = ? WHERE id = ?");
            this.insert = this.conn.prepareStatement("INSERT INTO users (id, username, password) VALUES(?, ?, ?)");
            this.delete = this.conn.prepareStatement("DELETE FROM users WHERE id = ?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void registerNew(User user) {
        this.newUsers.add(user);
    }

    public void registerUpdate(User user) {
        this.updateUsers.add(user);
    }

    public void registerDelete(User user) {
        this.deleteUsers.add(user);
    }

    public void commit() {
        try {
            conn.setAutoCommit(false);
            insert();
            update();
            delete();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void update() throws SQLException {
        this.updateUsers.forEach(user -> {
            try {
                this.update.setString(1, user.getLogin());
                this.update.setString(2, user.getPassword());
                this.update.setInt(3, user.getId());
                this.update.addBatch();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        this.update.executeBatch();
    }

    private void insert() throws SQLException {
        this.newUsers.forEach(user -> {
            try {
                this.insert.setInt(1, user.getId());
                this.insert.setString(2, user.getLogin());
                this.insert.setString(3, user.getPassword());
                this.insert.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        this.insert.executeBatch();
    }

    private void delete() throws SQLException {
        this.deleteUsers.forEach(user -> {
            try {
                this.delete.setInt(1, user.getId());
                this.delete.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        this.delete.executeBatch();
    }

}
