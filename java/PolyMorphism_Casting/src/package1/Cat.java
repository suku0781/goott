package package1;

import package2.Pet;

/**
 * packageName : package1
 * fileName : Cat
 * author : goott5
 * date : 2023-11-21
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-21          goott5             최초생성
 **/
public class Cat extends Pet {
    // 기본생성자를 만들거나 Pet를 땡겨오거나 해야함.

    public String disease;

    public Cat(String name, int age, String kind, String disease){ // default -> public | protected
        super(name, kind, age);
        this.disease = disease;
    }

    public String getDisease() {
        return this.disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    @Override
    public void doCry() {
        System.out.println("야옹");
    }




}



