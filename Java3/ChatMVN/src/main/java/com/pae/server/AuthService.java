package com.pae.server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class AuthService {
    static Connection connection;
    static Statement statement;
    private static final Logger log = LogManager.getLogger(AuthService.class);
    public static void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:chat-db.db");
            statement = connection.createStatement();
            log.info("Auth service connected");
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Database connection error: ", e);
        }
    }

    public static String getNickByLoginPassword(String login, String pass){
        String query = String.format(
                "SELECT nick FROM users where login='%s' and password='%s'", login, pass.hashCode()
        );
        try {
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                return rs.getString("nick");
            }
        } catch (SQLException e) {
            log.error("Getting nick error: ", e);
        }
        return null;
    }

    public static List<String> getBlacklistByOwner(String nick){
        List<String> res = new LinkedList<>();
        String query = String.format("SELECT banned FROM v_blacklist where owner='%s'", nick);
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                res.add(rs.getString("banned"));
            }
        } catch (SQLException e) {
            log.error("Getting blacklist error: ", e);
        }
        return res;
    }

    public static List<String> getHistoryByOwner(String nick){
        List<String> res = new LinkedList<>();
        String query = String.format("SELECT message FROM v_history where owner='%s'", nick);
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                res.add(rs.getString("message"));
            }
        } catch (SQLException e) {
            log.error("Getting history error: ", e);
        }
        return res;
    }

    public static int storeUserHistory(String owner, String message){
        StringBuilder query = new StringBuilder();
        query.append("insert into history (user_id, message) values ")
                .append(String.format("((select id from users where nick='%s'), '%s')", owner, message));
        try {
            return statement.executeUpdate(query.toString());
        } catch (SQLException e) {
            log.error("History saving error: ", e);
        }
        return 0;
    }

    public static int dropHistoryByOwner(String owner){
        String query = String.format(
                "delete from history where user_id=(select id from users where nick = '%s')", owner
        );
        try {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            log.error("History cleaning error: ", e);
        }
        return 0;
    }

    public static int blockUser(String owner, String user){
        StringBuilder sb = new StringBuilder();
        sb.append("insert into blacklist (user_id, ban_id) values ")
                .append(String.format("((select id from users where nick = '%s'),", owner))
                .append(String.format("(select id from users where nick = '%s'))", user));
        try {
            return statement.executeUpdate(sb.toString());
        } catch (SQLException e) {
            log.error("Blocking user error: ", e);
        }
        return 0;
    }

    public static int unblockUser(String owner, String user){
        StringBuilder sb = new StringBuilder();
        sb.append("delete from blacklist where ")
                .append(String.format("user_id=(select id from users where nick = '%s')", owner))
                .append(" and ")
                .append(String.format("ban_id=(select id from users where nick = '%s')", user));
        try {
           return statement.executeUpdate(sb.toString());
        } catch (SQLException e) {
            log.error("Unblocking user error: ", e);
        }
        return 0;
    }

    public static int registerUser(String login, String pass, String nick){
        StringBuilder q = new StringBuilder();
        q.append("insert into users (login, password, nick) values ")
                .append(String.format("('%s', '%s', '%s')", login, pass.hashCode(), nick));
        try {
            return statement.executeUpdate(q.toString());
        } catch (SQLException e) {
            log.error("Registration error: ", e);
        }
        return 0;
    }

    public static void disconnect()  {
        try {
            connection.close();
            log.info("Auth service disconnected");
        } catch (SQLException e) {
            log.error("Disconnecting error: ", e);
        }
    }

}
