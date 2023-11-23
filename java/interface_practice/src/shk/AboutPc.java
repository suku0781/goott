package shk;

/**
 * packageName : shk
 * fileName : AboutPc
 * author : goott5
 * date : 2023-11-23
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-23          goott5             최초생성
 **/
public class AboutPc {
    String keyboard;
    String monitor;
    String mouse;
    String speaker;
    String tv;

    AboutPc(String keyboard, String monitor, String mouse, String speaker, String tv) {
        this.keyboard = keyboard;
        this.monitor = monitor;
        this.mouse = mouse;
        this.speaker = speaker;
        this.tv = tv;


    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
