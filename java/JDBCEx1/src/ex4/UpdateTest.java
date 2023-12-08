package ex4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * packageName : ex4
 * fileName : UpdateTest
 * author : goott5
 * date : 2023-12-06
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-06          goott5             최초생성
 **/
public class UpdateTest {
    public static void main(String[] args) {
        // 오라클 DB접속하기 위해 필요 정보 (아이디, 패스워드, DB서버 주소)
        String id = "hr";
        String pwd = "1234";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";

        Connection con = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, id, pwd);

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버를 찾을 수 없습니다.");
        } catch (SQLException e) {
            System.out.println("DB에 연결하지 못했습니다.");
        }

        if(con != null){
            int deptNo = 281;
            String dName = "영업부";
            int mgrId = 105;
            int locId = 1700;

            // 쿼리문 준비
            String query = "update departments set department_name = '"+dName+"' where department_id = "+deptNo;

            // Statement 객체 준비
            try {

                Statement stmt = con.createStatement();

                // 실행
                int result = stmt.executeUpdate(query);
                if(result == 1){
                    System.out.println("업데이트됨.");
                }

                // 닫기 (가장가까운것부터)
                stmt.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }

    }
}
