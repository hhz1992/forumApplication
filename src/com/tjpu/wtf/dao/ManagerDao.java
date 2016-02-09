/**
 * 
 */
package com.tjpu.wtf.dao;

import java.util.List;

import com.tjpu.wtf.model.Manager;
import com.tjpu.wtf.model.PageModel;

/**
 * @author ZM
 *
 */
public interface ManagerDao {
	//通过登录名和密码检验数据库中是否有数据
	public Manager findUser(String loginName,String password);
    //更新登录者的最后登陆时间，ip,id
	public void updateLoginCount(String lastLoginTime,String ip,int id);
	//增加新会员到数据库
	public boolean addManager(Manager manager);
	//从数据库中找出所有的用户信息
	public List<Manager> findAll();
    //通过所传递的id值删除某一用户信息
	public boolean delete(int id);
    //通过id值找到某一用户（更新时使用）
	public Manager findById(int id);
    //通过所给的manager更新数据
	public boolean update(Manager manager);
	public List<Manager> findAllByLoginName(String loginName);
	public PageModel<Manager> findAll2(int pageNo, int pageSize);
	public PageModel<Manager> findAllByLoginName2(String loginName,
			int pageNo, int pageSize);
	
	

}
