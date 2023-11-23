package practice2.main;

import java.util.ArrayList;

/**
 * packageName : practice2.main
 * fileName : Department
 * author : goott5
 * date : 2023-11-22
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-22          goott5             최초생성
 **/
public class Department {
    private String dept;
    private ArrayList<Employees> empList;

    Department(String dept){
        this.dept = dept;
        this.empList = new ArrayList<Employees>();

    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public ArrayList<Employees> getEmpList() {
        return empList;
    }

    public void setEmpList(Employees e) {
        this.empList.add(e);
    }

    public void outputEntireEmp() {
        for( Employees e : this.empList ){
            System.out.println(e.toString());
        }
    }


}
