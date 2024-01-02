package com.ajaxjsp.dao;

import com.ajaxjsp.vo.Dept;
import com.ajaxjsp.vo.Employee;
import com.ajaxjsp.vo.Job;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public abstract interface EmployeesDAO
{
  public abstract List<Employee> selectAllEmp()
    throws NamingException, SQLException;

  public abstract List<Employee> selectAllEmp(String paramString)
    throws NamingException, SQLException;

  public abstract List<Job> selectAllJob()
    throws NamingException, SQLException;

  public abstract int selectLastEmpId()
    throws NamingException, SQLException;

  public abstract List<Dept> selectAllDept()
    throws NamingException, SQLException;

  public abstract String insertEmp(Employee paramEmployee)
    throws NamingException, SQLException;

  public abstract List<Employee> searchEmp(String paramString)
    throws NamingException, SQLException;

  public abstract List<Employee> searchEmp(String paramString1, String paramString2)
    throws NamingException, SQLException;

  public abstract Employee selectEmployeeByEmpNo(int paramInt)
    throws NamingException, SQLException;

  public abstract int updateEmployee(Employee paramEmployee)
    throws NamingException, SQLException;

  public abstract int deleteEmp(int paramInt)
    throws SQLException, NamingException;
}