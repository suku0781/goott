package package2;

import package1.Dog;

/**
 * packageName : package2
 * fileName : PetDoctor
 * author : goott5
 * date : 2023-11-21
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-21          goott5             최초생성
 **/
public class PetDoctor {
    private String name;
    private int age;

    public PetDoctor(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void doClinic(Pet pet) {
        System.out.println(pet.toString(1) + " 를 치료한다.");
    }

    public String toString(){
        return "[진료의사명 :  " + this.getName() + ", 나이 : " + this.getAge() + "]";
    }

}
