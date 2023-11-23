package ScoreV2_1;

import java.util.Scanner;

public class ScoreV2_1 {
    static Student[] students = new Student[3];
    public static void main(String[] args){
        String yn = "";
        int count = 0;
        do{
            count = ScoreV2_1.inputAccountInfo(count);

            System.out.print("count "+count);
            if(count < students.length){
                System.out.print("추가등록(y/n) >> ");
                Scanner sc = new Scanner(System.in);
                yn = sc.nextLine();
            }
        } while(yn.equals("Y") || yn.equals("y"));
        outputFunc();

    }

    public static int inputAccountInfo(int count){
        Scanner sc = new Scanner(System.in);
        System.out.println("학생등록 ("+ (students.length-count-1) + ")");
        System.out.print("학번 입력 >> ");
        int stdNum = Integer.parseInt(sc.nextLine());
        System.out.print("이름 입력 >> ");
        String name = sc.nextLine();
        System.out.print("국어 점수 입력 >> ");
        int kor = Integer.parseInt(sc.nextLine());
        System.out.print("영어 점수 입력 >> ");
        int eng = Integer.parseInt(sc.nextLine());
        System.out.print("수학 점수 입력 >> ");
        int math = Integer.parseInt(sc.nextLine());
        Student std = new Student(stdNum, name, kor, eng, math);
        if(std.getName() != null){
            students[count] = std;
            count += 1;
        }
        return count;
    }

    public static void outputFunc(){
        System.out.printf("학번\t\t\t이름\t\t\t국어\t\t\t영어\t\t\t수학\t\t\t총점\t\t\t평균\t\t\t학점\n");
        for(int i = 0 ; i < students.length ; i++){
            System.out.printf("%d\t\t\t%s\t\t\t%d\t\t\t%d\t\t\t%d\t\t\t%d\t\t\t%f\t\t\t%s", students[i].getStdNum(), students[i].getName(), students[i].getKor(), students[i].getEng(), students[i].getMath(), students[i].getTotal(), Math.floor(students[i].getAvg()*100)/100, students[i].getCredit());
            System.out.println();
        }

    }
}
