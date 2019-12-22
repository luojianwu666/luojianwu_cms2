package com.luojianwu.cms.pojo;

import java.io.Serializable;

public class Comment  implements Serializable{
    /**   
	 * @Fields serialVersionUID : TODO(这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer articleid;

    private Integer userid;

    private String content;

    private String created;
    
    private String headimg;
    private String nickname;
    private String title;
    

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Comment(Integer id, Integer articleid, Integer userid, String content, String created, String headimg,
			String nickname, String title) {
		super();
		this.id = id;
		this.articleid = articleid;
		this.userid = userid;
		this.content = content;
		this.created = created;
		this.headimg = headimg;
		this.nickname = nickname;
		this.title = title;
	}
	

	public Comment() {
		super();
	}

	public Comment(Integer id, Integer articleid, Integer userid, String content, String created, String title) {
		super();
		this.id = id;
		this.articleid = articleid;
		this.userid = userid;
		this.content = content;
		this.created = created;
		this.title = title;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", articleid=" + articleid + ", userid=" + userid + ", content=" + content
				+ ", created=" + created + ", headimg=" + headimg + ", nickname=" + nickname + ", title=" + title + "]";
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created == null ? null : created.trim();
    }
}