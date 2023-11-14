import java.util.ArrayList;
import java.util.Arrays;

public class ArrayEx1 {

	public static void main(String[] args) {
		char[] charArr = new char[3];
		
		System.out.println('1');
		int a; // 지역변수는 초기화 하지 않으면 사용할 수 없음.
//		System.out.println(a); // The local variable a may not have been initialized
		System.out.println('2');
		int[] scores = new int[3]; // 객체는 자동 초기화
		System.out.println(scores); // I@1db9742 타입@주소(16진수)
		
		System.out.println(Arrays.toString(scores)); // [0, 0, 0]
		
		String[] names = new String[5];
		System.out.println(names + " " + Arrays.toString(names)); 
		
		
		System.out.println('3');
		
		boolean isOk[] = new boolean[2];
		System.out.println(isOk);
		
		// scores 배열의 요소에 값 할당
		scores[0] = 45;
		scores[1] = 23;
		scores[2] = 99;
//		scores[3] = 89; // 에러 Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException
		// 배열의 범위에서 벗어남. - 컴파일 시 문제 없으나 실행시 에러 발생.(예외)
		// 자바에서 push 개념은 없음. 배열에서 추가하기 위해서는 다시만들어야함.
		// 배열의 길이를 변경하는 법.
		// 1. 더 큰 배열을 새로 생성한다. 
		// 2. 기존 배열의 내용을 새로운 배열에 복사한다. 
		
		scores= new int[4]; // 크기가 4인 배열을 생성. 
//		scores = new char[4]; // cannot convert from char[] to int[] 위에서 scores배열은 int배열로 사용하겠다고 선언. 같은 블럭 내에서 다른 타입으로 할당 불가.
		
		System.out.println(scores);		
		// 배열의 각 요소에 값을 할당할 때 아래와 같은 방법을 쓰면 편리하다. 
		int[] scores2 = {100, 20, 30, 55, 23}; // new 연산자가 없어도 {} 안에 있는 값들이 할당된
		System.out.println("scores2 "+scores2);
		System.out.println(scores[2]);
		
		int[] scores3 = new int[] {10, 20, 30, 40, 50}; // 배열에 값을 바로 할당할 경우 크기를 입력하지 않는다. 
		System.out.println(Arrays.toString(scores3));
		
		int[] scores4; // 선언
		scores4 = new int[] {100, 200, 300}; // 생성과 동시에 할당
//		scores4 = {100, 200, 300}; // new연산자를 쓰지않고 할당하게 되면 주소값이 설정되지않아서 에러가 발생한다. Array constants can only be used in initializers
		
		// 배열의 요소를 출력하는 방법.
		String[] heros = { "아이언맨", "스파이더맨", "헐크" };
		for(int i = 0 ; i < heros.length ; i++) {
			System.out.println(heros[i]);
		}
		for(String hero : heros) {
			System.out.println(hero);
		}
		
		
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("TEST1");
		arrayList.add("TEST2");
		arrayList.add("TEST3");
		
		System.out.println(arrayList);

	}

}
