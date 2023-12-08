package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-12-06
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-06          goott5             최초생성
 **/
public class DBConnectionTest {
    public static void main(String[] args) {
        String id = "hr";
        String pwd = "1234";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";

        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, id, pwd);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(conn != null){
            System.out.println("DB 연결 성공. " + conn.toString());

            try {
                conn.close();
                System.out.println("DB 연결 해제 성공. ");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("DB 연결 실패. ");
        }
    }
}