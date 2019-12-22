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
import com.luojianwu.cms.pojo.Links;
import com.luojianwu.cms.service.LinkService;

@Controller
@RequestMapping("/admin/link/")
public class LinkedController {
	@Autowired
	private LinkService linkService;
	
	@RequestMapping("list")
	public Object list(Links link,Model m,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",defaultValue="3") int pageSize
			) {
	PageHelper.startPage(pageNum, pageSize);	
		List<Links> list=linkService.list(link);
		PageInfo pageInfo = new PageInfo(list);
		m.addAttribute("list",list);
		m.addAttribute("pageInfo",pageInfo);
		m.addAttribute("link", link);
	return "link/list";	
	
	}
	
	
	@RequestMapping("edit")
	public Object edit(
		Integer id,Model m
			) {
		if(id!=null) {
			Links link=linkService.edit(id);
			m.addAttribute("link", link);
		}
		
		
		
		return "link/edit";
	}
	
	@ResponseBody
	@RequestMapping("save")
	public Object save(Links link) {
		
		boolean flag=linkService.save(link);
		
		return flag;
	}
	
	@ResponseBody
	@RequestMapping("delByIds")
	public Object deleteData(String ids) {
		boolean flag=linkService.deleteData(ids);
		
		return flag;
	}
	}
	

