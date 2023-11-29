package shk;

import java.util.Comparator;

/**
 * packageName : shk
 * fileName : SortStudentByScoreInAsc
 * author : goott5
 * date : 2023-11-29
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-29          goott5             최초생성
 **/
public class SortStudentByScoreInAsc implements Comparator<Student>  {
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getScore() - o2.getScore(), 0);
    }
}
