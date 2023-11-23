package ScoreV2_2;

public class ScoreV2_2 {
    public static void main(String[] args){
        // 학생 객체 3명 만들기
        // 각 학생 성적표 뽑기
        // 전체 학생의 촘점을 출력

        Student std = new Student("1601050", "김수혁", 79, 90, 20);
        Student std2 = new Student("1000000", "홍길동", 100, 50, 78);
        Student std3 = new Student("2109501", "이주연", 85, 89, 99);
        System.out.println(std.toString());
        std.setKor(100);
        System.out.println(std.toString());
        System.out.println(std2.toString());
        System.out.println(std3.toString());
//        System.out.println(Student.getAllStudentTotal());
    }
}
