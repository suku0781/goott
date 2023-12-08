package com.shk.dao;

import java.sql.*;

import static java.lang.Class.forName;

/**
 * packageName : com.shk.dao
 * fileName : DBConnection
 * author : goott5
 * date : 2023-12-07
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-07          goott5             최초생성
 **/
public class DBConnection {
    private static String id ="hr";
    private static String pwd ="1234";
    private static String url ="jdbc:oracle:thin:@127.0.0.1:1521:xe";

    static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(url, id, pwd);

        return con;
    }

    static void close(ResultSet rs, Statement stmt, Connection con) throws SQLException {
        rs.close();
        stmt.close();
        con.close();
    }

    static void close(Statement stmt, Connection con) throws SQLException {
        stmt.close();
        con.close();
    }
}
