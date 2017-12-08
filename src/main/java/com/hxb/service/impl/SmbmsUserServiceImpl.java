package com.hxb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxb.dao.SmbmsUserDao;
import com.hxb.entity.SmbmsUser;
import com.hxb.service.SmbmsUserService;

@Service
public class SmbmsUserServiceImpl implements SmbmsUserService {
	@Autowired
	private SmbmsUserDao dao;

	@Override
	// (1)用户登录
	public SmbmsUser login(SmbmsUser user) {
		return dao.login(user);
	}

	@Override
	// (2)得到总用户总记录数
	public int getUserRows(String userName, Integer userRole) {
		return dao.getUserRows(userName, userRole);
	}

	@Override
	// (3)分页显示用户信息
	public List<SmbmsUser> getSmbmsUserPage(String userName, Integer userRole,
			int currentNo, int pageSize) {
		return dao.getSmbmsUserPage(userName, userRole, (currentNo - 1)
				* pageSize, pageSize);
	}

	@Override
	// （4）添加用户信息
	public int addSmbmsUser(SmbmsUser user) {
		return dao.addSmbmsUser(user);
	}

	@Override
	// （5）通过用户id得到用户信息
	public SmbmsUser getSmbmsUserById(Long id) {
		return dao.getSmbmsUserById(id);
	}

	@Override
	// (6)修改用户信息
	public int updateSmbmsUser(SmbmsUser user) {
		return dao.updateSmbmsUser(user);
	}

	@Override
	// (7)根据userCode查询出User
	public SmbmsUser selectUserCodeExist(String userCode) {
		return dao.getLoginUser(userCode);
	}

	@Override
	//（8）JSNO刪除用戶信息
	public int delUserIdExist(Long id) {
		return dao.deleteUserById(id);
	}
	@Override
	// (9)JSON根据用户id修改密码 
	public int updatePwd(Integer id, String userPassword) {
		return dao.updatePwd(id, userPassword);
	}

	

}
