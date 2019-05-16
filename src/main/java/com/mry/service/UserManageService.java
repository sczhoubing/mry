package com.mry.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.DesTechnician;
import com.mry.model.UserManage;
import com.mry.repository.DesTechnicianRepository;
import com.mry.repository.UserManageRepository;

@Service
@Transactional
public class UserManageService {
	@Resource
	private DesTechnicianRepository desTechnicianRepository;
	@Resource
	private UserManageRepository userManageRepository;
	
	// 添加一条顾客的信息
	public int addUserManageInfo(UserManage userManage) {
		//UserManage originUserManage = userManageRepository.getUserManageByIdCard(userManage.getStoreId(), userManage.getIdCard());
		UserManage originUserManage = userManageRepository.getUserManageByPhoneNumber(userManage.getStoreId(), userManage.getPhoneNum());
		// 当前门店的顾客信息如果已经存在就覆盖
		if(null != originUserManage) {
			userManage.setId(originUserManage.getId());
		}
		userManageRepository.save(userManage);
		
		// 自动为当前顾客添加一条指定技师记录，技师为推荐人的技师
		// 获取推荐人的指定技师信息
		List<DesTechnician> tempDesTechnicians = desTechnicianRepository.getDesTechnicianByUserId(userManage.getStoreId(), userManage.getReferId());
		if(!tempDesTechnicians.isEmpty()) {
			DesTechnician desTechnician = new DesTechnician();
			desTechnician.setUserId(userManage.getId());
			desTechnician.setStoreId(userManage.getStoreId());
			desTechnician.setEmpId(tempDesTechnicians.get(0).getEmpId());
			desTechnician.setEmpName(tempDesTechnicians.get(0).getEmpName());
			desTechnicianRepository.save(desTechnician);
		}
		return userManage.getStoreId();
	}
	
	// 编辑顾客信息
	public int editUserManageInfo(UserManage userManage) {
		userManageRepository.save(userManage);
		return userManage.getId();
	}
	
	// 根据 storeId + userName 获取该门店下一条顾客信息
	public List<UserManage> getUserManageByUserName(int storeId, String userName) {
		return userManageRepository.getUserManageByUserName(storeId, userName);
	}
	
	// 根据 storeId + idCard 获取该门店下一条顾客信息
	/*public UserManage getUserManageByIdCard(int storeId, String idCard) {
		return userManageRepository.getUserManageByIdCard(storeId, idCard);
	}*/
	
	// 根据 storeId + phoneNum 获取该门店下一条顾客信息
	public UserManage getUserManageByPhoneNum(int storeId, String phoneNum) {
		return userManageRepository.getUserManageByPhoneNumber(storeId, phoneNum);
	}
	
	// 根据 storeId 查出该门店下所有顾客信息
	public List<UserManage> getUserManageByStoreId(int storeId) {
		return userManageRepository.getUserManageByStoreId(storeId);
	}
	
	// 根据 storeId + idCard 删除该门店下一条顾客信息
	/*public int deleteUserManageByIdCard(int storeId, String idCard) {
		return userManageRepository.deleteUserManageByIdCard(storeId, idCard);
	}*/
	
	// 根据 storeId + phoneNum 删除该门店下一条顾客信息
	public int deleteUserManageByPhoneNum(int storeId, String phoneNum) {
		return userManageRepository.deleteUserManageByPhoneNum(storeId, phoneNum);
	}
	
	// 根据 storeId + idCard 删除该门店所有顾客信息
	public int deleteUserManageByStoreId(int storeId) {
		return userManageRepository.deleteUserManageByStoreId(storeId);
	}
}
