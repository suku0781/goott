
public class DataTypeCasting {

	public static void main(String[] args) {
		byte b = 125;
		System.out.println(b);
		
		// 묵시적 자동 형변환
		int i = b;
		System.out.println(i);
		
		float f = 1234; // float형이 int형보다 더 큰 범위를 담을 수 있다. float f = (float)1234; 가 생략되었다. 
		System.out.println(f);
		
//		int i1 = 3.14f; // float형이 int형 보다 더 큰범위이기 때문에 int형에 담을 수 없다. 
		int i1 = (int)3.14;  // 명시적 형변환
		System.out.println(i1);
		
		// int -> byte
		int i2 = 300; // 2진수로 100101100
		byte b2 = (byte)i2; // 1바이트는 8비트이므로 00101100까지만 출력된다. 
		System.out.println("i2 => b2"+b2); // 44가 출력된다.
		
		byte b3 = 100; // 100은 이진수로 바이트에 충분히 담기므로 오류가 발생하지 않음.
//		byte b4 = 400; // 400은 이진수로 바이트에 초과되므로 오류가 발생한다. 
		System.out.println(b3);
		
		int ii = 100;
		byte b5 = (byte)ii;
		
		short sMin = -32768;
		System.out.println( (short)(sMin - 1)); // 가장 작은 범위보다 초과할 경우 가장 큰 범위로 간다.
		
		short sMax = 32767;
		System.out.println( (short)(sMax + 1)); // 가장 큰 범위보다 초과할 경우 가장 작은 범위로 간다.
		
		
		
		// 명시적 형변환
		double pi = 3.141592;
		float f1 = (float)pi;
		System.out.println("f1 : " + f1);
		
		int i3 = 32767;
		short s = (short)i3;
		System.out.println("s : " + s);
		
		long l = -2147483648;
		int i4 = (int)l;
		System.out.println("l : " + l);
		
		int i5 = 32768;
		short s1 = (short)i5;
		System.out.println("i5 : " + s1);
		
		//
		char c = 'a';
		System.out.println("c : " + c);
		System.out.println("c : " + (int)c);
		System.out.println("c : " + (char)98);
		
		
		
	}

}