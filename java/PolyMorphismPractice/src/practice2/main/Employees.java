package practice2.main;

/**
 * packageName : practice2.main
 * fileName : employees
 * author : goott5
 * date : 2023-11-22
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-22          goott5             최초생성
 **/
public abstract class Employees {
    private String name;
    private String rank;
    private String dept;
    private String phone;
    private int birth;
    protected int salary = 9870;

    public Employees(String name, String rank, String dept, String phone, int birth) {
        this.name = name;
        this.rank = rank;
        this.dept = dept;
        this.phone = phone;
        this.birth = birth;

        if(this.rank.equals("전임")){
            this.salary += (int)(this.salary * 10);
        } else if(this.rank.equals("선임")){
            this.salary += (int)(this.salary * 30);
        } else if(this.rank.equals("책임")){
            this.salary += (int)(this.salary * 50);
        } else if(this.rank.equals("수석")){
            this.salary += (int)(this.salary * 70);
        } else {
            this.salary += (int)(this.salary * 90);
        }
    }

    public String getName() {
        return name;
    }

    public int getBirth() {
        return birth;
    }

    public String getPhone() {
        return phone;
    }

    public String getDept() {
        return dept;
    }

    public String getRank() {
        return rank;
    }

    public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    // 정규직과 알바직 사원간 급여 지급 방법이 다르므로 abstract로 한다.
    public abstract int paidSalary();

    @Override
    public String toString() {
        return "Employees{" +
                "name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", dept='" + dept + '\'' +
                ", phone='" + phone + '\'' +
                ", birth=" + birth +
                ", salary=" + salary +
                '}';
    }
}
