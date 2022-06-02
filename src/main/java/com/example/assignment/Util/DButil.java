package com.example.assignment.Util;

import com.mysql.cj.jdbc.result.CachedResultSetMetaDataImpl;

import java.sql.*;
import com.sun.rowset.CachedRowSetImpl;


public class DButil {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection = null;
    private static String databaseName = "";
    private static String url = "jdbc:mysql://localhost:3307/" + databaseName;
    private static String username = "root";
    private static String password = "Sohaib@1716";

    public static void dbConnect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(url, username, password);
    }

    public static void dbDissconnect() throws SQLException{
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }catch (Exception e){
            throw e;
        }
    }

    public static void dbExecuteQuery(String sql) throws SQLException, ClassNotFoundException  {
        Statement statement = null;
        try{
            dbConnect();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Data uploaded successfully");

        }catch (SQLException | InstantiationException | IllegalAccessException e) {
            System.out.println("Problem occured while executing the query");
            throw new RuntimeException(e);
        }
        finally {
            if(statement!=null){
                statement.close();
            }
            dbDissconnect();
        }
    }

    public static ResultSet RetriveData(String sql) throws ClassNotFoundException, SQLException{
        Statement statement = null;
        ResultSet rs = null;

        try{
            dbConnect();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

        }catch (SQLException | InstantiationException | IllegalAccessException e) {
            System.out.println("Problem occured while executing the query");
            throw new RuntimeException(e);
        }
//        finally {
//            if(rs!=null){
//                rs.close();
//            }
//            if(statement!=null){
//                statement.close();
//            }
//            dbDissconnect();
//        }
        return rs;
    }
}
