package com.hxb.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hxb.entity.SmbmsProvider;
import com.hxb.service.SmbmsProviderService;

@Controller
@RequestMapping("jsp")
public class SmbmsProviderController {
	@Autowired
	private SmbmsProviderService ps;

	// （1）显示供应商分页页表
	@RequestMapping(value = "provider.do")
	public String providerList(HttpSession session, String queryProCode,
			String queryProName,
			@RequestParam(defaultValue = "1") Integer pageIndex) {
		Integer pageSize = 5;
		List<SmbmsProvider> providerList = ps.getSmbmsProviderList(
				queryProCode, queryProName, pageIndex, pageSize);
		int totalCount = ps.getProviderRows(queryProCode, queryProName);
		int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;
		session.setAttribute("providerList", providerList);
		session.setAttribute("currentPageNo", pageIndex);
		session.setAttribute("totalPageCount", totalPageCount);
		session.setAttribute("totalCount", totalCount);
		session.setAttribute("queryProCode", queryProCode);
		session.setAttribute("queryProName", queryProName);
		return "jsp/providerlist";
	}

	// （2）添加供应商信息
	@RequestMapping("addProvider.do")
	public String saveProvider(
			@ModelAttribute("provider") SmbmsProvider provider,
			@RequestParam("photos") MultipartFile[] photos,
			HttpServletRequest req) {
		// 拿到图片文件夹路径
		String path = req.getSession().getServletContext()
				.getRealPath("photos");
		// System.out.println("文件绝对路径：" + path);
		// 根据报错信息提示
		List<String> errors = new ArrayList<>();
		// 上传多个文件名
		List<String> filenames = new ArrayList<>();
		String errorInfo = null;
		String isPicPath = null;
		String workPicpath = null;

		for (int i = 0; i < photos.length; i++) {
			MultipartFile ps = photos[i];
			String err = getErrInfo(ps);
			if (!ps.isEmpty()) {
				if (i == 0) {
					errorInfo = "uploadFileError";
				} else if (i == 1) {
					errorInfo = "uploadWpError";
				}
				String fileName = upload(ps, path);
				if (i == 0) {
					isPicPath = "photos/"+ fileName;
					//isPicPath = "photos/" + fileName;
				} else if (i == 1) {
					workPicpath = "photos/" + fileName;
					//workPicpath = "photos/"+ fileName;
				}
				filenames.add(fileName);
			} else {
				// 在前天显示错误提示
				errors.add(err);
			}
		}
		if (filenames.size() > 0) {
			provider.setEnterPrise(isPicPath);
			provider.setPlanner(workPicpath);
			provider.setCreatedBy(1L);
			provider.setCreationDate(new Timestamp(System.currentTimeMillis()));
			if (ps.addSmbmsProvider(provider) > 0) {
				return "redirect:provider.do";
			}
		}
		req.setAttribute("errors", errors);
		req.setAttribute("errorInfo", errorInfo);
		return "jsp/provideradd";
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

	// (3)查看供应商信息
	@RequestMapping("providerView/{id}")
	public String providerView(@PathVariable Long id, HttpSession session) {
		SmbmsProvider provider = ps.getProviderById(id);
		session.setAttribute("provider", provider);
		return "jsp/providerview";
	}

	// （4）点击供应商id跳到供应商修改页面
	@RequestMapping("providerModify/{id}")
	public String providerModify(@PathVariable Long id, HttpSession session) {
		SmbmsProvider provider = ps.getProviderById(id);
		session.setAttribute("provider", provider);
		return "jsp/providermodify";
	}

	// （5）修改供应商信息
	@RequestMapping("updateProvider.do")
	public String updateProvider(
			@ModelAttribute("provider") SmbmsProvider provider) {
		provider.setModifyBy(1L);
		provider.setModifyDate(new Timestamp(System.currentTimeMillis()));
		if (ps.updateSmbmsProvider(provider) > 0) {
			return "redirect:provider.do";
		}
		return "redirect:providerModify.do";
	}

	// (6)JSON供应商删除验证
	@ResponseBody
	@RequestMapping("delProvider.do")
	public Object userNameExit(@RequestParam Long id) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		int isDel = ps.delproIdExist(id);
		if (isDel > 0) {
			resultMap.put("delResult", "true");
		} else {
			resultMap.put("delResult", "noexist");
		}
		return resultMap;
	}
}
