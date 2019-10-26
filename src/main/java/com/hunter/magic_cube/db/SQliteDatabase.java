package com.hunter.magic_cube.db;

import java.sql.*;

/**
 *
 * @author Hunter
 */
public class SQliteDatabase {

    private static final String DB_FILE_NAME = "./cube.db";
    
    // sqlite.org/inmemorydb.html
    // https://stackoverflow.com/questions/51202525/how-can-i-change-in-memory-db-to-file-db-in-java
    // private static final String DB_FILE_NAME = ":memory:";

    private static Connection connection;

    private SQliteDatabase() {
    }

    /**
     * Connect to a sample database
     *
     * @param fileName the database file name
     */
    private static Connection connectOrCreateDatabase(String fileName) {

        String url = "jdbc:sqlite:" + fileName;

        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                
                return conn;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new RuntimeException("Cannot connect or create the database");
    }

    static public Connection getDbConnection() {
        if (connection == null) {
            connection = SQliteDatabase.connectOrCreateDatabase(DB_FILE_NAME);
        }
        return connection;
    }
    
    static public void closeDbConnection(){
        if (connection != null) {
            try{
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
