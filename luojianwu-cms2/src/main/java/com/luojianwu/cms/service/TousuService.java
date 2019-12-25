package com.luojianwu.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bawei.luojianwu.DateUtil;
import com.luojianwu.cms.dao.TousuDao;
import com.luojianwu.cms.pojo.Article;
import com.luojianwu.cms.pojo.Complain;
import com.luojianwu.cms.pojo.Condation;
import com.luojianwu.cms.pojo.Tousu;
import com.luojianwu.cms.pojo.User;

@Service
public class TousuService {
	@Autowired
	private TousuDao tousuDao;
	@Autowired
	private ArticleService articleService;
	/**
	 * @Title: add   
	 * @Description: 添加评论   
	 * @param: @param comment
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean add(Tousu tousu) {
		String createdStr = DateUtil.dateTimeFormat.format(new Date());
		tousu.setCreated(createdStr);
		tousuDao.insert(tousu);
		articleService.addTousu(tousu.getArticleId());
		return true;
	}
	public boolean addComplain(Complain complain) {
		// TODO Auto-generated method stub
		int i=tousuDao.addComplain(complain);
		return i>0;
	}
	public Article queryUser(Integer article_id) {
		// TODO Auto-generated method stub
		return tousuDao.queryUser(article_id);
	}
	public List<Complain> list(Condation con) {
		// TODO Auto-generated method stub
		return tousuDao.list(con);
	}
	public void updatearticle(Integer article_id) {
		// TODO Auto-generated method stub
		tousuDao.updatearticle(article_id);
	}
	public List<Complain> xiangqing(Condation con,String aid) {
		// TODO Auto-generated method stub
		return tousuDao.xiangqing(con,aid);
	}
	public void changejinzhi(String id) {
		// TODO Auto-generated method stub
		tousuDao.changejinzhi(id);
	}
}
