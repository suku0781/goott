package ex2;

import java.sql.*;
import java.util.Scanner;

/**
 * packageName : ex2
 * fileName : SelectTest
 * author : goott5
 * date : 2023-12-06
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-06          goott5             최초생성
 **/
public class SelectTest {

    public static void main(String[] args) {
        String id = "hr";
        String pwd = "1234";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";

        Connection conn = null;
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, id, pwd);
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버를 찾을 수 없음.");
        } catch (SQLException e) {
            System.out.println("DB에 연결하지 못함.");
        }

        // select
        if(conn != null){
            System.out.print("조회할 사원 번호 입력 >> ");
            int empNo = sc.nextInt();

            // 1. 실행할 쿼리문 준비
            String query = "select * from employees where employee_id = " + empNo; // 사번을 가진 직원 검색

            // 2. Statement객체 : 쿼리문을 Connection객체가 연결하고 있는 DB서버로 전송하고 실행하는 역할을 하는 객체
            Statement stmt = null;
            ResultSet rs = null;

            // 3. ResultSet객체 : 쿼리문이 실행된 후 결과테이블을 담고있는 객체
            try {
                stmt = conn.createStatement();

                // 4. 쿼리문 실행
                rs = stmt.executeQuery(query); // ResultSet object을 반환함.

                // 5. 결과테이블을 ResultSet rs가 가리키고 있음.
                if(rs.next()){
                    System.out.println( rs.getInt("EMPLOYEE_ID") + " "
                                        + rs.getString("FIRST_NAME") + " "
                                        + rs.getString("EMAIL") + " "
                                        + rs.getString("PHONE_NUMBER") + " "
                                        + rs.getDate("HIRE_DATE") + " "
                                        + rs.getString("JOB_ID") + " "
                                        + rs.getFloat("SALARY") + " "
                                        + rs.getFloat("COMMISSION_PCT") + " "
                                        + rs.getInt("MANAGER_ID") + " "
                                        + rs.getInt("DEPARTMENT_ID") + " ");
                } else {
                    System.out.println("사원이 존재하지 않음.");
                }

                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
//                throw new RuntimeException(e);
                if(e instanceof SQLSyntaxErrorException){
                    System.out.println("쿼리문을 잘짜지그래썽요....");
                }

            }







        }
    }
}
