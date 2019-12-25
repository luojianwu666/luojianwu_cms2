package com.luojianwu.cms.pojo;

public class Condation {
	private String complaintype;
	
	private Integer complainnum;
	
	private Integer pageNum;
	

	private Integer complainnum2;



	public Integer getComplainnum2() {
		return complainnum2;
	}

	public void setComplainnum2(Integer complainnum2) {
		this.complainnum2 = complainnum2;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Condation(String complaintype, Integer complainnum, Integer pageNum) {
		super();
		this.complaintype = complaintype;
		this.complainnum = complainnum;
		this.pageNum = pageNum;
	}

	public String getComplaintype() {
		return complaintype;
	}

	public void setComplaintype(String complaintype) {
		this.complaintype = complaintype;
	}

	public Integer getComplainnum() {
		return complainnum;
	}

	public void setComplainnum(Integer complainnum) {
		this.complainnum = complainnum;
	}

	@Override
	public String toString() {
		return "Condation [complaintype=" + complaintype + ", complainnum=" + complainnum + "]";
	}

	public Condation(String complaintype, Integer complainnum) {
		super();
		this.complaintype = complaintype;
		this.complainnum = complainnum;
	}

	public Condation() {
		super();
	}
	
	
	
	
	
}
