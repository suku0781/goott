package teacher;

/**
 * packageName : shk
 * fileName : Student
 * author : goott5
 * date : 2023-11-29
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-29          goott5             최초생성
 **/
public class Student implements Comparable<Student> {
    private String stdNo;
    private String stdName;
    private int score;

    public Student(String stdNo, String stdName, int score){
//        super(); // 생략 가능.
        this.stdNo = stdNo;
        this.stdName = stdName;
        this.score = score;

    }

    // getter and setter 학번 수정 불가
    public String getStdNo() {
        return stdNo;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "{" + "stdNo='" + stdNo + '\'' + ", stdName='" + stdName + '\'' + ", score=" + score + '}';
    }

    //comparator 는 compareTo 추상메서드
    //comparable 는 compare 추상메서드

    @Override
    public int compareTo(Student o) {
        return this.stdNo.compareTo(o.stdNo); // 기본적으로 오름차순 정렬.
    }
    // 인터페이스는 표준 계약을 나타내는 것. 여기에 직접 comparator를 하는건 좋은 방법이 아님.
    // 새로운 클래스를 만들어서 하는것이 좋음.
    // 클래스 자체가 가지고있는 비교기.
}
