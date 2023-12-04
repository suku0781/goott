package ex4;

/**
 * packageName : ex4
 * fileName : Account
 * author : goott5
 * date : 2023-12-04
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-04          goott5             최초생성
 **/
public class Account {
    private int balance = 10000;

    Account(){

    }

    // 잔액을 가져올 getter
    public int getBalance() {
        return balance;
    }

    public synchronized boolean withDraw(int money){
        boolean result = false;
        synchronized (this) {
            if(this.balance >= money){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                this.balance -= money;
                result = true;
            }
        }

        return result;
    }
}
