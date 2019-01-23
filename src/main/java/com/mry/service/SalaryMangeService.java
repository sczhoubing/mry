package com.mry.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.SalaryManage;
import com.mry.repository.SalaryManageRepository;

@Service
@Transactional
public class SalaryMangeService {
	@Resource
	private SalaryManageRepository salaryManageRepository;
	
	// 添加一条薪资管理信息
	public int addSalaryMangeInfo(SalaryManage salaryManage) {
		// 如果数据库已有相同数据，则将其覆盖
		SalaryManage originSalary = salaryManageRepository.getSalaryManageByStoreId(salaryManage.getStoreId());
		if(null != originSalary) {
			salaryManage.setId(originSalary.getId());
		}
		salaryManageRepository.save(salaryManage);
		return salaryManage.getStoreId();
	}
	
	// 根据 storeId 查出一条薪资管理信息
	public SalaryManage getSalaryManageByStoreId(int storeId) {
		return salaryManageRepository.getSalaryManageByStoreId(storeId);
	}
	
	// 根据 storeId 删除一条薪资管理信息
	public int deleteSalaryManageByStoreId(int storeId) {
		return salaryManageRepository.deleteSalaryMangeByStoreId(storeId);
	}
}
