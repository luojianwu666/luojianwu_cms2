package com.luojianwu.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luojianwu.cms.pojo.Article;
import com.luojianwu.cms.pojo.Comment;
import com.luojianwu.cms.pojo.Complain;
import com.luojianwu.cms.pojo.Condation;
import com.luojianwu.cms.pojo.Tousu;
import com.luojianwu.cms.pojo.User;

public interface TousuDao {
	/**
	 * @Title: selectById   
	 * @Description: 根据Id，查询对象   
	 * @param: @param id
	 * @param: @return      
	 * @return: Comment      
	 * @throws
	 */
	Comment selectById(@Param("id") Integer id);
	/**
	 * @Title: select   
	 * @Description: 根据Comment查询列表  
	 * @param: @param comment
	 * @param: @return      
	 * @return: List<Comment>      
	 * @throws
	 */
	List<Comment> select(@Param("tousu") Tousu tousu);
	/**
	 * @Title: count   
	 * @Description: 查询数据条数   
	 * @param: @param comment
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int count(@Param("tousu") Tousu tousu);
	/**
	 * @Title: insert   
	 * @Description: 插入一条记录   
	 * @param: @param comment
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int insert(@Param("tousu") Tousu tousu);
	/**
	 * @Title: update   
	 * @Description: 根据Id更新记录 
	 * @param: @param comment
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int update(@Param("tousu") Tousu tousu);
	/**
	 * @Title: deleteById   
	 * @Description: 根据Id删除记录   
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int deleteById(@Param("id") Integer id);
	/**
	 * @Title: deleteByIds   
	 * @Description: 根据Ids批量删除记录   
	 * @param: @param ids "1,2,3"
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int deleteByIds(@Param("ids") String ids);
	int addComplain(Complain complain);
	Article queryUser(@Param("article_id")Integer article_id);
	void updatearticle(@Param("article_id")Integer article_id);
	
	List<Complain> list(@Param("con")Condation con);
	List<Complain> xiangqing(@Param("con")Condation con,@Param("aid")String aid);
	void changejinzhi(@Param("id")String id);
}
