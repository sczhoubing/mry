package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.MemMange;
import com.mry.param.MemMangeParam;
import com.mry.repository.MemManageRepository;

@Service
@Transactional
public class MemMangeService {
	@Resource
	private MemManageRepository memManageRepository;
	
	// 保存员工管理信息
	public int addMemMangeInfo(MemMangeParam params) {
		MemMange memMange = params.getMemMange();
		List<MemMange> originMemManges = memManageRepository.getMemMangeInfoByStoreId(params.getStoreId());
		// 重复提交将覆盖原来的员工管理信息
		for(MemMange originMemMange : originMemManges) {
			if(originMemMange.getJobTitle().equals(memMange.getJobTitle())) {
				memMange.setId(originMemMange.getId());
			}
		}
		memManageRepository.save(memMange);
		return params.getStoreId();
	}
	
	// 查询出所有员工管理信息
	public List<MemMange> getMemMangeInfoByStoreId(int storeId) {
		return memManageRepository.getMemMangeInfoByStoreId(storeId);
	}
	
	// 根据 jobTitle 查出单个员工管理信息
	public MemMange getMemMangeInfoByJobTitle(int storeId, String jobTitle) {
		return memManageRepository.getMemMangeInfoByJobTitle(storeId, jobTitle);
	}
	
	// 根据 storeId 删除所有的员工管理信息
	public int deleteMemMangeByStoreId(int storeId) {
		return memManageRepository.deleteMemMangeByStoreId(storeId);
	}
	
	// 根据 storeId + jobTitle 删除一条员工管理信息
	public int deleteMemMangeByJobTitle(int storeId, String jobTitle) {
		return memManageRepository.deleteMemMangeByJobTitle(storeId, jobTitle);
	}
}
