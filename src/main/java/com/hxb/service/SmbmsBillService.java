package com.hxb.service;

import java.util.List;


import com.hxb.entity.SmbmsBill;

public interface SmbmsBillService {
	/**
	 * （1）得到订单总页数
	 * @param proCode
	 * @param proName
	 * @return
	 */
	int getBillRows(String productName,Integer providerId,Integer isPayment);
	
	/***
	 * (2)显示按条件查询的订单分页信息
	 * @param productName
	 * @param providerId
	 * @param isPayment
	 * @param currentNo
	 * @param pageSize
	 * @return
	 */
	List<SmbmsBill> getSmbmsBillList(String productName,Integer providerId,Integer isPayment,
										Integer currentNo,Integer pageSize);
	
	/**
	 * （3）添加订单信息
	 * @param user
	 * @return
	 */
	int addSmbmsBill(SmbmsBill bill);
	
	/**
	 * （4）通过订单id得到订单信息
	 * @param id
	 * @return
	 */
	SmbmsBill getSmbmsBillById(Long id);
	/**
	 * （5）JSNO刪除订单信息
	 * @param id
	 * @return
	 */
	int deleteBillById(Long id);
	/**
	 * (6)修改订单信息
	 * @param user
	 * @return
	 */
	int updateSmbmsBill(SmbmsBill bill);
}
