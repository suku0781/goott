package shk;

import java.util.*;

/**
 * packageName : shk
 * fileName : Class
 * author : goott5
 * date : 2023-11-29
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-29          goott5             최초생성
 **/
public class StdClass {
    private int classNo;
    private Set<Student> stdSet;
    ArrayList<String> list = new ArrayList<>(); // set을 ArrayList로 변경
    // 생성자
    public StdClass(int classNo) {
        this.classNo = classNo;
        this.stdSet = new TreeSet<Student>(new SortStudentByScoreInAsc());
    }

    // getter, setter 반번호 변경 불가
    public int getClassNo() {
        return classNo;
    }

    public Set<Student> getStdSet() {
        return stdSet;
    }

    // 학생을 Treeset에 추가
    public void setStdSet(Set<Student> stdSet) {
        this.stdSet = stdSet;
    }

    public void addStudent(Student s){
        this.stdSet.add(s);
        for(int i = 0 ; i < this.stdSet.size() ; i++) {
            System.out.println("s : " + s + ", s getStdNo() : " + s.getStdNo() + ", this.stdSet : " + this.stdSet(i));
//            if(s.getStdNo().equals(this.stdSet[i].getStdNo())){
//
//            }
        }

//        for(Student std : this.stdSet){
//            if(s.getStdNo().equals(std.getStdNo())){
//                System.out.println("test2 : " + s);
//                this.stdSet.remove(s);
//            }
//        }

    }

    // 학생 전체 목록 출력
    public void outputEntireStudent(){
        for(Student std : this.stdSet) {
            System.out.println(std.toString() + ", hashCode : " + std.hashCode());
        }
    }



    @Override
    public String toString() {
        return "stdClass{" + "classNo=" + classNo + ", stdSet=" + stdSet + '}';
    }

}
