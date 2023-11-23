package studentV2;

public class OOPEx3_instanceCopy {
    public static void main(String[] args) {
        Student std1 = new Student("1601050", "김수혁", 68, 87, 95);
        System.out.println("std1 생성 : " + std1 + ", " + std1.hashCode());

        // 얕은 복사
        Student std1_copy = std1;
        System.out.println("std1 앝은복사 : " + std1_copy + ", " + std1.hashCode());

        // 원본 수정
        std1.setEng(0);
        System.out.println("std1 원본수정 : " + std1 + ", " + std1.hashCode());
        System.out.println("std1_copy : " + std1_copy + ", " + std1_copy.hashCode()); // 왜 안바뀌지?

        // 생성자를 이용한 깊은 복사
        Student deepCopy = new Student(std1);
        System.out.println("std1 원본 : " + std1 + ", " + std1.hashCode());
        System.out.println("std1 깊은복사 : " + deepCopy + ", " + deepCopy.hashCode());

        deepCopy.setKor(100);
        System.out.println("std1 원본 : " + std1 + ", " + std1.hashCode());
        System.out.println("std1 깊은복사 : " + deepCopy + ", " + deepCopy.hashCode());

        // 잘못된 형태로 입력 시 생성자에서 예외를 의도적으로 발생시켜 tru{}catch()로 걸러내기
        try{
            Student std2 = new Student("1730541", "홍길동", -80, -90, -100);
            System.out.println("std2 원본 : " + std2 + ", " + std2.hashCode());
        } catch(IllegalArgumentException e){
            System.out.println("객체를 다시 생성하십시오.");
            System.out.println(e); // 생성자에서 의도적으로 날린 오류e 를 출력함.
            System.exit(0);
        }

    }

    public static class Student {
        // 학번, 이름, 국어, 영어, 수학, 총점, 평균, 학점
        // 전체학생의 총점
        // 각 과목의 점수는 0 점 이상 100 점 이하

        // 인스턴스 변수 생성
        private String stuNo;
        private String stuNmae;
        private int kor;
        private int eng;
        private int math;
        private int total;
        private double avg;
        private char grd;

        private float allStudentTotal;

        // 생성자
        public Student(){

        }

        // 생성자 오버로딩
        public Student(String stuNo, String stuNmae, int kor, int eng, int math){
            this.stuNo = stuNo;
            this.stuNmae = stuNmae;
            if(kor >= 0 && kor <= 100 && eng >= 0 && eng <= 100 && math >= 0 && math <= 100){
                this.kor = kor;
                this.eng = eng;
                this.math = math;
            } else {
                throw new IllegalArgumentException("학번 "+ stuNo + "학생 점수를 확인하십시오.");
            }

            setScores();

        }

        // getter setter
        public String getStuNo() {
            return stuNo;
        }

        public void setStuNo(String stuNo) {
            this.stuNo = stuNo;
        }

        public String getStuNmae() {
            return stuNmae;
        }

        public void setStuNmae(String stuNmae) {
            this.stuNmae = stuNmae;
        }

        public int getKor() {
            return kor;
        }

        public void setKor(int kor) {
            if(kor >= 0 && kor <= 100){
                this.kor = kor;
                setScores();
            }
        }

        public int getEng() {
            return eng;
        }

        public void setEng(int eng) {
            if(eng >= 0 && eng <= 100){
                this.eng = eng;
                setScores();
            }
        }

        public int getMath() {
            return math;
        }

        public void setMath(int math) {
            if(math >= 0 && math <= 100){
                this.math = math;
                setScores();
            }
        }

        public int getTotal() {
            return total;
        }

        public void setTotal() {
            this.total = this.kor + this.eng + this.math;
        }

        public double getAvg() {
            return avg;
        }

        public void setAvg() {
            this.avg = this.total / 3f;
        }

        public char getGrd() {
            return grd;
        }

        public void setGrd() {
            switch((int)Math.floor(this.avg/10)){
                case 10:
                case 9:
                    this.grd = 'A';
                    break;
                case 8:
                    this.grd = 'B';
                    break;
                case 7:
                    this.grd = 'C';
                    break;
                case 6:
                    this.grd = 'D';
                    break;
                default:
                    this.grd = 'F';
            }
        }

        //생성자 오버로딩(생성자를 이용한 깊은 복사)
        public Student(Student std){
            this.stuNo = std.stuNo;
            this.stuNmae = std.stuNmae;
            this.kor = std.kor;
            this.eng = std.eng;
            this.math = std.math;
            this.total = std.total;
            this.avg = std.avg;
            this.grd = std.grd;


        }

        public float getAllStudentTotal() {
            return allStudentTotal;
        }

        public void setAllStudentTotal() {
            allStudentTotal += getTotal();
        }

        public void setScores() {
            setTotal();
            setAvg();
            setGrd();

        }

        // toString
        @Override
        public String toString() {
            return "studentV2.OOPEx3_instanceCopy.Student{" +
                    "stuNo='" + stuNo + '\'' +
                    ", stuNmae='" + stuNmae + '\'' +
                    ", kor=" + kor +
                    ", eng=" + eng +
                    ", math=" + math +
                    ", total=" + total +
                    ", avg=" + avg +
                    ", grd=" + grd +
                    '}';
        }


        // 학생 객체 복사 생성자 만들기


    }
}