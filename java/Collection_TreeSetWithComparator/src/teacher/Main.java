package teacher;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-29
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-29          goott5             최초생성
 **/
public class Main {
    public static void main(String[] args) {
        // 반먼저 생성.
        StdClass c1 = new StdClass(1);

        Student s1 = new Student("1601050", "김수혁", 80);
        Student s2 = new Student("B611058", "노하영", 90);
        Student s3 = new Student("23003", "마이콜", 70);
        Student s4 = new Student("23003", "도우너", 100);
        Student s5 = new Student("23003", "둘리", 100);
        Student s6 = new Student("23003", "둘리", 100);
        Student s7 = new Student("23003", "김수혁", 100);

        c1.addStudent(s1);
        c1.addStudent(s2);
        c1.addStudent(s3);
        c1.addStudent(s4);
        c1.addStudent(s5);
        c1.addStudent(s6);
        c1.addStudent(s7);


        c1.outputEntireStudent();

    }
}