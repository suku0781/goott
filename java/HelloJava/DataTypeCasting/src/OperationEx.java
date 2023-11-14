
public class OperationEx {

	public static void main(String[] args) {
		byte result1 = 10 + 20;
		System.out.println(result1); // 리터럴 함수로 쓰니까 컴파일러가 자동으로 처리해줌.
		
		byte b1 = 10;
		byte b2 = 20;
//		byte result2 = b1 + b2; 
		int result2 = b1 + b2; // int타입으로 변환 후 연산
		System.out.println(result2);
		
		byte b3 = 10;
		int b4 = 100;
		long b5 = 1000;
		int result3 = b3 + b4;
//		byte result4 = b3 + b4; // Type mismatch
//		int result5 = b3 + b4 + b5; // Type mismatch long타입으로 변환 후 연산
		long result5 = b3 + b4 + b5;
		System.out.println(result5);
		
		int i1 = 10;
		int result6 = i1 / 4;
		System.out.println(result6); // 소수점이 잘리고 정수만 출력
		int i2 = 10; 
//		double result7 = (float)i2 / 4; // 가능(실수연산)
		double result7 = i2 / 4F; // 가능(실수연산)
		System.out.println(result7);
		
		int i3 = 1;
		int i4 = 2;
		int result8 = i3 / i4; // 0
		double result9 = i3 / i4; // 0.0 int형에서 계산을 한 후 double로 들어간 것이기때문에 0.0으로 됨.
		System.out.println(result8);
		
		double result10 = (double)i3 / i4;
		System.out.println(result10);
	}

}
