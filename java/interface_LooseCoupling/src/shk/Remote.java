package shk;

/**
 * packageName : shk
 * fileName : Remote
 * author : goott5
 * date : 2023-11-23
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-23          goott5             최초생성
 **/
public class Remote {
    private Tv tv;

    public Remote(){
//        tv = new Tv();
        tv = new Tv("LG"); // Tv생성자 수정시, 같이 수정해야함.
    }

    public void PowerOnTv() {
        this.tv.powerOn();
    }
}
