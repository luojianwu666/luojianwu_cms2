package com.luojianwu.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luojianwu.cms.pojo.Links;

public interface LinkDao {

	List<Links> list(@Param("link")Links link);

	Links edit(@Param("id") Integer id);

	int save(Links link);

	int update(Links link);

	int insert(Links link);

	int deleteDate(@Param("ids")String ids);

	List<Links> showlist();
	
}
