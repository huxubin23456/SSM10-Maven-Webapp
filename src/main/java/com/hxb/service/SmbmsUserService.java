package com.hxb.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hxb.entity.SmbmsUser;

public interface SmbmsUserService {
	/**
	 * (1)用户登录
	 * @param user
	 * @return
	 */
	SmbmsUser login(SmbmsUser user);
	/**
	 * (2)得到总用户总记录数
	 * @param userName
	 * @param userRole
	 * @return
	 */
	int getUserRows(@Param("userName") String userName,@Param("userRole") Integer userRole);
	/**
	 * (3)分页显示用户信息
	 * @param userName
	 * @param userRole
	 * @param currentNo
	 * @param pageSize
	 * @return
	 */
	List<SmbmsUser> getSmbmsUserPage(@Param("userName") String userName,@Param("userRole") Integer userRole,@Param("currentNo")int currentNo,@Param("pageSize")int pageSize);
	
	/**
	 * （4）添加用户信息
	 * @param user
	 * @return
	 */
	int addSmbmsUser(SmbmsUser user);
	/**
	 * （5）通过用户id得到用户信息
	 * @param id
	 * @return
	 */
	SmbmsUser getSmbmsUserById(Long id);
	/**
	 * (6)修改用户信息
	 * @param user
	 * @return
	 */
	int updateSmbmsUser(SmbmsUser user);
	
	/**
	 * (7)根据userCode查询出User
	 * @param userCode
	 * @return
	 */
	public SmbmsUser selectUserCodeExist(String userCode);
	
	/**
	 * （8）JSNO刪除用戶信息
	 * @param id
	 * @return
	 */
	int delUserIdExist(Long id);
	
	/**
	 * (9)JSON根据用户id修改密码 
	 * @param id
	 * @param userPassword
	 * @return
	 */
	int updatePwd(Integer id,String userPassword);
	
}
