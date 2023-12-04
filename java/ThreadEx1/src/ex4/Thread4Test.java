package ex4;

/**
 * packageName : ex4
 * fileName : Thread4Test
 * author : goott5
 * date : 2023-12-04
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-04          goott5             최초생성
 **/
public class Thread4Test {
    public static void main(String[] args) {
        Family 아빠 = new Family("아빠");
        Family 엄마 = new Family("엄마");
        Family 수혁 = new Family("수혁");

        Runnable fam = new Family("");
        Thread fTh = new Thread(fam);
        Thread mTh = new Thread(fam);
        Thread sTh = new Thread(fam);

        fTh.start();
        mTh.start();
        sTh.start();
    }
}
