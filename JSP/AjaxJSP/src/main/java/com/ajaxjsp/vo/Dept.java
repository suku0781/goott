package com.ajaxjsp.vo;

public class Dept
{
  private int department_id;
  private String department_name;
  private int manager_id;
  private int location_id;

  public Dept(int department_id, String department_name, int manager_id, int location_id)
  {
    this.department_id = department_id;
    this.department_name = department_name;
    this.manager_id = manager_id;
    this.location_id = location_id;
  }

  public int getDepartment_id() {
    return this.department_id;
  }

  public void setDepartment_id(int department_id) {
    this.department_id = department_id;
  }

  public String getDepartment_name() {
    return this.department_name;
  }

  public void setDepartment_name(String department_name) {
    this.department_name = department_name;
  }

  public int getManager_id() {
    return this.manager_id;
  }

  public void setManager_id(int manager_id) {
    this.manager_id = manager_id;
  }

  public int getLocation_id() {
    return this.location_id;
  }

  public void setLocation_id(int location_id) {
    this.location_id = location_id;
  }

  public String toString()
  {
    return "Dept [department_id=" + this.department_id + ", department_name=" + this.department_name + ", manager_id=" + this.manager_id + ", location_id=" + this.location_id + "]";
  }
}