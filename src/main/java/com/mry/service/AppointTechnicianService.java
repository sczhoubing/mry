package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.AppointTechnician;
import com.mry.repository.AppointTechnicianRepository;

@Service
@Transactional
public class AppointTechnicianService {
	@Resource
	private AppointTechnicianRepository appointTechnicianRepository;
	
	// 添加一条用户指定技师记录
	public int addAppointTechnicianInfo(AppointTechnician appointTechnician) {
		AppointTechnician orignAppointTechnician = appointTechnicianRepository.getAppointTechnicianByUserId(
				appointTechnician.getStoreId(), appointTechnician.getUserId());
		// 一个用户指定技师的记录只能有一条，如果已经存在记录，则将其覆盖
		if(null != orignAppointTechnician) {
			appointTechnician.setId(orignAppointTechnician.getId());
		}
		appointTechnicianRepository.save(appointTechnician);
		return appointTechnician.getStoreId();
	}
	
	// 根据 storeId + userId 返回一条用户指定技师记录
	public AppointTechnician getAppointTechnicianByUserId(int storeId, int userId) {
		return appointTechnicianRepository.getAppointTechnicianByUserId(storeId, userId);
	}
	
	// 根据 storeId 返回该用户所有指定技师记录
	public List<AppointTechnician> getAppointTechnicianByStoreId(int storeId) {
		return appointTechnicianRepository.getAppointTechnicianByStoreId(storeId);
	}
}
