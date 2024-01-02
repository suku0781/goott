package com.jsp.dto;

public class ProductDTO {
	private String prodName;
	private int prodNum;
	private int prodPrice;
	private String prodColor;
	
	
	public ProductDTO(String prodName, int prodNum, int prodPrice, String prodColor) {
		super();
		this.prodName = prodName;
		this.prodNum = prodNum;
		this.prodPrice = prodPrice;
		this.prodColor = prodColor;
	}
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNum() {
		return prodNum;
	}
	public void setProdNum(int prodNum) {
		this.prodNum = prodNum;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getProdColor() {
		return prodColor;
	}
	public void setProdColor(String prodColor) {
		this.prodColor = prodColor;
	}

	@Override
	public String toString() {
		return "ProductDTO [prodName=" + prodName + ", prodNum=" + prodNum + ", prodPrice=" + prodPrice + ", prodColor="
				+ prodColor + "]";
	}
	
	
}
