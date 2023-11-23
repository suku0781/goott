package ObjectEx3;

public class Account {
    /*
    은행계좌 객체인 Account객체는 잔고(balance)필드를 가지고 있다.
    balance필드는 음수값이 될 수 없고, 최대 백만원까지만 저장할 수 있다.
    외부에서 balance필드를 마음대로 변경하지 못하도록 하고
    0 <= balance <= 1,000,000 범위의 값만 가질 수 있도록 Account클래스를 작성하세요.
    * getter, setter 이용
    * 0과 1,000,000은 MIN_BALANCE 와 MAX_BALANCE상수를 선언해서 이용
    * setter의 매개값이 음수이거나 백만원을 초과하면 현재 balance값을 유지
    * */
    public static final int MIN_BALANCE = 0;
    public static final int MAX_BALANCE = 1000000;
    String accountNo;
    String owner;
    int balance;

    Account(int count, String owner){
        this.accountNo = "0411"+String.format("%07d", count)+"97";
        this.owner = owner;
        this.balance = 0;
    }

    // getter
    public String getAccount() {
        return this.accountNo;
    }
    public String getOwner() {
        return this.owner;
    }
    public int getBalance() {
        return this.balance;
    }

    // setter
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public void setBalance(int balance, String type) {
        if(type.equals("deposit")){
            if(balance >= MIN_BALANCE && balance <= MAX_BALANCE){
                this.balance += balance;
                System.out.println("나라사랑체크 계좌" + this.getAccount() + "에 "+ balance + "원이 성공적으로 입급되었습니다.");
            } else {
                System.out.println("예금 1회 당 1000000원 까지 가능합니다.");
            }
        } else {
            if(balance >= MIN_BALANCE && balance <= MAX_BALANCE){
                if(this.balance > balance){
                    this.balance -= balance;
                    System.out.println("나라사랑체크 계좌" + this.getAccount() + "에 "+ balance + "원이 성공적으로 출급되었습니다.");
                } else {
                    System.out.println("계좌 잔액이 부족합니다.");
                }

            } else {
                System.out.println("출금 1회 당 1000000원 까지 가능합니다.");
            }
        }
        System.out.println("계좌 잔액은 " + getBalance() + "원 입니다.");
    }

//    @Override
//    public String toString() {
//        return "Account{" +
//                "계좌번호 = '" + accountNo + '\'' +
//                ", 예금주 ='" + owner + '\'' +
//                ", 잔액 =" + balance +
//                '}';
//    }



    public String 잔액조회() {
        return this.owner + "님 KB나라사랑체크 계좌 "+ this.accountNo + "의 잔액은 " + this.balance + "입니다.";
    }
}
