package com.hxb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxb.dao.SmbmsBillDao;
import com.hxb.entity.SmbmsBill;
import com.hxb.service.SmbmsBillService;
@Service
public class SmbmsBillServiceImpl implements SmbmsBillService {
	@Autowired
	private SmbmsBillDao dao;

	@Override
	//（1）得到订单总页数
	public int getBillRows(String productName, Integer providerId,
			Integer isPayment) {
		return dao.getBillRows(productName, providerId, isPayment);
	}

	@Override
	//(2)显示按条件查询的订单分页信息
	public List<SmbmsBill> getSmbmsBillList(String productName,
			Integer providerId, Integer isPayment, Integer currentNo,
			Integer pageSize) {
		return dao.getSmbmsBillList(productName, providerId, isPayment, (currentNo-1)*pageSize, pageSize);
	}

	@Override
	//(3)添加订单信息
	public int addSmbmsBill(SmbmsBill bill) {
		return dao.addSmbmsBill(bill);
	}

	@Override
	//（4）通过订单id得到订单信息
	public SmbmsBill getSmbmsBillById(Long id) {
		return dao.getSmbmsBillById(id);
	}

	@Override
	//（5）JSNO刪除订单信息
	public int deleteBillById(Long id) {
		return dao.deleteBillById(id);
	}

	@Override
	public int updateSmbmsBill(SmbmsBill bill) {
		return dao.updateSmbmsBill(bill);
	}


}
