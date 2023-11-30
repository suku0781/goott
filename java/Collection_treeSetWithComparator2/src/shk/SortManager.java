package shk;

/**
 * packageName : shk
 * fileName : SortManager
 * author : goott5
 * date : 2023-11-30
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-30          goott5             최초생성
 **/
public class SortManager {
    public static Sortable getSortMethod(int i){
        Sortable result = null;
        switch (i) {
            case 1:
            result = new DescendingByStdNo();
            break;
            case 2:
            result = new AscendingByStdName();
            break;
            case 3:
            result = new DescendingByStdScore();
            break;
            case 9:
            System.exit(0);
        }
        return result;
    }

}
