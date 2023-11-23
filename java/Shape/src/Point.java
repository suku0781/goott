/**
 * packageName : PACKAGE_NAME
 * fileName : Point
 * author : goott5
 * date : 2023-11-20
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-20          goott5             최초생성
 **/
public class Point {
    private int x;
    private int y;

    // 생성자
    Point(){

    }

    // 생성자 오버로딩
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getter, setter

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // bean
    // java안에 멤버변수 + 생성자 + getter,setter + toString()

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
