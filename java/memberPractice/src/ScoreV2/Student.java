package ScoreV2;

public class Student {
    // 학번, 이름, 국어, 영어, 수학, 총점, 평균, 학점
    // 전체 학점의 총점
    // 각 과목은 0 ~ 100 이하 제약

    int stdNum;
    String name;
    int kor;
    int eng;
    int math;
    int total;
    double avg;
    String credit;

    Student(int stdNum, String name, int ...par) {
        boolean flag = false;
        for(int i = 0 ; i < par.length ; i++){
            if(par[i] >= 0 && par[i] <= 100){
                if( i == 0 ) this.kor = par[i];
                if( i == 1 ) this.eng = par[i];
                if( i == 2 ) this.math = par[i];
            } else {
                flag = true;
            }
        }
        if(flag){
            System.out.println("점수 기준에 부합하지 않습니다.");
            return;
        }
        this.stdNum = stdNum;
        this.name = name;
        this.total = this.kor + this.eng + this.math;
        this.avg = Math.floor(((float)(this.total) / par.length)*100)/100;

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
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{학번=" + stdNum +
                ", 이름='" + name + '\'' +
                ", 국어=" + kor +
                ", 영어=" + eng +
                ", 수학=" + math +
                ", 총점=" + total +
                ", 평균=" + avg +
                ", 학점=" + credit +
                '}';
    }
}
