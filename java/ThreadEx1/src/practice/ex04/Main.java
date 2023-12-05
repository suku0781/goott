package practice.ex04;

/**
 * packageName : practice.ex04
 * fileName : Main
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
class Family implements Runnable{
    private String name;
    private Account acc = new Account(); // 가족은 가족 통장을 가진다.
    Family(String name){
        super();
        this.name = name;
    }
    @Override
    public void run() {
        while(this.acc.getBalance() > 0){
            int money = (int)((Math.random() * 10000) / 10 )* 10 + 10;
            if(this.acc.withdraw(money) != -1){
                System.out.println(this.name + "이/가 " + money + "원을 출금하였습니다. 남은 잔액은 " + this.acc.getBalance() + "원 입니다.");
            }
        }
    }
}

class Account{
    private int balance = 10000;

    public int getBalance() {
        return balance;
    }

    public synchronized int withdraw(int money) {
        if(this.balance >= money){
            try {
                Thread.sleep(2000);
                return this.balance -= money;

            } catch (InterruptedException e) {
                e.printStackTrace();
                return -1;
            }
        } else {
            return -1;
        }

    }
}

public class Main {

    public static void main(String[] args) {
        Runnable fam = new Family("");
        Thread t1 = new Thread(fam);

        t1.start();
    }
}
