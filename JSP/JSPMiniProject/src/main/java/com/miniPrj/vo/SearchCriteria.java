package com.miniPrj.vo;

public class SearchCriteria {
	private String searchType;
	private String searchWord;
	public SearchCriteria(String searchType, String searchValue) {
		super();
		this.searchType = searchType;
		this.searchWord = searchValue;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchValue(String searchWord) {
		this.searchWord = searchWord;
	}
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", searchWord=" + searchWord + "]";
	}
	
	
}
