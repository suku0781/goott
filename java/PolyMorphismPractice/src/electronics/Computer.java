package electronics;

import shk.ElectronicProduct;

/**
 * packageName : shk
 * fileName : Computer
 * author : goott5
 * date : 2023-11-22
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-22          goott5             최초생성
 **/
public class Computer extends ElectronicProduct {
    private String motherBoard;
    private String graphicCard;
    private String cpu;
    private int ram;
    private int ssdSize;
    private int hddSize;

    public Computer(String productName, int price, int pointRate, String motherBoard,String graphicCard,String cpu, int ram, int ssdSize, int hddSize){
        super(productName, price, pointRate);
        this.motherBoard = motherBoard;
        this.graphicCard = graphicCard;
        this.cpu = cpu;
        this.ram = ram;
        this.ssdSize = ssdSize;
        this.hddSize = hddSize;
    }

    public String getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(String motherBoard) {
        this.motherBoard = motherBoard;
    }

    public String getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(String graphicCard) {
        this.graphicCard = graphicCard;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getSsdSize() {
        return ssdSize;
    }

    public void setSsdSize(int ssdSize) {
        this.ssdSize = ssdSize;
    }

    public int getHddSize() {
        return hddSize;
    }

    public void setHddSize(int hddSize) {
        this.hddSize = hddSize;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
