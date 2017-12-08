package com.hxb.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.ResolverUtil.IsA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxb.entity.SmbmsBill;
import com.hxb.entity.SmbmsProvider;
import com.hxb.entity.SmbmsUser;
import com.hxb.service.SmbmsBillService;
import com.hxb.service.SmbmsProviderService;

@Controller
@RequestMapping("jsp")
public class SmbmsBillController {
	@Autowired
	private SmbmsBillService bs;
	@Autowired
	private SmbmsProviderService ps;
	
	// （1）显示供应商分页页表
		@RequestMapping(value = "bill.do")
		public String billList(HttpSession session, String queryProductName,
				Integer queryProviderId,Integer queryIsPayment,
				@RequestParam(defaultValue = "1") Integer pageIndex) {
			Integer pageSize = 5;
			List<SmbmsBill> billList =bs.getSmbmsBillList(queryProductName, queryProviderId, queryIsPayment, pageIndex, pageSize);
			int totalCount = bs.getBillRows(queryProductName, queryProviderId, queryIsPayment);
			int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize
					: totalCount / pageSize + 1;
			List<SmbmsProvider> providerList=ps.getProviderList();
			session.setAttribute("billList", billList);
			session.setAttribute("providerList", providerList);
			session.setAttribute("currentPageNo", pageIndex);
			session.setAttribute("totalPageCount", totalPageCount);
			session.setAttribute("totalCount", totalCount);
			session.setAttribute("queryProductName", queryProductName);
			session.setAttribute("queryProviderId", queryProviderId);
			session.setAttribute("queryIsPayment", queryIsPayment);
			return "jsp/billlist";
		}
		
		//(2)JSON添加订单页面的显示供应商信息
		@ResponseBody
		@RequestMapping("getproviderlist.do")
		public Object getproviderlist(){
			return ps.getProviderList();
		}
		
		//(4)添加订单信息
		@RequestMapping("addBill.do")
		public Object addBill(@ModelAttribute("bill") SmbmsBill bill){
			bill.setCreatedBy(1L);
			bill.setCreationDate(new Timestamp(System.currentTimeMillis()));
			int isAdd=bs.addSmbmsBill(bill);
			if(isAdd>0){
				return "redirect:bill.do";
			}
			return "billadd";
		}
		
		// (5)JSON订单信息明细
		@ResponseBody
		@RequestMapping("bView.do")
		public Object bView(Long id){
			return bs.getSmbmsBillById(id);
		}
		
		// (6)JSON删除订单信息验证
		@ResponseBody
		@RequestMapping("delbill.do")
		public Object delbill(@RequestParam Long id) {
			HashMap<String, String> resultMap = new HashMap<String, String>();
			int isDel = bs.deleteBillById(id);
			if (isDel > 0) {
				resultMap.put("delResult", "true");
			} else {
				resultMap.put("delResult", "noexist");
			}
			return resultMap;
		}
		
		// （7）点击修改调到修改订单页面
		@RequestMapping("modifyBill/{id}")
		public String modifyBill(@PathVariable Long id, HttpSession session) {
			SmbmsBill bill=bs.getSmbmsBillById(id);
			session.setAttribute("bill", bill);
			return "jsp/billmodify";
		}
		
		// (8)保存修改订单信息
		@RequestMapping("updateBill.do")
		public String updateBill(@ModelAttribute("bill") SmbmsBill bill) {
			bill.setModifyBy(1L);
			bill.setModifyDate(new Timestamp(System.currentTimeMillis()));
			if (bs.updateSmbmsBill(bill)>0) {
				return "redirect:bill.do";
			}
			return "redirect:modifyBill/{id}";
		}
}
