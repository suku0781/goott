package websky;

public abstract class Employee {
		private String empNo;
		private String empName;
		private int deptNo;
		protected int salary;
		
		Employee(String empNo, String empName, int deptNo, int salary){
			this.empNo = empNo;
			this.empName = empName;
			this.deptNo = deptNo;
			this.salary = salary;
			
		}


		public String getEmpName() {
			return empName;
		}


		public void setEmpName(String empName) {
			this.empName = empName; 
		}


		public int getDeptNo() {
			return deptNo;
		}


		public void setDeptNo(int deptNo) {
			this.deptNo = deptNo;
		}


		public int getSalary() {
			return salary;
		}


		public void setSalary(int salary) {
			this.salary = salary;
		}


		public String getEmpNo() {
			return empNo;
		}
		
		// 정규직과 알바직 사원의 급여지급 방법이 다르므로 abstract로 한다.
		public abstract int paidSalary();


		@Override
		public String toString() {
			return "Employee [empNo=" + empNo + ", empName=" + empName + ", deptNo=" + deptNo + ", salary=" + salary
					+ "]";
		}
		
		
		
		
}
