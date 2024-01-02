package com.jsp.dto;

public class ProductDTO {
	private String prodName;
	private int prodCnt;
	private int prodPrice;
	private String prodOption;

	public ProductDTO(String prodName, int prodCnt, int prodPrice, String prodOption) {
		super();
		this.prodName = prodName;
		this.prodCnt = prodCnt;
		this.prodPrice = prodPrice;
		this.prodOption = prodOption;
	}


	public String getProdName() {
		return prodName;
	}


	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	public int getProdCnt() {
		return prodCnt;
	}


	public void setProdCnt(int prodCnt) {
		this.prodCnt = prodCnt;
	}


	public int getProdPrice() {
		return prodPrice;
	}


	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	
	public String getProdOption() {
		return prodOption;
	}
	
	public void setProdOption(String prodOption) {
		this.prodOption = prodOption;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
