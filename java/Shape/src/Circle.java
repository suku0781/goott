/**
 * packageName : PACKAGE_NAME
 * fileName : Circle
 * author : goott5
 * date : 2023-11-20
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-20          goott5             최초생성
 **/
public class Circle extends Shape {
    private int radius;

    public Circle(String name, String color, Point p, int radius) {
        super(name, color, p);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("반지름이 "+this.radius + "인 원을 생성합니다.");
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
