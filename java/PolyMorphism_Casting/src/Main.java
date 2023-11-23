import package1.*;
import package2.*;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-21
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-21          goott5             최초생성
 **/


public class Main {
    public static void main(String[] args) {
        Cat nenne = new Cat("nenne", 3, "흰색고양이", "FCV");
        Dog jjang = new Dog("jjang", 12, "비숑", "외부기생충");

        System.out.println(nenne);
        System.out.println(jjang);

        PetDoctor doLittle = new PetDoctor("두리틀", 40);
        doLittle.doClinic(nenne);
        doLittle.doClinic(jjang);

        Pet 우리해피 = new Dog("해피", 14, "말티즈", "슬개골 탈골");
        우리해피.doCry();

        Dog happy = (Dog) 우리해피;
        happy.doWag();
        happy.doCry();
        happy.doEat();

        jjang.doWag();
        jjang.doCry();
        jjang.doEat();

    }
}