package com.luojianwu.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bawei.luojianwu.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luojianwu.cms.dao.CommentDao;
import com.luojianwu.cms.pojo.Comment;
import com.luojianwu.cms.pojo.Links;
import com.luojianwu.cms.pojo.User;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	/**
	 * @Title: add   
	 * @Description: 添加评论   
	 * @param: @param comment
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean add(Comment comment) {
		String createdStr = DateUtil.dateTimeFormat.format(new Date());
		comment.setCreated(createdStr);
		return commentDao.insert(comment)>0;
	}
	/**
	 * @Title: getPageInfo   
	 * @Description: 根据文章Id,查询评论   
	 * @param: @param articleId
	 * @param: @param pageNum
	 * @param: @return      
	 * @return: PageInfo<Comment>      
	 * @throws
	 */
	public PageInfo<Comment> getPageInfo(Integer articleId,int pageNum){
		PageHelper.startPage(pageNum, 2);
		Comment comment = new Comment();
		comment.setArticleid(articleId);
		List<Comment> commentList = commentDao.select(comment);
		return new PageInfo<>(commentList);
	}
	public  List<Comment> list(User user, String mohu) {
		// TODO Auto-generated method stub
		System.out.println(user+"//////////////////////");
		return commentDao.query(user,mohu);
	}
	public boolean deleteData(String ids) {
		// TODO Auto-generated method stub
		return commentDao.deleteData(ids);
	}
	public Comment edit(Integer id) {
		// TODO Auto-generated method stub
		return commentDao.edit(id);
	}
	public boolean save(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.save(comment);
	}
	
}
