package practice2.employees;

import practice2.main.Employees;

/**
 * packageName : practice2.employees
 * fileName : FullTime
 * author : goott5
 * date : 2023-11-22
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-22          goott5             최초생성
 **/
public class FullTimeEmployee extends Employees {
    String location;

    public FullTimeEmployee(String name, String rank, String dept, String phone, int birth, String location) {
        super(name, rank, dept, phone, birth);
        this.location = location;
    }

    @Override
    public int paidSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return "FullTimeEmployee{" +
                super.toString() +
                "location='" + location + '\'' +
                '}';
    }


}
