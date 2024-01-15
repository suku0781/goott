package com.miniPrj.etc;

public class PagingInfo {
	private int totalPostCnt; // ��ü �Խ����� ���� ����
	private int viewPostCntPerPage = 10; // 1�������� ������ ���� ���� default = 10
	private int totalPageCnt; // �� ������ �� 
	private int startRowIndex; // �����ֱ� ������ ���� row index��ȣ
	private int pageNo; // ������ Ŭ���� ��������ȣ

	// ����¡ �� ó�� 
	private int pageCntPerBlock = 10; // �Ѱ��� �� �� ������ ������ ����
	private int totalPagingBlockCnt; // ��ü ����¡ �� ���� 
	private int pageBlockOfCurrentPage; // ���� �������� ���� ����¡ �� ��ȣ
	private int startNumOfCurrentPagingBlock; // ���� ����¡ �������� ���� ������ ��ȣ
	private int endNumOfCurrentPagingBlock; // ���� ����¡ �������� �������� ��ȣ

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

	// ����¡ �� ���� ���� getter, setter
	public int getTotalPagingBlockCnt() {
		return this.totalPagingBlockCnt;
	}
	public void setTotalPagingBlockCnt() {
		// ��ü ������ �� / pageCntPerBlock -> ������ �������� ������ + 1
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
//		���� ������ ��ȣ / pageCntPerBlock -> ������ �������� ������ �ø�.
		if(this.pageNo % this.pageCntPerBlock == 0) {
			this.pageBlockOfCurrentPage = this.pageNo / this.pageCntPerBlock;
		} else {
			this.pageBlockOfCurrentPage = (int)Math.ceil((double)(this.pageNo) / this.pageCntPerBlock);
		}
	}
	public int getStartNumOfCurrentPagingBlock() {
		return this.startNumOfCurrentPagingBlock;

	}
	public void setStartNumOfCurrentPagingBlock() { // ���� ����¡ �������� ���� ������ ��ȣ
		this.startNumOfCurrentPagingBlock = (this.pageBlockOfCurrentPage - 1) * this.pageCntPerBlock + 1;
	}
	public int getEndNumOfCurrentPagingBlock() {
		return this.endNumOfCurrentPagingBlock;

	}
	public void setEndNumOfCurrentPagingBlock() {
		// ���� ����¡ �������� �������� ��ȣ
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