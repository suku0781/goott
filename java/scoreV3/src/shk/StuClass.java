package shk;

import java.util.Arrays;

public class StuClass {
    private int no;
    private String className;
    private Student[] stuList;
    private int total;
    private float totalAvg;
    public static final int STU_COUNT = 4;

    // 생성자
    public StuClass(int no, String className){
        this.no = no;
        this.className = className;
        this.stuList = new Student[STU_COUNT]; // Student 객체 배열 생성. (크기 : STU_COUNT)
    }

    // 생성자 오버로딩
    public StuClass(int no, String className, Student[] stdList){
        this.no = no;
        this.className = className;
        this.stuList = stdList;
    }



    // 해당 반에 학생 추가 메서드
    public void addStudent(Student student, int cnt) {
        this.stuList[cnt] = student;
    }

    // getter, setter
    public int getNo(){
        return this.no;
    }
    public String getClassName(){
        return this.className;
    }
    public Student[] getStuList(){
        return this.stuList;
    }

    // 반번호는 수정 불가
    public void setClassName(String className){
        this.className = className;
    }
    // stuList는 메서드로


    public void outputAllStd(){
        for(int i = 0 ; i < Main.getCurrentSaveStdCnt(); i++) {
            System.out.println(this.stuList[i].toString());
        }
    }

    public int calcTotal(){
        this.total = 0;
        for(int i = 0 ; i < Main.getCurrentSaveStdCnt(); i++) {
            this.total += this.stuList[i].getTotal();
        }

        return this.total;
    }

    public float calcAvg(){
        this.totalAvg = 0;
        this.totalAvg = Math.round((((float) this.total / Main.getCurrentSaveStdCnt()) / 3) * 100) / 100f;

        return this.totalAvg;
    }

    @Override
    public String toString() {
        return "StuClass{" +
                "no=" + no +
                ", className='" + className + '\'' +
                ", stuList=" + Arrays.toString(stuList) +
                ", total=" + total +
                ", totalAvg=" + totalAvg +
                '}';
    }
}
