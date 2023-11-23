/**
 * packageName : PACKAGE_NAME
 * fileName : Shape
 * author : goott5
 * date : 2023-11-20
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-20          goott5             최초생성
 **/
public abstract class Shape {
    private String name;
    private String color;
    private Point p;

    // 기본 생성자
    Shape(){

    }

    // 생성자
    public Shape(String name, String color, Point p) {
        this.name = name;
        this.color = color;
        this.p = p;
    }

    // getter, setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    // 화면에 그려주는 메서드
    // 뭘그려야할 지 모르면 추상메서드(abstract)가 된다.
    // 추상메서드는 body가 없다.
    // 추상메서드를 가지고있는 클래스는 추상클래스가되므로 선언시 abstract를 포함해야한다.
    public abstract void draw();

    @Override
    public String toString() {
        return "Shape{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", p=" + p +
                '}';
    }

    public Point toString(int num) {
        return p;
    }
}
