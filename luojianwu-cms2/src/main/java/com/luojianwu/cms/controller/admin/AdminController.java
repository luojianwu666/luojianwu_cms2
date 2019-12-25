package com.luojianwu.cms.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luojianwu.cms.pojo.Article;
import com.luojianwu.cms.pojo.Channel;
import com.luojianwu.cms.pojo.Complain;
import com.luojianwu.cms.pojo.Condation;
import com.luojianwu.cms.pojo.User;
import com.luojianwu.cms.service.ArticleService;
import com.luojianwu.cms.service.TousuService;
import com.luojianwu.cms.service.UserService;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	private TousuService tousuService;
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/tousu/list")
	
	public Object list2(Model m,Condation con,@RequestParam(value="pageNum",defaultValue="1") int pageNum) {
		
		System.out.println(pageNum+"--------------------------");
		if(pageNum>1) {
			con.setComplaintype(null);
		}
		PageHelper.startPage(pageNum, 3);
		List<Complain> list=tousuService.list(con);
		PageInfo pageInfo = new PageInfo(list);
		m.addAttribute("list", list);
		m.addAttribute("pageInfo", pageInfo);
		m.addAttribute("con", con);
		
		return "admin/complain";
	}
	@RequestMapping("/tousu/xiangqing")
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
	/**
	 * @Title: login   
	 * @Description: 后台登录   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/")
	public String login() {
		return "admin/login";
	}
	/**
	 * @Title: home   
	 * @Description: 后台首页  
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/home")
	public String home() {
		return "admin/home";
	}
	
	@RequestMapping("/article/tousu/list")
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
	
	
	/**
	 * @Title: welcome   
	 * @Description: 后台欢迎页面   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/welcome")
	public String welcome() {
		return "admin/welcome";
	}
	/**
	 * @Title: user   
	 * @Description: 用户管理   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/user")
	public String user(User user,Model model,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,@RequestParam(value="pageSize",defaultValue="3") int pageSize) {
		PageInfo<User> pageInfo = userService.getPageInfo(user,pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/user";
	}
	/**
	 * @Title: locked   
	 * @Description: 禁用用户   
	 * @param: @param userId
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping("/user/locked")
	@ResponseBody
	public boolean locked(Integer userId) {
		boolean locked = userService.locked(userId);
		return locked;
	}
	/**
	 * @Title: unLocked   
	 * @Description: 启用  
	 * @param: @param userId
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping("/user/unLocked")
	@ResponseBody
	public boolean unLocked(Integer userId) {
		boolean locked = userService.unLocked(userId);
		return locked;
	}
	
	/**
	 * @Title: article   
	 * @Description: 文章管理     
	 * @param: @param article
	 * @param: @param model
	 * @param: @param pageNum
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/article")
	public String article(Article article,Model model,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,@RequestParam(value="pageSize",defaultValue="3") int pageSize) {
		//设置文章状态
		article.setStatusIds("0,-1,1");
		PageInfo<Article> pageInfo = articleService.getPageInfo(article,pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		return "admin/article";
	}
	
	/**
	 * @Title: updateArticleStatus   
	 * @Description: 修改文章状态   
	 * @param: @param article
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping("/article/update/status")
	@ResponseBody
	public boolean updateArticleStatus(Article article) {
		return articleService.updateStatus(article.getId(), article.getStatus());
	}
	/**
	 * @Title: addHot  
	 * @Description: 文章加热
	 * @param: @param article
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping("/article/addHot")
	@ResponseBody
	public boolean addHot(Article article) {
		return articleService.addHot(article.getId());
	}
	
	

}
