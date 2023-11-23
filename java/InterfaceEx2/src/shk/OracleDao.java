package shk;

/**
 * packageName : shk
 * fileName : OracleDao
 * author : goott5
 * date : 2023-11-23
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-23          goott5             최초생성
 **/
public class OracleDao implements DataAccessObject{

    @Override
    public void select() {
        System.out.println("Oracle DB에서 검색.");
    }

    @Override
    public void insert() {
        System.out.println("Oracle DB에 추가.");
    }

    @Override
    public void update() {
        System.out.println("Oracle DB에 수정.");
    }

    @Override
    public void delete() {
        System.out.println("Oracle DB의 튜플 삭제.");
    }
}
