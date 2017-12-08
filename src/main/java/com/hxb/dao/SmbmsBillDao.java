package com.hxb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hxb.entity.SmbmsBill;
import com.hxb.entity.SmbmsUser;

public interface SmbmsBillDao {
	/**
	 * （1）得到订单总页数
	 * @param proCode
	 * @param proName
	 * @return
	 */
	int getBillRows(@Param("productName") String productName,
			@Param("providerId") Integer providerId,
			@Param("isPayment") Integer isPayment);
	
	/***
	 * (2)显示按条件查询的订单分页信息
	 * @param productName
	 * @param providerId
	 * @param isPayment
	 * @param currentNo
	 * @param pageSize
	 * @return
	 */
	List<SmbmsBill> getSmbmsBillList(@Param("productName") String productName,
										@Param("providerId") Integer providerId,
										@Param("isPayment") Integer isPayment,
										@Param("currentNo") Integer currentNo,
										@Param("pageSize") Integer pageSize);
	
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
