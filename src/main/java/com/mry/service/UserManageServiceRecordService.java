package com.mry.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.model.UserManageServiceRecord;
import com.mry.repository.UserManageServiceRecordRepository;

@Service
@Transactional
public class UserManageServiceRecordService {
	@Resource
	private UserManageServiceRecordRepository userManageServiceRecordRepository;
	
	// 添加一条用户服务记录信息
	public int addUserManageServiceRecordInfo(UserManageServiceRecord userManageServiceRecord) {
		UserManageServiceRecord originUserManageServiceRecord = userManageServiceRecordRepository.getUserManageServiceRecordByServiceNum(
				userManageServiceRecord.getStoreId(), userManageServiceRecord.getUserId(), userManageServiceRecord.getServiceNum());
		// 如果有重复记录则将其覆盖
		if(null != originUserManageServiceRecord) {
			userManageServiceRecord.setId(originUserManageServiceRecord.getId());
		}
		userManageServiceRecordRepository.save(userManageServiceRecord);
		return userManageServiceRecord.getStoreId();
	}
	
	// 修改一条用户服务记录信息
	public int editUserManageServiceRecordInfo(UserManageServiceRecord userManageServiceRecord) {
		userManageServiceRecordRepository.save(userManageServiceRecord);
		return userManageServiceRecord.getStoreId();
	}
	
	// 根据 storeId + userId + serviceNum 查询一条用户服务记录
	public UserManageServiceRecord getUserManageServiceRecordByServiceNum(int storeId, int userId, String serviceNum) {
		return userManageServiceRecordRepository.getUserManageServiceRecordByServiceNum(storeId, userId, serviceNum);
	}
	
	// 根据 storeId + userId 查询该用户下所有的服务记录
	public List<UserManageServiceRecord> getUserManageServiceRecordByUserId(int storeId, int userId) {
		return userManageServiceRecordRepository.getUserManageServiceRecordByUserId(storeId, userId);
	}
	
	// 根据 storeId 查询该门店下所有的服务记录
	public List<UserManageServiceRecord> getUserManageServiceRecordByStoreId(int storeId) {
		return userManageServiceRecordRepository.getUserManageServiceRecordByStoreId(storeId);
	}
	
	// 根据 storeId + userId + id 删除一条用户服务记录
	public int deleteUserManageServiceRecordById(int storeId, int userId, int id) {
		return userManageServiceRecordRepository.deleteUserManageServiceRecordById(storeId, userId, id);
	}
	
	// 根据 storeId + userId 删除该用户下所有的服务记录
	public int deleteUserManageServiceRecordByUserId(int storeId, int userId) {
		return userManageServiceRecordRepository.deleteUserManageServiceByUserId(storeId, userId);
	}
	
	// 根据 storeId 删除该门店下所有的服务记录
	public int deleteUserManageServiceRecordByStoreId(int storeId) {
		return userManageServiceRecordRepository.deleteUserManageServiceByStoreId(storeId);
	}
}
