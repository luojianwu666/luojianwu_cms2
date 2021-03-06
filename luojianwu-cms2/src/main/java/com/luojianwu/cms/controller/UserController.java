package com.luojianwu.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bawei.luojianwu.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luojianwu.cms.common.CmsConstant;
import com.luojianwu.cms.common.CmsMd5Util;
import com.luojianwu.cms.common.CookieUtil;
import com.luojianwu.cms.common.JsonResult;
import com.luojianwu.cms.pojo.Article;
import com.luojianwu.cms.pojo.Channel;
import com.luojianwu.cms.pojo.Comment;
import com.luojianwu.cms.pojo.Links;
import com.luojianwu.cms.pojo.User;
import com.luojianwu.cms.service.ArticleService;
import com.luojianwu.cms.service.CommentService;
import com.luojianwu.cms.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * @Title: login   
	 * @Description: 用户登录界面   
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public Object login() {
		
		return "/user/login";
	}
	/**
	 * @Title: login   
	 * @Description: 用户登录接口   
	 * @param: @param user
	 * @param: @param session
	 * @param: @return      
	 * @return: Object    
	 * @throws
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Object login(User user,HttpSession session,HttpServletResponse response) {
		System.out.println(user+"////////////////////");
		
		//判断用户名和密码
		if(StringUtil.isBlank(user.getUsername()) || StringUtil.isBlank(user.getPassword())) {
			return JsonResult.fail(1000, "用户名和密码不能为空");
		}
		//查询用户
		User userInfo = userService.getByUsername(user.getUsername());
		//用户为空
		if(userInfo==null) {
			return JsonResult.fail(1000, "用户名或密码错误");
		}
		if(userInfo.getLocked()==1) {
			return JsonResult.fail(1000, "用户禁用");
		}
		//判断密码
		String string2md5 = CmsMd5Util.string2MD5(user.getPassword());
		if(string2md5.equals(userInfo.getPassword())) {
			session.setAttribute(CmsConstant.UserSessionKey, userInfo);
			session.setAttribute("user", userInfo);
		
			return JsonResult.sucess();
		}
		return JsonResult.fail(500, "未知错误");
	}
	/**
	 * @Title: logout   
	 * @Description: TODO(描述这个方法的作用)   
	 * @param: @param response
	 * @param: @param session
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping("logout")
	public Object logout(HttpServletResponse response,HttpSession session) {
		session.removeAttribute(CmsConstant.UserSessionKey);
		return "redirect:/";
	}
	
	/**
	 * @Title: register   
	 * @Description: 用户注册页面   
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value="register",method=RequestMethod.GET)
	public Object register() {
		return "/user/register";
	}

	

	/**
	 * @Title: settings   
	 * @Description: 保存用户信息  
	 * @param: @param user
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public @ResponseBody Object register(User user,HttpSession session) {
		//判断用户名是否存在
		boolean result = userService.isExist(user.getUsername());
		if(result) {
			return JsonResult.fail(10001, "用户名已存在");
		}
		//用户注册
		boolean register = userService.register(user);
		if(register) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(500, "未知错误");
	}
	
	@RequestMapping("center")
	public String center(HttpServletResponse response,HttpSession session) {
		return "user/center";
	}
	
	/**
	 * @Title: settings   
	 * @Description: 设置用户信息   
	 * @param: @param response
	 * @param: @param session
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value="settings",method=RequestMethod.GET)
	public String settings(HttpServletResponse response,HttpSession session,Model model) {
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		/** 查询用户信息 **/
		User user = userService.getByUsername(userInfo.getUsername());
		model.addAttribute("user", user);
		return "user/settings";
	}
	/**
	 * @Title: settings   
	 * @Description: 保存用户信息  
	 * @param: @param user
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value="settings",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult settings(User user,HttpSession session) {
		//修改用户信息
		boolean result = userService.update(user);
		if(result) {
			//跟新session中的用户信息
			User userInfo = userService.getById(user.getId());
			session.setAttribute(CmsConstant.UserSessionKey, userInfo);
			System.out.println(session.getAttribute("USER_SESSION_ID")+"99999999999999999");
			return JsonResult.sucess();
		}
		return JsonResult.fail(100002, "修改失败");
	}
	
	@RequestMapping("comment")
	public String comment(HttpServletResponse response,HttpSession session,@RequestParam(value="pageNum",defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",defaultValue="3") int pageSize,Model m,String mohu
			
			) {
		User user = (User) session.getAttribute("user");
		PageHelper.startPage(pageNum, pageSize);
		List<Comment> list=commentService.list(user,mohu);
		PageInfo pageInfo = new PageInfo(list);
		m.addAttribute("list", list);
		m.addAttribute("pageInfo", pageInfo);
		for (Comment comment : list) {
			System.out.println(comment);
		}
		
		
		return "user/comment";
	}
	
	@RequestMapping("article")
	public String article(Article article,Model model,HttpSession session,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,@RequestParam(value="pageSize",defaultValue="3") int pageSize) {
		//设置用户Id
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		article.setUserId(userInfo.getId());
		//查询文章
		PageInfo<Article> pageInfo = articleService.getPageInfo(article,pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		return "user/article";
	}
	
	@RequestMapping("comment/user/edit")
	public Object edit(
		Integer id,Model m
			) {
		if(id!=null) {
			Comment link=commentService.edit(id);
			m.addAttribute("link", link);
		}
		
		
		
		return "user/edit";
	}
	
	@ResponseBody
	@RequestMapping("comment/user/delByIds")
	public Object deleteData(String ids) {
		boolean flag=commentService.deleteData(ids);
		
		return flag;
	}
	
	@ResponseBody
	@RequestMapping("comment/user/save")
	public Object save(Comment comment) {
		
		boolean flag=commentService.save(comment);
		
		return flag;
	}
	
	/**

	 * @Title: isLogin   

	 * @Description: 验证用户是否登录   

	 * @param: @param session
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value="isLogin",method=RequestMethod.POST)
	public @ResponseBody Object isLogin(HttpSession session) {
		Object userInfo = session.getAttribute(CmsConstant.UserSessionKey);
		if(userInfo!=null) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(CmsConstant.unLoginErrorCode, "未登录");
	}
}
