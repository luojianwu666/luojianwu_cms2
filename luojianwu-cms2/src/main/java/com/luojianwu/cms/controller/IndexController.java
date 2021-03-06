package com.luojianwu.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bawei.luojianwu.StringUtil;
import com.github.pagehelper.PageInfo;
import com.luojianwu.cms.common.CmsConstant;
import com.luojianwu.cms.common.CookieUtil;
import com.luojianwu.cms.common.SpringBeanUtils;
import com.luojianwu.cms.pojo.Article;
import com.luojianwu.cms.pojo.Category;
import com.luojianwu.cms.pojo.Channel;
import com.luojianwu.cms.pojo.Links;
import com.luojianwu.cms.pojo.Slide;
import com.luojianwu.cms.pojo.User;
import com.luojianwu.cms.service.ArticleService;
import com.luojianwu.cms.service.LinkService;
import com.luojianwu.cms.service.SlideService;
import com.luojianwu.cms.service.UserService;


@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private LinkService linkService;
	
	
	@RequestMapping(value="/")
	public String index(Model model,HttpServletRequest request, HttpServletResponse response, Object handler) {
		
	
		return index(1, model);
	}
	
	@RequestMapping(value="/hot/{pageNum}.html")
	public String index(@PathVariable Integer pageNum, Model model) {
		/** 频道 */
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** 轮播图 */
		List<Slide> slideList = slideService.getAll();
		model.addAttribute("slideList", slideList);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("newArticleList", newArticleList);
		
		//投诉文章
		List<Article> touList=articleService.toulist();
		
		/** 热点文章 **/
		if(pageNum==null) {
			pageNum=1;
		}
		PageInfo<Article> pageInfo =  articleService.getHotList(pageNum);
		model.addAttribute("pageInfo", pageInfo);
		List<Links> linkList=linkService.showlist();
		
		model.addAttribute("linkList", linkList);
		return "index";
	}
	
	
	@RequestMapping("/{channelId}/{cateId}/{pageNo}.html")
	public String channel(@PathVariable Integer channelId, Model model,
			@PathVariable Integer cateId,@PathVariable Integer pageNo) {
		/** 频道 */
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** 分类 */
		List<Category> cateList = articleService.getCateListByChannelId(channelId);
		model.addAttribute("cateList", cateList);
		PageInfo<Article> pageInfo =  articleService.getListByChannelIdAndCateId(channelId,cateId,pageNo);
		model.addAttribute("pageInfo", pageInfo);
		
		
		return "index";
	}
	
	@RequestMapping("article/{id}.html")
	public String articleDetail(@PathVariable Integer id,Model model) {
		/** 查询文章 **/
		Article article = articleService.getById(id);
		model.addAttribute("article", article);
		/** 查询用户 **/
		User user = userService.getById(article.getUserId());
		model.addAttribute("user", user);
		/** 查询相关文章 **/
		List<Article> articleList = articleService.getListByChannelId(article.getChannelId(),id,10);
		model.addAttribute("articleList", articleList);
		return "article/detail";
	}
	
}
