package package2;

/**
 * packageName : package2
 * fileName : Pet
 * author : goott5
 * date : 2023-11-21
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-21          goott5             최초생성
 **/
public abstract class Pet {
    private String name;
    private String type;
    private int age;

    protected Pet(String name, String type, int age){
        this.name = name;
        this.type = type;
        this.age = age;
    }
    //protexted 상속받은 자식 클래스가 접근할 수 있음.

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void doCry();

    public String toString() {
        return "이름 : '" + name + '\'' + ", 종 : '" + type + '\'' + ", 나이 : " + age;
    }
    public String toString(int num) {
        return name;
    }
}
