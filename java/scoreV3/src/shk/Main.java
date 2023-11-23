package shk;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static int currentSaveStdCnt = 0; // 현재 생성된 학생의 수

    //getter
    public static int getCurrentSaveStdCnt(){
        return currentSaveStdCnt;
    }

    // 메뉴 출력
    private static void outputMenu(){
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                     성적표 V.3                                                          ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("1. 반 생성. | 2. 반에 학생 추가 | 3. 전체 학생 출력 | 4. 반, 학생 자동생성 | 5. 학번으로 학생 점수 조회 | 9. 종료");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.print("번호 선택 >> ");
    }

    // 반생성 메서드
    private StuClass createClass(){
        System.out.print("반 번호 입력 >> ");
        int classNo = Integer.parseInt(sc.nextLine());
        System.out.print("반 이름 입력 >> ");
        String className = sc.nextLine();

        // 반 객체 생성
        StuClass c1 = new StuClass(classNo, className);
        System.out.println(c1);
        return c1;
    }

    // 학생 추가 메서드
    private void inputStudent(StuClass c1) {
        if(c1 == null){
            System.out.println("반 생성 후 학생 추가가 가능합니다.");
            return;
        } else{
            if(currentSaveStdCnt < StuClass.STU_COUNT){
                System.out.print("학번 입력 >> ");
                String no = sc.nextLine();
                System.out.print("이름 입력 >> ");
                String name = sc.nextLine();
                System.out.print("국어점수 입력 >> ");
                int kor = Integer.parseInt(sc.nextLine());
                System.out.print("영어점수 입력 >> ");
                int eng = Integer.parseInt(sc.nextLine());
                System.out.print("수학점수 입력 >> ");
                int math = Integer.parseInt(sc.nextLine());

                c1.addStudent(new Student(no, name, kor, eng, math), currentSaveStdCnt);
                currentSaveStdCnt++;

            } else {
                System.out.println("해당 반의 학생수가 초과됩니다.");
            }
        }
    }

    // 해당 반의 전체 학생 출력 메서드
    private void outputEntireStudent(StuClass stuc) {
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                     성적표 V.3                                                          ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("반 번호 : " + stuc.getNo() + " | 과정명 : " + stuc.getClassName());

        System.out.println("debugger point");

        stuc.outputAllStd();
        System.out.println("전체 총점 : " + stuc.calcTotal());
        System.out.println("전체 평균 : " + stuc.calcAvg());
    }

    // 자동 반, 학생 추가 메서드
    private StuClass autoInputStd() {
        Student[] stdList = {   new Student("23001", "홍길동", 100, 85, 93),
                                new Student("23002", "홍길갑", 84, 22, 87),
                                new Student("23003", "홍길을", 19, 65, 98),
                                new Student("23004", "홍길병", 98, 97, 100) }; // 학생 배열 생성.
        currentSaveStdCnt = stdList.length;
        StuClass c1 = new StuClass(5, "java", stdList);
        return c1;
    }

    // 5. 학번으로 학생 점수 조회 메서드
    private void selectStdAsStdno(StuClass stuc) {
        System.out.print("학번 입력 >> ");
        String no = sc.nextLine();
        boolean flag = false;
        for(Student std : stuc.getStuList()){
            if(std.getStuNo().equals(no)){
                System.out.printf("%s학번 %s님의 성적입니다.\n", std.getStuNo(), std.getStuNmae());
                System.out.printf("국어 : %d, 영어 : %d, 수학 : %d, 총점 : %d, 평균 : %f, 학점 : %s\n",std.getKor(), std.getEng(), std.getMath(), std.getTotal(), std.getAvg(), std.getGrd());
                flag = true;
                break;
            }
        }
        if(!flag) System.out.println(no+"학번 학생정보가 검색결과가 않습니다.");

        System.out.print("더 탐색하시겠습니까? (Y/N) >>");
        String moreExpYn = sc.nextLine();
        if(moreExpYn.equals("Y") || moreExpYn.equals("y")) selectStdAsStdno(stuc);

    }

    public static void main(String[] args) {
        Main m = new Main();
        StuClass stuc = null;

        while(true){
            outputMenu();
            int memu = Integer.parseInt(sc.nextLine());

            switch (memu){
                case 1:
                    stuc = m.createClass();
                break;
                case 2:
                    m.inputStudent(stuc);
                break;
                case 3:
                    m.outputEntireStudent(stuc);
                break;
                case 4:
                    stuc = m.autoInputStd();
                    break;
                case 5:
                    m.selectStdAsStdno(stuc);
                    break;
                case 9:
                    System.exit(0);
                break;
            }
        }
    }
}