import java.util.Scanner;

public class ForEx {

	public static void main(String[] args) {

		
		
		// 출력할 라인수를 입력하십시오. 
		System.out.println("출력할 라인수를 입력하십시오.");
		Scanner sc = new Scanner(System.in);
		
		String tmp = sc.nextLine();
		int num = 0;
		num = Integer.parseInt(tmp);
		
		System.out.println("출력할 라인 수 : "+num);
		for(int i = 0 ; i < num/2 ; i++) {
			
			for(int j = 0 ; j <= i ; j++) {
				System.out.print('*');	
				
			}
			System.out.println(' ');				

		}		
		for(int i = (num/2)-1 ; i > 0 ; i--) {
			
			for(int j = i ; j > 0 ; j--) {
				System.out.print('*');	
				
			}
			System.out.println(' ');				

		}	
		
		
		// 구구단
		for(int i = 2 ; i <= 9 ; i++) {
			for(int j = 1 ; j <= 9 ; j++ ) {
				System.out.printf("%d X %d = %d\n", i, j, i*j);
				if(j == 9) System.out.println();
			}
			
		}
		
		
		// 가로 구구단
		for(int i = 1 ; i <= 9 ; i++) {
			for(int j = 2 ; j <= 9 ; j++ ) {
				System.out.printf("%d X %d = %2d  ", j, i, j*i);
				if(j == 9) System.out.println();
			}
			
		}
		
	}

}