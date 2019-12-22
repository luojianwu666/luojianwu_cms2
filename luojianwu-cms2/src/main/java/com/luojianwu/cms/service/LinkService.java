package com.luojianwu.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luojianwu.cms.dao.LinkDao;
import com.luojianwu.cms.pojo.Links;

@Service
@Transactional
public class LinkService {
	
	@Autowired
	private LinkDao linkDao;

	public List<Links> list(Links link) {
		// TODO Auto-generated method stub
		return linkDao.list(link);
	}

	public Links edit(Integer id) {
		// TODO Auto-generated method stub
		return linkDao.edit(id);
	}

	public boolean save(Links link) {
		// TODO Auto-generated method stub
		
		int i=0;
		if(link.getId()!=null) {
			i=linkDao.update(link);
		}else {
			i=linkDao.insert(link);
		}
		return i>0;
	}

	public boolean deleteData(String ids) {
		// TODO Auto-generated method stub
		int i=linkDao.deleteDate(ids);
		return i>0;
	}

	public List<Links> showlist() {
		// TODO Auto-generated method stub
		return linkDao.showlist();
	}
	
	
	
}
