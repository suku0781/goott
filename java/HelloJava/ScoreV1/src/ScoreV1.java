
public class ScoreV1 {

	public static void main(String[] args) {
		// 성적표
		String name = "둘리";
		int kor = 88;
		int eng = 89;
		int math = 47;
		
		// 총점, 평균, 학점 구해서 출력
		int total = kor + eng + math;
		float avg = Math.round((total / 3F) * 100)/100f;
		char grd = ' ';
//		if(Math.floor(avg) > 90) {
//			grd = 'A';
//		} else if(Math.floor(avg) >= 80 && Math.floor(avg) < 90) {
//			grd = 'B';
//		} else if(Math.floor(avg) >= 70 && Math.floor(avg) < 80) {
//			grd = 'C';
//		} else if(Math.floor(avg) >= 60 && Math.floor(avg) < 70) {
//			grd = 'D';
//		} else {
//			grd = 'F';
//		}
		switch( (int)Math.floor(avg / 10d) ) {
			case 10:
			case 9:
				grd = 'A';
				break;
			case 8:
				grd = 'B';
				break;
			case 7:
				grd = 'C';
				break;
			case 6:
				grd = 'D';
				break;	
			default:
				grd = 'F';
		}
		
		System.out.println("이름 : " + name);
		System.out.println("총점 : " + total);
		System.out.println("평균 : " + avg);
//		System.out.println("학점 : " + grd);
		System.out.printf("학점 : %c", grd);
//		System.out.printf("name: %s\t kor: %i\t eng: %i\t math: %i\t avg: %f\t grd: %c", name, kor, eng, math, avg, grd);
		

	}

}
