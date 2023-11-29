package shk;

import java.util.Comparator;

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
public class Student {
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

}
