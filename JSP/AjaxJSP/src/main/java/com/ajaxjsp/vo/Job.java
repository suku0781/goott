package com.ajaxjsp.vo;

public class Job
{
  private String job_id;
  private String job_title;
  private int min_salary;
  private int max_salary;

  public Job(String job_id, String job_title, int min_salary, int max_salary)
  {
    this.job_id = job_id;
    this.job_title = job_title;
    this.min_salary = min_salary;
    this.max_salary = max_salary;
  }

  public String getJob_id() {
    return this.job_id;
  }
  public String getJob_title() {
    return this.job_title;
  }
  public int getMin_salary() {
    return this.min_salary;
  }
  public int getMax_salary() {
    return this.max_salary;
  }

  public String toString()
  {
    return "Job [job_id=" + this.job_id + ", job_title=" + this.job_title + ", min_salary=" + this.min_salary + ", max_salary=" + this.max_salary + "]";
  }
}