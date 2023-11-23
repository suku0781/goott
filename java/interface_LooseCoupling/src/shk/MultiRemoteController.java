package shk;

/**
 * packageName : shk
 * fileName : MultiRemoteController
 * author : goott5
 * date : 2023-11-23
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-23          goott5             최초생성
 **/
public class MultiRemoteController implements Remotable{
    @Override
    public void remoteControl(ElectronicDevice ed) {
        System.out.println("controlling " + ed.toString());
    }
}
