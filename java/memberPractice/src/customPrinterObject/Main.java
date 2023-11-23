package customPrinterObject;

public class Main {
    public static void main(String[] args) {
        printer.printer("홀길동");
        printer printer2 = new printer(10);
        printer printer3 = new printer(true);
        printer printer4 = new printer(5.7);
        printer printer5 = new printer(5.7, 10000, 2000 * 10);
        printer printer6 = new printer(5.7f);
    }
}
