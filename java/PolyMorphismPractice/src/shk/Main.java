package shk;

import electronics.Computer;
import electronics.Monitor;
import electronics.Tv;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-22
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-22          goott5             최초생성
 **/
public class Main {
    public static void main(String[] args) {
//        ## 연습문제)
//        고객(Buyer)이 전자제품 Tv, Monitor, Computer 를 구매하려 한다.
//        100만원 돈으로 가격이 20만원인 Tv, 가격이 10만원인 Monitor, 30만원인 Computer를 구입했다.
//        구입가격의 10%를 적립포인트로 받을 수 있다.
//        고객이 구입한 상품 목록과 총 가격, 적립포인트를 출력하시오.
        ElectronicProduct lgTv = new Tv("올레드 오브제컬렉션 Posé", 2140000, 10, "LG", "48LX1QKNA", 55);
        ElectronicProduct lgMonitor = new Monitor("스탠바이미", 1090000, 10, "LG", "27ART10DKPL", 27);
        ElectronicProduct assemblyComputer = new Computer("조립컴퓨터", 580000, 10, "Z790 AORUS XTREME X", "GeForce RTX 4090", "i9 13900k", 128, 5012, 20048);

        Customer c = new Customer(5000000);
        System.out.println("가지고있는돈 : " + c.getHaveMoney());
        System.out.println(lgTv.getProductName()+" 가격 : "+lgTv.getPrice());
        c.setHaveMoney(c.withdrawMoney(lgTv.getPrice(), c.getHaveMoney()));
        System.out.println(lgMonitor.getProductName()+" 가격 : "+lgMonitor.getPrice());
        c.setHaveMoney(c.withdrawMoney(lgMonitor.getPrice(), c.getHaveMoney()));
        System.out.println(assemblyComputer.getProductName()+" 가격 : "+assemblyComputer.getPrice());
        c.setHaveMoney(c.withdrawMoney(assemblyComputer.getPrice(), c.getHaveMoney()));
        System.out.println("남은돈 : " + c.getHaveMoney());
        System.out.println("적립금액 : " + c.getSpentMoney() / 10);


    }
}