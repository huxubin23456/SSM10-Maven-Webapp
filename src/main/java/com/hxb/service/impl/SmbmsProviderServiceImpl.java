package com.hxb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxb.dao.SmbmsProviderDao;
import com.hxb.entity.SmbmsProvider;
import com.hxb.service.SmbmsProviderService;

@Service
public class SmbmsProviderServiceImpl implements SmbmsProviderService {
	@Autowired
	private SmbmsProviderDao dao;

	@Override
	// （1）得到供应商总页数
	public int getProviderRows(String proCode, String proName) {
		// TODO Auto-generated method stub
		return dao.getProviderRows(proCode, proName);
	}

	@Override
	// （2）得到分页后的供应商信息
	public List<SmbmsProvider> getSmbmsProviderList(String proCode,
			String proName, Integer currentNo, Integer pageSize) {
		return dao.getSmbmsProviderList(proCode, proName, (currentNo - 1)
				* pageSize, pageSize);
	}

	@Override
	//(3)添加供应商
	public int addSmbmsProvider(SmbmsProvider provider) {
		return dao.addSmbmsProvider(provider);
	}

	@Override
	//（4）根据供应商编号得到供应商信息
	public SmbmsProvider getProviderById(Long id) {
		return dao.getProviderById(id);
	}
	
	@Override
	//（5）修改供应商信息
	public int updateSmbmsProvider(SmbmsProvider provider) {
		return dao.updateSmbmsProvider(provider);
	}


	@Override
	//(6)删除供应商
	public int delproIdExist(Long id) {
		return dao.deleteSmbmsProvider(id);
	}

	@Override
	// (7)得到供应商名称列表
	public List<SmbmsProvider> getProviderList() {
		return dao.getProviderList();
	}

}
