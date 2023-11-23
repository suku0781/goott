package practice;

public class Student {
    String no;
    String name;
    int kor;
    int eng;
    int math;
    int total;
    double avg;
    char grd;

    Student(String no,String name,int kor,int eng,int math){
        this.no = no;
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        this.total = this.kor + this.eng + this.math;
        this.avg = this.total / 3f;
        this.grd = settingGrd();
    }

    char settingGrd() {
        char result;
        switch ((int)Math.floor(this.avg * 10)){
            case 10:
            case 9:
                result = 'A';
            break;
            case 8:
                result = 'B';
            break;
            case 7:
                result = 'C';
            break;
            case 6:
                result = 'D';
            break;
            default:
                result = 'F';
        }
        return result;
    }

    public String getNo() {
        return this.no;
    }

    @Override
    public String toString() {
        return "Student{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", kor=" + kor +
                ", eng=" + eng +
                ", math=" + math +
                ", total=" + total +
                ", avg=" + avg +
                ", grd=" + grd +
                '}';
    }
}
