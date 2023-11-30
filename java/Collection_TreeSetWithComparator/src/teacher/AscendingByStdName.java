package teacher;

import java.util.Comparator;

/**
 * packageName : teacher
 * fileName : AscendingBy
 * author : goott5
 * date : 2023-11-30
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-30          goott5             최초생성
 **/
public class AscendingByStdName implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {

        return o1.getStdName().compareTo(o2.getStdName());
    }
}
