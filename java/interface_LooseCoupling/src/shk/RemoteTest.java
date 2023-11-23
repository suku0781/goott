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
public class RemoteTest {
    public static void main(String[] args) {
        Remote 리모컨 = new Remote();
        리모컨.PowerOnTv();

        Tv tv = new Tv("LG");
        MultiRemoteController mrc = new MultiRemoteController();
        mrc.remoteControl(tv);

        Computer pc = new Computer();
        pc.powerOn();
        mrc.remoteControl(pc);

    }
}