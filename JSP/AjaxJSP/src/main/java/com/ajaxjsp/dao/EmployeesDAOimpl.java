package com.ajaxjsp.dao;

import com.ajaxjsp.vo.Dept;
import com.ajaxjsp.vo.Employee;
import com.ajaxjsp.vo.Job;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class EmployeesDAOimpl
  implements EmployeesDAO
{
  private static EmployeesDAOimpl instance;

  public static EmployeesDAOimpl getInstance()
  {
    if (instance == null) instance = new EmployeesDAOimpl();
    return instance;
  }

  public List<Employee> selectAllEmp() throws NamingException, SQLException
  {
    System.out.println("DAO단으로 호출됨");

    List lst = new ArrayList();
    Connection con = DBConnection.dbConnect();

    if (con != null) {
      String query = "select e.*, d.department_name from Employees e inner join departments d on e.department_id = d.department_id order by e.employee_id";

      PreparedStatement pstmt = null;
      pstmt = con.prepareStatement(query);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        lst.add(new Employee(rs.getInt("EMPLOYEE_ID"), 
          rs.getString("FIRST_NAME"), 
          rs.getString("LAST_NAME"), 
          rs.getString("EMAIL"), 
          rs.getString("PHONE_NUMBER"), 
          rs.getDate("HIRE_DATE"), 
          rs.getString("JOB_ID"), 
          rs.getFloat("SALARY"), 
          rs.getFloat("COMMISSION_PCT"), 
          rs.getInt("MANAGER_ID"), 
          rs.getInt("DEPARTMENT_ID"), 
          rs.getString("DEPARTMENT_NAME")));
      }

      DBConnection.dbClose(rs, pstmt, con);
    }

    return lst;
  }

  public List<Employee> selectAllEmp(String type) throws NamingException, SQLException
  {
    System.out.println("DAO단으로 호출됨 SORTING");

    List lst = new ArrayList();
    Connection con = DBConnection.dbConnect();

    if (con != null) {
      String query = "select e.*, d.department_name from employees e inner join departments d on e.department_id = d.department_id order by ";

      if (type.equals("sal"))
        query = query + "e.salary";
      else if (type.equals("hireDate"))
        query = query + "e.hire_date";
      else {
        query = query + "e.employee_id";
      }

      PreparedStatement pstmt = con.prepareStatement(query);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        lst.add(new Employee(rs.getInt("EMPLOYEE_ID"), 
          rs.getString("FIRST_NAME"), 
          rs.getString("LAST_NAME"), 
          rs.getString("EMAIL"), 
          rs.getString("PHONE_NUMBER"), 
          rs.getDate("HIRE_DATE"), 
          rs.getString("JOB_ID"), 
          rs.getFloat("SALARY"), 
          rs.getFloat("COMMISSION_PCT"), 
          rs.getInt("MANAGER_ID"), 
          rs.getInt("DEPARTMENT_ID"), 
          rs.getString("DEPARTMENT_NAME")));
      }

      System.out.println("breakPoint");

      DBConnection.dbClose(rs, pstmt, con);
    }

    return lst;
  }

  public List<Job> selectAllJob() throws NamingException, SQLException
  {
    System.out.println("DAO단으로 호출됨");

    List lst = new ArrayList();
    Connection con = DBConnection.dbConnect();

    if (con != null) {
      String query = "SELECT * FROM jobs";
      PreparedStatement pstmt = null;
      pstmt = con.prepareStatement(query);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next())
      {
        lst.add(new Job(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
      }

      DBConnection.dbClose(rs, pstmt, con);
    }

    return lst;
  }

  public int selectLastEmpId() throws NamingException, SQLException
  {
    Connection con = DBConnection.dbConnect();
    int result = -1;

    if (con != null) {
      String query = "select max(employee_id) from employees";
      PreparedStatement pstmt = con.prepareStatement(query);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        result = rs.getInt(1);
      }
      DBConnection.dbClose(rs, pstmt, con);
    }

    return result;
  }

  public List<Dept> selectAllDept() throws NamingException, SQLException
  {
    Connection con = DBConnection.dbConnect();
    List lst = new ArrayList();

    if (con != null) {
      String query = "select * from departments order by department_id";
      PreparedStatement pstmt = con.prepareStatement(query);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        lst.add(new Dept(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
      }

      DBConnection.dbClose(rs, pstmt, con);
    }

    return lst;
  }

  public String insertEmp(Employee emp) throws NamingException, SQLException
  {
    System.out.println("사원 저장을 위한 daoIMPL 호출");
    Connection con = DBConnection.dbConnect();
    String result = null;
    if (con != null)
    {
      String query = "{call PROC_SAVEEMP(?,?,?,?,?,?,?,?,?,?,?)}";
      CallableStatement cstmt = con.prepareCall(query);
      cstmt.setString(1, emp.getFirst_name());
      cstmt.setString(2, emp.getLast_name());
      cstmt.setString(3, emp.getEmail());
      cstmt.setString(4, emp.getPhone_number());
      cstmt.setDate(5, emp.getHire_date());
      cstmt.setString(6, emp.getJob_id());
      cstmt.setFloat(7, emp.getSalary());
      cstmt.setFloat(8, emp.getCommision_pct());
      cstmt.setInt(9, emp.getManager_id());
      cstmt.setInt(10, emp.getDepartment_id());
      cstmt.registerOutParameter(11, 12);

      cstmt.executeUpdate();

      result = cstmt.getString(11);
      System.out.println("result : " + result);

      DBConnection.dbClose(cstmt, con);
    }
    return result;
  }

  public List<Employee> searchEmp(String srchVal)
    throws NamingException, SQLException
  {
    System.out.println("사원 검색을 위한 daoIMPL 호출");

    Connection con = DBConnection.dbConnect();
    List lst = new ArrayList();
    if (con != null) {
      String query = "select e.*, d.department_name from employees e inner join departments d on e.department_id = d.department_id where LOWER(first_name) like ? or LOWER(last_name) like ?";
      PreparedStatement pstmt = con.prepareStatement(query);
      pstmt.setString(1, "%" + srchVal + "%");
      pstmt.setString(2, "%" + srchVal + "%");
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        lst.add(new Employee(rs.getInt(1), 
          rs.getString(2), 
          rs.getString(3), 
          rs.getString(4), 
          rs.getString(5), 
          rs.getDate(6), 
          rs.getString(7), 
          rs.getFloat(8), 
          rs.getFloat(9), 
          rs.getInt(10), 
          rs.getInt(11), 
          rs.getString(12)));
      }
      DBConnection.dbClose(rs, pstmt, con);
    }

    return lst;
  }

  public List<Employee> searchEmp(String srchVal, String type) throws NamingException, SQLException
  {
    System.out.println("사원 검색과 소팅을 위한 daoIMPL 호출");

    Connection con = DBConnection.dbConnect();
    List lst = new ArrayList();
    if (con != null) {
      String query = "select e.*, d.department_name from employees e inner join departments d on e.department_id = d.department_id where LOWER(first_name) like ? or LOWER(last_name) like ? order by ";

      if (type == null) {
        query = query + "e.employee_id";
      }
      else if (type.equals("sal"))
        query = query + "e.salary";
      else if (type.equals("hireDate"))
        query = query + "e.hire_date";
      else {
        query = query + "e.employee_id";
      }

      PreparedStatement pstmt = con.prepareStatement(query);
      pstmt.setString(1, "%" + srchVal + "%");
      pstmt.setString(2, "%" + srchVal + "%");

      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        lst.add(new Employee(rs.getInt(1), 
          rs.getString(2), 
          rs.getString(3), 
          rs.getString(4), 
          rs.getString(5), 
          rs.getDate(6), 
          rs.getString(7), 
          rs.getFloat(8), 
          rs.getFloat(9), 
          rs.getInt(10), 
          rs.getInt(11), 
          rs.getString(12)));
      }
      DBConnection.dbClose(rs, pstmt, con);
    }

    return lst;
  }

  public int updateEmployee(Employee emp) throws NamingException, SQLException
  {
    System.out.println("직원 수정을 위한 daoIMPL 호출");
    Connection con = DBConnection.dbConnect();
    int result = 0;
    if (con != null) {
      String query = "update employees set first_name = ?, last_name = ?, email = ?, phone_number = ?, hire_date = ?, job_id = ?, salary = ?, commission_pct = ?, manager_id = ?, department_id = ? where employee_id = ?";

      PreparedStatement pstmt = con.prepareStatement(query);
      pstmt.setString(1, emp.getFirst_name());
      pstmt.setString(2, emp.getLast_name());
      pstmt.setString(3, emp.getEmail());
      pstmt.setString(4, emp.getPhone_number());
      pstmt.setDate(5, emp.getHire_date());
      pstmt.setString(6, emp.getJob_id());
      pstmt.setFloat(7, emp.getSalary());
      pstmt.setFloat(8, emp.getCommision_pct());
      pstmt.setInt(9, emp.getManager_id());
      pstmt.setInt(10, emp.getDepartment_id());
      pstmt.setInt(11, emp.getEmployee_id());

      result = pstmt.executeUpdate();

      DBConnection.dbClose(pstmt, con);
    }
    return result;
  }

  public Employee selectEmployeeByEmpNo(int empNo) throws NamingException, SQLException
  {
    Employee emp = null;
    Connection con = DBConnection.dbConnect();
    String query = "select e.*, d.department_name from employees e inner join departments d on e.department_id = d.department_id where e.employee_id = ?";
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setInt(1, empNo);

    ResultSet rs = pstmt.executeQuery();
    while (rs.next()) {
      emp = new Employee(rs.getInt(1), 
        rs.getString(2), 
        rs.getString(3), 
        rs.getString(4), 
        rs.getString(5), 
        rs.getDate(6), 
        rs.getString(7), 
        rs.getFloat(8), 
        rs.getFloat(9), 
        rs.getInt(10), 
        rs.getInt(11), 
        rs.getString(12));
    }

    DBConnection.dbClose(rs, pstmt, con);
    return emp;
  }

  public int deleteEmp(int empno) throws SQLException, NamingException
  {
    System.out.println("삭제 임플단 empNo : " + empno);
    Connection con = DBConnection.dbConnect();
    PreparedStatement pstmt = null;
    int result = 0;
    if (con != null) {
      String query = "delete from employees where employee_id = ?";
      pstmt = con.prepareStatement(query);
      pstmt.setInt(1, empno);

      result = pstmt.executeUpdate();
      System.out.println("임플단 result : " + result);
    }

    DBConnection.dbClose(pstmt, con);
    return result;
  }
}