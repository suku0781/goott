package ex5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * packageName : ex5
 * fileName : DeleteTest
 * author : goott5
 * date : 2023-12-07
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-07          goott5             최초생성
 **/
public class DeleteTest {
    public static void main(String[] args) {
        // 오라클 DB접속하기 위해 필요 정보 (아이디, 패스워드, DB서버 주소)
        String id = "hr";
        String pwd = "1234";
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

        Connection con = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, id, pwd);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("드라이버를 찾을 수 없습니다.");
//               e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
//               e.printStackTrace();
            System.out.println("DB에 연결하지 못했습니다.");
        }

        if(con != null){
            int deptNo = 282;
            String query = "delete from departments where department_id = " + deptNo;

            Statement stmt = null;

            try {
                stmt = con.createStatement();
                int result = stmt.executeUpdate(query);
                if(result == 1) {
                    System.out.println("삭제 완료!");
                    System.out.println("auto-commmit : " + con.getAutoCommit());
                }

                 stmt.close();
                 con.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
