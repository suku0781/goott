package shk;

/**
 * packageName : shk
 * fileName : NoPositionInteger
 * author : goott5
 * date : 2023-11-24
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-24          goott5             최초생성
 **/
public class NoPositionInteger extends Exception {
    private int errorCode = 503;
//    private String msg;

    // 생성자
    NoPositionInteger(String msg) {
//        this.getMessage() = msg;
//        this.msg = msg;
        super(msg);
    }

    @Override
    public String getMessage() {
        return "error code ; " + this.errorCode + ", " + super.getMessage() ;
    }
}
