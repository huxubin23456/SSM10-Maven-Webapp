package com.hxb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hxb.entity.SmbmsProvider;

public interface SmbmsProviderDao {
	
	/**
	 * （1）得到供应商总页数
	 * @param proCode
	 * @param proName
	 * @return
	 */
	int getProviderRows(@Param("proCode") String proCode,@Param("proName") String proName);
	
	/**
	 * （2）得到分页后的供应商信息
	 * @param proCode
	 * @param proName
	 * @param currentNo
	 * @param pageSize
	 * @return
	 */
	List<SmbmsProvider> getSmbmsProviderList(@Param("proCode") String proCode,@Param("proName") String proName,
			@Param("currentNo") Integer currentNo,@Param("pageSize") Integer pageSize);
	
	/**
	 * (3)添加供应商
	 * @param provider
	 * @return
	 */
	int addSmbmsProvider(SmbmsProvider provider);
	
	/**
	 * （4）根据供应商编号得到供应商信息
	 * @param id
	 * @return
	 */
	SmbmsProvider getProviderById(Long id);
	
	/***
	 * (5)更新供应商信息
	 * @param provider
	 * @return
	 */
	int updateSmbmsProvider(SmbmsProvider provider);
	/**
	 * （6）删除供应商
	 * @param id
	 * @return
	 */
	int deleteSmbmsProvider(Long id);
	
	
	/**
	 * (7)得到供应商名称列表
	 * @return
	 */
	List<SmbmsProvider> getProviderList();
	
}
