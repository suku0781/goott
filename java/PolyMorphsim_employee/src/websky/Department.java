package websky;

import java.util.ArrayList;


public class Department {
	private int deptNo;
	private String deptName;
	private ArrayList<Employee>empList;
	
	Department(int deptNo, String deptName){
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.empList = new ArrayList<Employee>();
	}


	public String getDeptName() {
		return deptName;
	}

	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public int getDeptNo() {
		return deptNo;
	}

	public ArrayList<Employee> getEmpList() {
		return empList;
	}
	
	//
	public void addEmployee(Employee e) {
		this.empList.add(e);
		
	}
	public void outputEntireEmp() {
		for (Employee e : this.empList) {
			System.out.println(e.toString());
		}
	}
	
}
