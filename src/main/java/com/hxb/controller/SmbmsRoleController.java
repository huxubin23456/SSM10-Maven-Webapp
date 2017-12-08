package com.hxb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxb.service.SmbmsRoleService;

@Controller
@RequestMapping("jsp")
public class SmbmsRoleController {
	@Autowired
	private SmbmsRoleService rs = null;
	
	@ResponseBody
	@RequestMapping("getrolelist.do")
	public Object getrolelist(){
		return rs.getSmbmsRoleList();
	}
	
	@RequestMapping("getrolelist2")
	public Object getrolelist2(){
		return rs.getSmbmsRoleList();
	}
}
