package com.luojianwu.cms.pojo;

public class Complain {
	private Integer id;
	
	private Integer article_id;
	
	
	private Integer user_id;
	
	private String complaintype;
	
	private String urlip;
	
	private String complainTime;
	
	private String title;
	private Integer complainnum;
	
	private String username;
	
	
	

	public Complain(Integer id, Integer article_id, Integer user_id, String complaintype, String urlip,
			String complainTime, String title, Integer complainnum, String username) {
		super();
		this.id = id;
		this.article_id = article_id;
		this.user_id = user_id;
		this.complaintype = complaintype;
		this.urlip = urlip;
		this.complainTime = complainTime;
		this.title = title;
		this.complainnum = complainnum;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Complain(Integer id, Integer article_id, Integer user_id, String complaintype, String urlip,
			String complainTime, String title, Integer complainnum) {
		super();
		this.id = id;
		this.article_id = article_id;
		this.user_id = user_id;
		this.complaintype = complaintype;
		this.urlip = urlip;
		this.complainTime = complainTime;
		this.title = title;
		this.complainnum = complainnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getComplainnum() {
		return complainnum;
	}

	public void setComplainnum(Integer complainnum) {
		this.complainnum = complainnum;
	}

	public Complain(Integer id, Integer article_id, Integer user_id, String complaintype, String urlip,
			String complainTime) {
		super();
		this.id = id;
		this.article_id = article_id;
		this.user_id = user_id;
		this.complaintype = complaintype;
		this.urlip = urlip;
		this.complainTime = complainTime;
	}

	public String getComplainTime() {
		return complainTime;
	}

	public void setComplainTime(String complainTime) {
		this.complainTime = complainTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArticle_id() {
		return article_id;
	}

	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getComplaintype() {
		return complaintype;
	}

	public void setComplaintype(String complaintype) {
		this.complaintype = complaintype;
	}

	public String getUrlip() {
		return urlip;
	}

	public void setUrlip(String urlip) {
		this.urlip = urlip;
	}

	@Override
	public String toString() {
		return "Complain [id=" + id + ", article_id=" + article_id + ", user_id=" + user_id + ", complaintype="
				+ complaintype + ", urlip=" + urlip + "]";
	}

	public Complain(Integer id, Integer article_id, Integer user_id, String complaintype, String urlip) {
		super();
		this.id = id;
		this.article_id = article_id;
		this.user_id = user_id;
		this.complaintype = complaintype;
		this.urlip = urlip;
	}

	public Complain() {
		super();
	}
	
	
	
}
