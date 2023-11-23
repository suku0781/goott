package websky;

public class Parttime extends Employee{
	private int workTime; 
	private int payPerHour;	
	
	//생성자
	
	Parttime(String empNo, String empName, int deptNo, int workTime, int payPerHour){
//		super(empNo, empName, deptNo, 0);
		super(empNo, empName, deptNo, workTime * payPerHour);
		this.workTime = workTime;
		this.payPerHour = payPerHour;
//		this.paidSalary();
	}


	public int getWorkTime() {
		return workTime;
	}
	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}


	public int getPayPerHour() {
		return payPerHour;
	}

	public void setPayPerHour(int payPerHour) {
		this.payPerHour = payPerHour;
	}
	
	@Override
	public int paidSalary() {
		super.salary = this.workTime * this.payPerHour;
		return super.salary;
		
	}


	@Override
	public String toString() {
		return "Parttime [workTime=" + workTime + ", payPerHour=" + payPerHour + ", toString()=" + super.toString()
				+ "]";
	}

//
//	@Override
//	public String toString() {
//		return "알바직 "+super.toString()+"";
//	}
//	
	
	
}
