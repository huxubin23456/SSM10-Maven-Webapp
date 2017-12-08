package com.hxb.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.hxb.entity.SmbmsRole;
import com.hxb.entity.SmbmsUser;
import com.hxb.service.SmbmsRoleService;
import com.hxb.service.SmbmsUserService;
import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping("jsp")
public class SmbmsUserController {
	@Autowired
	private SmbmsUserService us = null;
	@Autowired
	private SmbmsRoleService rs = null;

	// （1）用户登录
	@RequestMapping(value = "login.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String login(SmbmsUser user, HttpSession session,
			HttpServletRequest request) {
		SmbmsUser login = us.login(user);
		if (login != null) {
			session.setAttribute("userSession", login);
			return "jsp/frame";
		} else {
			request.setAttribute("error", "用戶名或密码错误");
			return "login";
		}
	}

	// （2）显示用户分页信息
	@RequestMapping(value = "user.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String userList(HttpSession session, String queryname,
			Integer queryUserRole,
			@RequestParam(defaultValue = "1") Integer pageIndex) {
		Integer pageSize = 5;
		List<SmbmsUser> userList = us.getSmbmsUserPage(queryname,
				queryUserRole, pageIndex, pageSize);
		List<SmbmsRole> roleList = rs.getSmbmsRoleList();
		int totalCount = us.getUserRows(queryname, queryUserRole);
		int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;
		session.setAttribute("roleList", roleList);
		session.setAttribute("userList", userList);
		session.setAttribute("currentPageNo", pageIndex);
		session.setAttribute("totalPageCount", totalPageCount);
		session.setAttribute("totalCount", totalCount);
		session.setAttribute("queryUserName", queryname);
		session.setAttribute("queryUserRole", queryUserRole);
		return "jsp/userlist";
	}

	// （3）注销
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("userSession");
		return "login";
	}

	// (4)添加用戶
	@RequestMapping("userAdd.do")
	public String adduserSave(@ModelAttribute("user") SmbmsUser user,
			@RequestParam("photos") MultipartFile[] photos,
			HttpServletRequest req) {
		// 拿到图片文件夹路径
		String path = req.getSession().getServletContext()
				.getRealPath("photos");
		// 根据报错信息提示
		List<String> errors = new ArrayList<>();
		// 上传多个文件名
		List<String> filenames = new ArrayList<>();
		for (MultipartFile photo : photos) {
			String err = getErrInfo(photo);
			if (err == null) {
				String fileName = upload(photo, path);
				filenames.add(fileName);
			} else {
				// 在前天显示错误提示
				errors.add(err);
			}
		}
		if (filenames.size() > 0) {
			user.setPhotoPath("photos/" + filenames.get(0));
			user.setCreatedBy(1L);
			user.setCreationDate(new Timestamp(System.currentTimeMillis()));
			if (us.addSmbmsUser(user) > 0) {
				return "redirect:user.do";
			}
		}
		req.setAttribute("errors", errors);
		req.setAttribute("filenames", filenames);
		return "jsp/useradd";
	}

	/**
	 * 文件上传
	 * 
	 * @param photo
	 *            图片
	 * @param path
	 *            图片路径
	 * @return
	 */
	private String upload(MultipartFile photo, String path) {
		// 拿到文件名
		String fileName = photo.getOriginalFilename();
		// 时间
		long time = System.currentTimeMillis();
		// 随机数
		int randNumber = RandomUtils.nextInt(1000000);
		// 最终的图片名
		File dest = new File(path, time + "_" + randNumber + "_" + fileName);
		try {
			photo.transferTo(dest);
			return dest.getName();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * //1).上传的文件是否为空 //2).上传文件的大小 //3).上传文件的格式
	 * 
	 * @param photo
	 * @return null is ok!!!
	 */
	private String getErrInfo(MultipartFile photo) {
		// 拿到文件的文件名
		String fileName = photo.getOriginalFilename();
		// 拿到文件的文件名后缀
		String suf = FilenameUtils.getExtension(fileName);
		// 错误提示
		String err = null;
		if (photo.isEmpty()) {
			err = String.format("%s：上传文件不能为空", fileName);
		} else if (photo.getSize() > 500000) {
			err = String.format("%s：上传文件大小不能超过500KB", fileName);
		} else if (!formats.contains(suf.toLowerCase())) {
			err = String.format("%s：上传文件格式不正确，支持的格式：%s", fileName, formats);
		}
		return err;
	}

	// 上传的文件格式
	static List<String> formats = new ArrayList<String>();
	static {
		formats.add("png");
		formats.add("jpg");
		formats.add("jpeg");
		formats.add("pneg");
	}

	// （5）点击修改调到修改用户页面
	@RequestMapping("modifyUser/{id}")
	public String modifyUser(@PathVariable Long id, HttpSession session) {
		SmbmsUser user = us.getSmbmsUserById(id);
		session.setAttribute("user", user);
		return "jsp/usermodify";
	}

	/* 使用@InitBinder装配自定义编辑器 */
	@InitBinder
	public void initDate(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	// (6)保存修改用户信息
	@RequestMapping("updateUser.do")
	public String updateUser(@ModelAttribute("user") SmbmsUser user) {
		user.setModifyBy(1L);
		user.setModifyDate(new Timestamp(System.currentTimeMillis()));
		if (us.updateSmbmsUser(user) > 0) {
			return "redirect:user.do";
		}
		return "redirect:modifyUser.do";
	}

	// (7)查看用户信息
	@RequestMapping("userView/{id}")
	public String userView(@PathVariable Long id, HttpSession session) {
		SmbmsUser user = us.getSmbmsUserById(id);
		session.setAttribute("user", user);
		return "jsp/userview";
	}

	// (8)JSON用户编码验证userCode
	@ResponseBody
	@RequestMapping("ucexist.do")
	public Object userCodeExit(@RequestParam String userCode) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(userCode)) {
			resultMap.put("userCode", "isnull");
		} else {
			SmbmsUser user = us.selectUserCodeExist(userCode);
			if (user != null) {
				resultMap.put("userCode", "exist");
			} else {
				resultMap.put("userCode", "noexist");
			}
		}
		return resultMap;
	}

	// (9)JSON删除用户验证
	@ResponseBody
	@RequestMapping("delUser.do")
	public Object userNameExit(@RequestParam Long id) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		int isDel = us.delUserIdExist(id);
		if (isDel > 0) {
			resultMap.put("delResult", "true");
		} else {
			resultMap.put("delResult", "noexist");
		}
		return resultMap;
	}

	// (10)JSON用户信息明细
	@ResponseBody
	@RequestMapping("pView.do")
	public Object pView(Long id) {
		return us.getSmbmsUserById(id);
	}

	// (11)JSON用户密码验证
	@ResponseBody
	@RequestMapping("pwdmodify.do")
	public Object pwdmodify(@RequestParam String oldpassword, 
			HttpServletRequest request) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		SmbmsUser users = (SmbmsUser) request.getSession().getAttribute(
				"userSession");
		String userPassword = users.getUserPassword();
		if (StringUtils.isNullOrEmpty(oldpassword)) {
			resultMap.put("result", "error");
		} else {
			if (userPassword.equals(oldpassword)) {
				resultMap.put("result", "true");
			} else {
				resultMap.put("result", "false");
			}
		}
		return resultMap;
	}

	@RequestMapping("updatePwd.do")
	public String updatePwd(@RequestParam("id") Integer id,
			@RequestParam("newpassword") String newpassword) {
		int isUpdatePwd = us.updatePwd(id, newpassword);
		if (isUpdatePwd > 0) {
			return "redirect:user.do";
		}
		return "pwdmodify";
	}
}
