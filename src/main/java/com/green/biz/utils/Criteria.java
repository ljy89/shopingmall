package com.green.biz.utils;


/*
 * 현재 페이지와 관련한 정보를 저장하는 클래스
 * -현재 페이지 번호
 * -페이지 당 출력 항목 수 
 * -각 페이지에서 시작하는 항목 번호
 */
public class Criteria {
	private int pageNum; // 현재 페이지 번호
	private int rowsPerPage; //페이지당 출력할 행의 갯수
	
	
	//생성자
	public Criteria(){
		this(1,10);  //기본값 : 1페이지 항목수는 10개
	}
	
	//페이지 번호, 출력행의 갯수를 지정하면 멤버에 설정하는 메소드 지정 안하면 기본 생성자에서 (1,10)으로 설정
	public Criteria(int pageNum, int rowsPerPage){
		this.pageNum = pageNum;
		this.rowsPerPage = rowsPerPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		if(pageNum<=0) {		//  0 , 음수가 들어오면 1로 설정
			this.pageNum = 1;
		}else {
			this.pageNum = pageNum;
		}
		
		
	}
	
	public int getRowsPerPage() {
	
		return rowsPerPage;
	}
	
	//페이지당 항목 수를 최대 10개로 제한
	public void setRowsPerPage(int rowsPerPage) {
		if(rowsPerPage <= 0|| rowsPerPage >10) {
			this.rowsPerPage = 10;
		}else {
			this.rowsPerPage = rowsPerPage;
		}
		
	}

	//각페이지에서 시작하는 항목 번호를 반환
	public int getPageStart() {
		return (pageNum-1) * rowsPerPage+1; //ex 1들어오면 1, 2들어오면 11, 3들어오면 21
	}
	
	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", rowsPerPage=" + rowsPerPage + "]";
	}
	
	
}
