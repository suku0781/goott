package ScoreV2;

import java.util.Scanner;

public class main {
    static Student[] students = new Student[3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = 0;
        String yn = "";
        do{
            inpStd(cnt);
            System.out.print("학생을 더 등록하시겠습니까?(y/n) >> ");
            yn = sc.nextLine();
            cnt++;
        } while(yn.equals("Y") || yn.equals("y"));

        for(Student i : students){
            if(i != null){
                System.out.println(i);
            }
        }


    }

    public static void inpStd(int count) {
        Scanner sc = new Scanner(System.in);
        System.out.print("학번을 입력하십시오.(남은 학생등록 횟수 "+(students.length-count-1)+") >> ");
        int stdNum = Integer.parseInt(sc.nextLine());
        System.out.print("이름을 입력하십시오. >> ");
        String name = sc.nextLine();
        System.out.print("국어 점수를 입력하십시오. >> ");
        int kor = Integer.parseInt(sc.nextLine());
        System.out.print("영어 점수를 입력하십시오. >> ");
        int eng = Integer.parseInt(sc.nextLine());
        System.out.print("수학 점수를 입력하십시오. >> ");
        int math = Integer.parseInt(sc.nextLine());

        Student std = new Student(stdNum, name, kor, eng, math);
        if(std.getName() != null ) {
            students[count] = std;
            System.out.println("학생 등록 완료.");
        } else {
            System.out.println("학생 등록 실패.");
        }

    }


}
