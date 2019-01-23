package com.mry.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.model.DesTechnician;
import com.mry.repository.DesTechnicianRepository;

@Service
@Transactional
public class DesTechnicianService {
	@Resource
	private DesTechnicianRepository desTechnicianRepository;
	
	// 添加一条用户指定技师记录
	public int addDesTechnicianInfo(DesTechnician desTechnician) {
		DesTechnician originDesTechnician = desTechnicianRepository.getDesTechnicianByEmpId(desTechnician.getStoreId(), desTechnician.getUserId(), desTechnician.getEmpId());
		// 如果有重复记录就将其覆盖
		if(null != originDesTechnician) {
			desTechnician.setId(originDesTechnician.getId());
		}
		desTechnicianRepository.save(desTechnician);
		return desTechnician.getStoreId();
	}
	
	// 编辑一条用户指定技师记录
	public int editDesTechnicianInfo(DesTechnician desTechnician) { 
		desTechnicianRepository.save(desTechnician);
		return desTechnician.getId();
	}
	
	// 根据 storeId 返回所有用户指定技师记录
	public List<DesTechnician> getDesTechnicianByStoreId(int storeId) {
		return desTechnicianRepository.getDesTechnicianByStoreId(storeId);
	}
	
	// 根据 storeId + empName 返回用户指定技师记录
	public List<DesTechnician> getDesTechnicianByEmpName(int storeId, String empName) {
		return desTechnicianRepository.getDesTechnicianByEmpName(storeId, empName);
	}
	
	// 根据 storeId + userId 返回用户指定技师记录
	public List<DesTechnician> getDesTechnicianByUserId(int storeId, int userId) {
		return desTechnicianRepository.getDesTechnicianByUserId(storeId, userId);
	}
	
	// 根据 storeId + userId + empId 返回一条用户指定技师记录
	public DesTechnician getDesTechnicianByEmpId(int storeId, int userId, int empId) {
		return desTechnicianRepository.getDesTechnicianByEmpId(storeId, userId, empId);
	}
	
	// 根据 storeId 删除所有用户指定技师记录
	public int deleteDesTechnicianByStoreId(int storeId) {
		return desTechnicianRepository.deleteDesTechnicianByStoreId(storeId);
	}
	
	// 根据 storeId + userId 删除所有用户指定技师记录
	public int deleteDesTechnicianByUserId(int storeId, int userId) {
		return desTechnicianRepository.deleteDesTechnicianByUserId(storeId, userId);
	}
	
	// 根据 storeId + empId 删除所有用户指定技师记录
	public int deleteDesTechnicianByEmpId(int storeId, int empId) {
		return desTechnicianRepository.deleteDesTechnicianByEmpId(storeId, empId);
	}
	
	// 根据 storeId + id 删除一条用户指定技师记录
	public int deleteDesTechnicianById(int storeId, int id) {
		return desTechnicianRepository.deleteDesTechnicianById(storeId, id);
	}
}
 