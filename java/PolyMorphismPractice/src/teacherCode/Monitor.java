package teacherCode;

public class Monitor extends Product{
    public Monitor(int price) {
        super(100000);

    }

    @Override
    public String toString() {
        return "Monitor [getPrice()=" + getPrice() + ", getPoint()=" + getPoint() + ", toString()=" + super.toString()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
    }




}