package com.mry.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.Common;
import com.mry.repository.CommonRepositroy;

@Service
@Transactional
public class CommonService {
	@Resource
	private CommonRepositroy commonRepositroy;
	
	// 根据 key 值返回一条资源信息
	public Common getCommonByKey(long key) {
		return commonRepositroy.getCommonByKey(key);
	}
	
	// 存储一条资源信息
	public void saveCommon(Common common) {
		commonRepositroy.save(common);
	}
	
	// 删除一条资源信息
	public void removeCommon(long key) {
		commonRepositroy.deleteById(key);
	}
}
