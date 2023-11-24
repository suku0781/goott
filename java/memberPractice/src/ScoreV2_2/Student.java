package ScoreV2_2;

public class Student {
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
        this.kor = kor;
        this.eng = eng;
        this.math = math;

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
        return "Student{" +
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
