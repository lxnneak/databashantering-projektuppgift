package se.linnea.projektuppgift;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    static Properties properties = new Properties();

    static {
        try (InputStream input = JDBCUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Could not find application.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load database properties");
        }
    }

    public static void main(String[] args) {
        String metadata = getDatabaseProductName();
        System.out.println(metadata);

    }

    public static Connection getConnection() {
        try {
            Driver hsqlDriver = new org.hsqldb.jdbcDriver();
            DriverManager.registerDriver(hsqlDriver);

            String dbURL = properties.getProperty("db.url");
            //System.out.println("Database URL: " + dbURL);
            String userID = properties.getProperty("db.user");
            //System.out.println("User ID: " + userID);
            String password = properties.getProperty("db.password");
            //System.out.println("Password: " + password);

            Connection conn = DriverManager.getConnection(dbURL,userID,password);

            conn.setAutoCommit(false);

            return conn;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection conn) {
        try {
            if (conn != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getDatabaseProductName() {
        try {
            Connection conn = getConnection();
            DatabaseMetaData metadata = conn.getMetaData();
            return metadata.getDatabaseProductName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}