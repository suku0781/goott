
public class DataTypeAndVariable {

	public static void main(String[] args) {
		// boolean : true / false 를 저장하는 1byte
		boolean bool = true;
		System.out.println(bool);
		
		// char : 문자형 2byte : 한문자만 저장
		char character = '\u0041';
		System.out.println(character); // A
		char character2 = '가';
		System.out.println(character2); // 가
//		char character2 = 'AB'; // Invalid character constant
//		System.out.println(character2); // 가
		
		// 정수형에서 기본은 int형임.
		// byte : 정수형 1byte
		byte b = 127;
		System.out.println(b);
		System.out.println("byte타입의 최대값 : " + Byte.MAX_VALUE); // 127
		System.out.println("byte타입의 최소값 : " + Byte.MIN_VALUE); // -128

		// short : 정수형, 2byte
		short sSrt = 128;
		System.out.println(sSrt);
		System.out.println("byte타입의 최대값 : " + Short.MAX_VALUE); // 32767
		System.out.println("byte타입의 최대값 : " + Short.MIN_VALUE); // -32768
		
		// int : 정수형, 4byte
		int sInt = 35;
		System.out.println(sInt);
		System.out.println("int타입의 최대값 : " + Integer.MAX_VALUE); // 2147483647
		System.out.println("int타입의 최대값 : " + Integer.MIN_VALUE); // -2147483648
		
		// long : 정수형, 8byte
		long sLong = 35;
		System.out.println(sLong);
		System.out.println("long타입의 최대값 : " + Long.MAX_VALUE); // 9223372036854775807
		System.out.println("long타입의 최대값 : " + Long.MIN_VALUE); // -9223372036854775808
		
		// 실수형에서 기본은 double형임. 
		// float : 실수형, 4byte
		float f = 3.14f; // 기본은 double형이지만 끝에 f를 붙여주면(접미사) float로 인식힌다. 
		System.out.println(f);
		System.out.println("float타입의 최대값 : " + Float.MAX_VALUE);
		System.out.println("float타입의 최소값 : " + Float.MIN_VALUE);
		
		double d = 3.14;
		System.out.println(d);
		System.out.println("double타입의 최대값 : " + Double.MAX_VALUE);
		System.out.println("double타입의 최소값 : " + Double.MIN_VALUE);
		
		// 문자열 : 기본데이터 타입이 아닌 참조 타입 : String
		// 두 문자 이상의 문자열은 큼따옴표로 감싸야한다. 
		String str = "대한민국";
		System.out.println(str);
		System.out.println("str의 길이 : "+str.length());
		System.out.println(str.getClass().getName());
		
		String str1 = "";
//		Char ch = ''; // char타입은 빈 문자열로 초기화 할 수 없다. 
		char ch = ' '; // 공백으로 초기화
		
		// 
		String name = "Ja" + "va";
		String concatName = "ja".concat("va");
		String name2 = name + 8.0;
		System.out.println("name2" + name2);
		System.out.println(name2+" "+" !");
		System.out.println(8 + 8 + "");
		System.out.println("" + 8 + 8);
		
		
		
		
	}

}