package ex4;

/**
 * packageName : ex4
 * fileName : Family
 * author : goott5
 * date : 2023-12-04
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-04          goott5             최초생성
 **/
public class Family implements Runnable{
    private String name;
    private Account acc = new Account(); // 가족은 가족 통장을 가지고있다.

    Family(String name){
        super();
        this.name = name;
    }

    @Override
    public void run() {
        // 가족들이 acc에서 돈을 인출
        while(this.acc.getBalance() > 0){
            int money = (int)((Math.random() * 4000) / 100) * 100 + 100;

            if(this.acc.withDraw(money)){
                System.out.println(this.name + "이/가 " + money + "원을 출금하였습니다. 남은 잔액은 " + this.acc.getBalance() + "원 입니다.");
            }
        }


    }
}
