package com.pashenko.DbUpdate;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private static MysqlDataSource ds = new MysqlDataSource();
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        init();
        updateScoresFromFile(new File("update.txt"));
        connection.close();
    }

    private static void updateScoresFromFile(File file) throws SQLException{
        try(
                BufferedReader br = new BufferedReader(new FileReader(file));
                PreparedStatement upd = connection.prepareStatement("update students set score = ? where id = ?");
                PreparedStatement sel = connection.prepareStatement("select id from students where id = ? and score != ?");
        ){
            connection.setAutoCommit(false);
            int id, score;
            String[] parts;
            String line = br.readLine();            // Skip 1-st row
            while ((line = br.readLine()) != null){
                parts = line.split(" ");
                id = Integer.parseInt(parts[0].trim());
                score = Integer.parseInt(parts[1].trim());
                sel.setInt(1, id);
                sel.setInt(2, score);
                if(sel.executeQuery().next()){
                    upd.setInt(1, score);
                    upd.setInt(2, id);
                    upd.addBatch();
                }
            }
            upd.executeBatch();
        } catch (IOException | NumberFormatException e){
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private static void init() throws SQLException {
        ds.setServerName("localhost");
        ds.setUser("test1");
        ds.setPassword("test1");
        ds.setDatabaseName("students");
        connection = ds.getConnection();
    }
}
