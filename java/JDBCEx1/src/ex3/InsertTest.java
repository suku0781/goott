package ex3;

import java.sql.*;

/**
 * packageName : ex3
 * fileName : InsertTest
 * author : goott5
 * date : 2023-12-06
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-06          goott5             최초생성
 **/
public class InsertTest {

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
            int deptNo = 283;
            String dName = "개발부";
            int mangerId = 105;
            int locationId = 1700;

            // (1) 실행할 쿼리문 준비
            String query = "insert into departments values(" + deptNo + ", '" + dName + "', " + mangerId + ", " + locationId + ")";

            // (2) Statement객체 : 쿼리문을 Connection 객체가 연결하고 있는 DB서버로 전송하고 실행하는 역할을 객체
            Statement stmt = null;

            // (3) ResultSet객체 : 쿼리문이 실행된 후에 결과 테이블을 담고 있는 객체
//            ResultSet rs = null;

            try {
                stmt = con.createStatement();
                int result = stmt.executeUpdate(query);

                if (result == 1) {
                    System.out.println("저장 완료!");
                }

                stmt.close();
                con.close();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}