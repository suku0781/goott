package shk;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-23
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-23          goott5             최초생성
 **/
public class InterfaceEx2 {

    public static void doWork(DataAccessObject dao){
        dao.select();
        dao.insert();
        dao.update();
        dao.delete();
    }


    public static void main(String[] args) {
//        InterfaceEx2.doWork();
        InterfaceEx2.doWork(new OracleDao());
        InterfaceEx2.doWork(new MySqlDao());
    }
}