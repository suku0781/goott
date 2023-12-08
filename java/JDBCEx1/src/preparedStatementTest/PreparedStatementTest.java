package preparedStatementTest;

import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;

/**
 * packageName : preparedStatementTest
 * fileName : PreparedStatementTest
 * author : goott5
 * date : 2023-12-07
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-07          goott5             최초생성
 **/
public class PreparedStatementTest {
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
            System.out.println("드라이버를 찾을 수 없습니다.");
        } catch (SQLException e) {
            System.out.println("DB에 연결하지 못했습니다.");
        }

        if(con != null){
            String firstName = "David";
            String query = "select * from employees where first_name like '%' || ? || '%'";
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                pstmt = con.prepareStatement(query); //parameterIndex – the first parameter is 1, the second is 2, ...(0번이 아님.)
//                pstmt.setString(1, firstName); // ? 매개변수에 할당(1부터 시작)
                pstmt.setString(1, "%" + firstName + "%"); // ? 매개변수에 할당(1부터 시작)
                rs = pstmt.executeQuery();

                while(rs.next()){
                    System.out.println( rs.getInt("EMPLOYEE_ID") + " "
                            + rs.getString("FIRST_NAME") + " "
                            + rs.getString("LAST_NAME") + " "
                            + rs.getString("EMAIL") + " "
                            + rs.getString("PHONE_NUMBER") + " "
                            + rs.getDate("HIRE_DATE") + " "
                            + rs.getString("JOB_ID") + " "
                            + rs.getFloat("SALARY") + " "
                            + rs.getFloat("COMMISSION_PCT") + " "
                            + rs.getInt("MANAGER_ID") + " "
                            + rs.getInt("DEPARTMENT_ID") + " ");
                }

                doInsert(con);

                rs.close();
                pstmt.close();
                con.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            System.out.println("1 : "+con);


        }

    }

    private static void doInsert(Connection con) {
        PreparedStatement pstmt = null;
        String query = "insert into departments values(?,?,?,?)";
        int department_id = 285;
        String department_name = "디지털사업부";
        int manager_id = 110;
        int location_id = 1000;

        try {
            System.out.println("2 : "+con);
            pstmt = con.prepareStatement(query); //parameterIndex – the first parameter is 1, the second is 2, ...(0번이 아님.)
            pstmt.setInt(1, department_id);
            pstmt.setString(2, department_name);
            pstmt.setInt(3, manager_id);
            pstmt.setInt(4, location_id);

            if(pstmt.executeUpdate() == 1) System.out.println("Insert Complete!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
