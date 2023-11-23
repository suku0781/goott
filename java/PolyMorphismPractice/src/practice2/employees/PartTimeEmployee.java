package practice2.employees;

import practice2.main.Employees;

/**
 * packageName : practice2.employees
 * fileName : PartTimeEmployee
 * author : goott5
 * date : 2023-11-22
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-22          goott5             최초생성
 **/
public class PartTimeEmployee extends Employees {
    String location;
    int workingHour = 0;
    int salary;

    public PartTimeEmployee(String name, String rank, String dept, String phone, int birth, String location, int workingHour) {
        super(name, rank, dept, phone, birth);
        this.location = location;
        this.workingHour = workingHour;
        this.salary = 9870 * workingHour;
    }

    public String getLocation() {
        return location;
    }

    public int getSalary () {
        return salary;
    }

    @Override
    public int paidSalary() {
        return super.salary = this.workingHour * 9870;
    }

    public void setLocation() {
        this.location = location;
    }

    public void setSalary(int salary) {
        this.salary = 9870 * workingHour;
    }

    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                "name = " + super.getName() +
                ", rank = " + super.getRank() +
                ", dept = " + super.getDept() +
                ", phone = " + super.getPhone() +
                ", birth = " + super.getBirth() +
                "location='" + location + '\'' +
                ", workingHour=" + workingHour +
                ", salary=" + salary +
                '}';
    }
}
