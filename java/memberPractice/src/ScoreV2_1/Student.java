package ScoreV2_1;

public class Student {
    int stdNum;
    String name;
    int kor;
    int eng;
    int math;
    int total;
    double avg;
    String credit;

    Student(int stdNum, String name, int kor, int eng, int math){
        if( (kor >= 0 && kor <= 100) || (eng >= 0 && eng <= 100) || (math >= 0 && math <= 100) ){
            this.stdNum = stdNum;
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
            this.total = this.kor + this.eng + this.math;
            this.avg = Math.floor(((float)(this.total) / 3)*100)/100;
            switch((int)Math.floor(this.avg/10)){
                case 10:
                case 9:
                    this.credit = "A";
                    break;
                case 8:
                    this.credit = "B";
                    break;
                case 7:
                    this.credit = "C";
                    break;
                case 6:
                    this.credit = "D";
                    break;
                default:
                    this.credit = "F";
            }
        } else {
            System.out.println("점수기준에 부합하지 않습니다.");
        }
    }

    public int getStdNum() {
        return stdNum;
    }

    public void setStdNum(int stdNum) {
        this.stdNum = stdNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String 학생배열출력(){
        return "Account{" +
                "학번: " + stdNum +
                ", 이름: '" + name + '\'' +
                ", 국어: " + kor +
                ", 영어: " + eng +
                ", 수학: " + math +
                ", 총점: " + total +
                ", 평균: " + avg +
                ", 학점: '" + credit + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Account{" +
                "stdNum=" + stdNum +
                ", name='" + name + '\'' +
                ", kor=" + kor +
                ", eng=" + eng +
                ", math=" + math +
                ", total=" + total +
                ", avg=" + avg +
                ", credit='" + credit + '\'' +
                '}';
    }
}
