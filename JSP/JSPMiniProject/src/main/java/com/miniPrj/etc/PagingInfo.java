package com.miniPrj.etc;

public class PagingInfo {
	private int totalPostCnt; // 전체 게시판의 글의 갯수
	private int viewPostCntPerPage = 10; // 1페이지당 보여줄 글의 갯수 default = 10
	private int totalPageCnt; // 총 페이지 수 
	private int startRowIndex; // 보여주기 시작할 글의 row index번호
	private int pageNo; // 유저가 클릭한 페이지번호

	// 페이징 블럭 처리 
	private int pageCntPerBlock = 10; // 한개의 블럭 당 보여줄 펭지ㅣ 갯수
	private int totalPagingBlockCnt; // 전체 페이징 블럭 갯수 
	private int pageBlockOfCurrentPage; // 현재 페이지가 속한 페이징 블럭 번호
	private int startNumOfCurrentPagingBlock; // 현재 페이징 블럭에서의 시작 페이지 번호
	private int endNumOfCurrentPagingBlock; // 현재 페이징 블럭에서의 끝페이지 번호

	public int getTotalPostCnt() {
		return this.totalPostCnt;
	}
	public void setTotalPostCnt(int totalPostCnt) {
		this.totalPostCnt = totalPostCnt;
	}
	public int getViewPostCntPerPage() {
		return this.viewPostCntPerPage;
	}
	public void setViewPostCntPerPage(int viewPostCntPerPage) {
		this.viewPostCntPerPage = viewPostCntPerPage;
	}
	public int getTotalPageCnt() {
		return this.totalPageCnt;
	}
	public void setTotalPageCnt(int totalPostCnt, int viewPostCntPerPage) {
		if(totalPostCnt % viewPostCntPerPage == 0) {
			this.totalPageCnt = totalPostCnt / viewPostCntPerPage;
		} else {
			this.totalPageCnt = (totalPostCnt / viewPostCntPerPage) + 1;
		}
	}
	public int getPageNo() {
		return this.pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getStartRowIndex() {
		return this.startRowIndex;
	}
	public void setStartRowIndex() {
		this.startRowIndex = (this.pageNo-1)*this.viewPostCntPerPage;
	}

	// 페이징 블럭 관련 변수 getter, setter
	public int getTotalPagingBlockCnt() {
		return this.totalPagingBlockCnt;
	}
	public void setTotalPagingBlockCnt() {
		// 전체 페이지 수 / pageCntPerBlock -> 나누어 떨어지지 않으면 + 1
		if( (this.totalPageCnt % this.pageCntPerBlock) == 0 ) {
			this.totalPagingBlockCnt = (this.totalPageCnt / this.pageCntPerBlock);
		} else {
			this.totalPagingBlockCnt = (this.totalPageCnt / this.pageCntPerBlock) + 1;
		}
	}
	public int getPageBlockOfCurrentPage() {
		return this.pageBlockOfCurrentPage;
	}
	public void setPageBlockOfCurrentPage() {
//		현재 페이지 번호 / pageCntPerBlock -> 나누어 떨어지지 않으면 올림.
		if(this.pageNo % this.pageCntPerBlock == 0) {
			this.pageBlockOfCurrentPage = this.pageNo / this.pageCntPerBlock;
		} else {
			this.pageBlockOfCurrentPage = (int)Math.ceil((double)(this.pageNo) / this.pageCntPerBlock);
		}
	}
	public int getStartNumOfCurrentPagingBlock() {
		return this.startNumOfCurrentPagingBlock;

	}
	public void setStartNumOfCurrentPagingBlock() { // 현재 페이징 블럭에서의 시작 페이지 번호
		this.startNumOfCurrentPagingBlock = (this.pageBlockOfCurrentPage - 1) * this.pageCntPerBlock + 1;
	}
	public int getEndNumOfCurrentPagingBlock() {
		return this.endNumOfCurrentPagingBlock;

	}
	public void setEndNumOfCurrentPagingBlock() {
		// 현재 페이징 블럭에서의 끝페이지 번호
		this.endNumOfCurrentPagingBlock = this.pageBlockOfCurrentPage * this.pageCntPerBlock;
		if(this.endNumOfCurrentPagingBlock > this.totalPageCnt) {
			this.endNumOfCurrentPagingBlock = this.totalPageCnt;
		}
	}
	@Override
	public String toString() {
		return "PagingInfo [totalPostCnt=" + totalPostCnt + ", viewPostCntPerPage=" + viewPostCntPerPage
				+ ", totalPageCnt=" + totalPageCnt + ", startRowIndex=" + startRowIndex + ", pageNo=" + pageNo
				+ ", pageCntPerBlock=" + pageCntPerBlock + ", totalPagingBlockCnt=" + totalPagingBlockCnt
				+ ", pageBlockOfCurrentPage=" + pageBlockOfCurrentPage + ", startNumOfCurrentPagingBlock="
				+ startNumOfCurrentPagingBlock + ", endNumOfCurrentPagingBlock=" + endNumOfCurrentPagingBlock + "]";
	}




}