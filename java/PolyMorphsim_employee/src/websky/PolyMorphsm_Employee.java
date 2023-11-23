package websky;

public class PolyMorphsm_Employee {

	public static void main(String[]args) {
		// TODO Auto-generated method stub
		// 1) Agreegation : 부서가 사라져도 사원은 남는다. (빈 마름모)
		Employee 채치수 = new Parttime("23001","채치수",10,10,10000);
		
		Department 총무부 = new Department(10, "총무부");
		총무부.addEmployee(채치수);
//		총무부 = null;
//		총무부.outputEntireEmp();
		System.out.println(채치수.toString());

		Employee 서태웅 = new Permanent("23002","서태웅", 10, 300000);

		// 2) Composition : 부서가 사라지면 사원도 사라진다. (속이 찬 마름모)
		Department it = new Department(10, "IT");
		it.addEmployee(new Parttime("23003", "강백호", 10,8, 11000));
		it.outputEntireEmp();
		it = null;
		// 1)과 2) 방식의 차이는 무엇일까요?
	}
}
