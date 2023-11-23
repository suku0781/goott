package websky;

public class Permanent extends Employee{
 //추가 멤버 변수는 없음
	
	//생성자
	
	Permanent(String empNo, String empName, int deptNo, int salary){
		super(empNo, empName, deptNo, salary);
	}
	
	@Override
	public int paidSalary() {
		
		return this.getSalary();
	}
	
	
	@Override
	public String toString() {
		
		return "정규직 [" + super.toString() + "]";
	}

}
