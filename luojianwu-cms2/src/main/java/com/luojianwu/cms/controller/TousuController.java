package com.luojianwu.cms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bawei.luojianwu.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luojianwu.cms.common.CmsConstant;
import com.luojianwu.cms.common.JsonResult;
import com.luojianwu.cms.pojo.Article;
import com.luojianwu.cms.pojo.Complain;
import com.luojianwu.cms.pojo.Condation;
import com.luojianwu.cms.pojo.Tousu;
import com.luojianwu.cms.pojo.User;
import com.luojianwu.cms.service.TousuService;

@Controller
@RequestMapping("/article/tousu/")
public class TousuController {
	@Autowired
	private TousuService tousuService;
	/**
	 * @Title: add   
	 * @Description: 添加评论 
	 * @param: @param comment
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public @ResponseBody JsonResult add(Tousu tousu,HttpSession session) {
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		if(userInfo==null) {
			return JsonResult.fail(CmsConstant.unLoginErrorCode, "用户未登录");
		}
		tousu.setUserId(userInfo.getId());
		boolean result = tousuService.add(tousu);
		if(result) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(10000, "未知错误");
	}
	
	@ResponseBody
	@RequestMapping("complain")
	public Object complain(Complain complain,Integer articleId,HttpSession session) {
		System.out.println(complain+"-------------------");
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		complain.setUser_id(userInfo.getId());
		complain.setArticle_id(articleId);
		String createdStr = DateUtil.dateTimeFormat.format(new Date());
		complain.setComplainTime(createdStr);
		Article ar=tousuService.queryUser(complain.getArticle_id());
		if(ar.getUserId()==userInfo.getId()) {
			return false;
		}
		String str="http://";
		if(complain.getUrlip()!=null) {
			if(!complain.getUrlip().contains(str)) {
				return false;
			}
		}
		if(complain.getUrlip()==null ||complain.getUrlip().equals("") ) {
			boolean flag=tousuService.addComplain(complain);
			tousuService.updatearticle(complain.getArticle_id());
			return flag;
		}
		boolean flag=tousuService.addComplain(complain);
		tousuService.updatearticle(complain.getArticle_id());
		return flag;
	}
	
	@RequestMapping("list")
	public Object list(Model m,Condation con) {
		if(con.getPageNum()==null) {
			con.setPageNum(1);
		}
		System.out.println(con+"---------------------");
		
		PageHelper.startPage(con.getPageNum(), 3);
		List<Complain> list=tousuService.list(con);
		PageInfo pageInfo = new PageInfo(list);
		m.addAttribute("list", list);
		m.addAttribute("pageInfo", pageInfo);
		m.addAttribute("con", con);
		
		return "admin/complain";
	}
	@RequestMapping("xiangqing")
	public Object xiangqing(Model m,String aid,Condation con) {
		if(con.getPageNum()==null) {
			con.setPageNum(1);
		}
		PageHelper.startPage(con.getPageNum(), 3);
		List<Complain> list=tousuService.xiangqing(con,aid);
		PageInfo pageInfo = new PageInfo(list);
		m.addAttribute("list", list);
		m.addAttribute("pageInfo", pageInfo);
		m.addAttribute("con", con);
		
		return "admin/xiangqing";
	}
	
	@RequestMapping("changejinzhi")
	public Object changejinzhi(String id) {
		tousuService.changejinzhi(id);
	return "/";	
	}
	
}
