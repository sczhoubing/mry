package com.mry.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.enums.DateFormat;
import com.mry.model.UserAppointment;
import com.mry.model.UserManage;
import com.mry.repository.BedInfoManageRepository;
import com.mry.repository.EmployeeRepository;
import com.mry.repository.UserAppointmentRepository;
import com.mry.repository.UserManageRepository;

@Service
@Transactional
public class UserAppointmentService {
	@Resource
	private UserAppointmentRepository userAppointmentRepository;
	@Resource
	private BedInfoManageRepository bedInfoManageRepository;
	@Resource
	private UserManageRepository userManageRepository;
	@Resource
	private EmployeeRepository employeeRepository;
	
	// 添加一条 用户预约信息
	public int addUserAppointmentInfo(UserAppointment userAppointment) {
		List<UserAppointment> userAppointments = userAppointmentRepository.getUserAppointmentByUserId(userAppointment.getStoreId(), userAppointment.getUserId());
		UserAppointment duplicateUserAppointment = UserAppointment.validDuplicateUserAppointment(userAppointment, userAppointments);
		// 如果有重复记录，就将其覆盖
		if(null != duplicateUserAppointment) {
			userAppointment.setId(duplicateUserAppointment.getId());
		}
		userAppointmentRepository.save(userAppointment);
		// 标记床位被占用，占用的时间段
		bedInfoManageRepository.markOccupancyTime(userAppointment.getStartTime(), userAppointment.getEndTime(), userAppointment.getBedId());
		// 标记技师被占用，占用时间段
		employeeRepository.markOccupancyTime(userAppointment.getStartTime(), userAppointment.getEndTime(), userAppointment.getTechnicianId());
		return userAppointment.getStoreId();
	}
	
	// 编辑一条 用户预约信息
	public int editUserAppointmentInfo(UserAppointment userAppointment) {
		// 如果状态表示已完成，应当解除床位和技师被占用的时间段；如果服务被取消也应解除床位和技师被占用的时间段
		if(userAppointment.getStatus().equals("1") || userAppointment.getStatus().equals("2")) {
			// 解除床位和技师被占用时间
			markBedAndTechnicianOccupancyTime(userAppointment.getBedId(), userAppointment.getTechnicianId());
		}
		userAppointmentRepository.save(userAppointment);
		return userAppointment.getId();
	}
	
	// mark 该服务是否已经完成
	public int markUserAppointmentInfo(int id, int bedId, int technicianId, String status) {
		userAppointmentRepository.markUserAppointmentInfo(id, status);
		// 1 表示完成服务，2 表示取消服务
		if(status.equals("1") || status.equals("2")) {
			// 解除床位和技师被占用时间
			markBedAndTechnicianOccupancyTime(bedId, technicianId);
		}
		return id;
	}
	
	// 根据 storeId + userId + startTime 查询用户预约信息
	public List<UserAppointment> getUserAppointmentByStartTime(int storeId, int userId, String startTime) {
		return userAppointmentRepository.getUserAppointmentByStartTime(storeId, userId, startTime);
	}
	
	// 根据 storeId 返回该门店下所有用户预约信息
	public List<UserAppointment> getUserAppointmentByStoreId(int storeId) {
		return userAppointmentRepository.getgetUserAppointmentByStoreId(storeId);
	}
	
	// 根据 storeId + userId 返回该用户下所有预约信息
	public List<UserAppointment> getUserAppointmentByUserId(int storeId, int userId) {
		return userAppointmentRepository.getUserAppointmentByUserId(storeId, userId);
	}
	
	// 根据 storeId + 用户手机号码 返回该用户下所有预约信息
	public List<UserAppointment> getUserAppointmentByUserPhoneNumber(int storeId, String phoneNum) {
		UserManage userManage = userManageRepository.getUserManageByPhoneNumber(storeId, phoneNum);
		if(null != userManage) {
			return userAppointmentRepository.getUserAppointmentByUserId(storeId, userManage.getId());
		}
		return null;
	}
	
	// 根据 storeId + 用户身份证号码 返回该用户下所有预约信息
	public List<UserAppointment> getUserAppointmentByUserIdCard(int storeId, String idCard) {
		UserManage userManage = userManageRepository.getUserManageByIdCard(storeId, idCard);
		if(null != userManage) {
			return userAppointmentRepository.getUserAppointmentByUserId(storeId, userManage.getId());
		}
		return null;
	}
	
	// 根据 用户预约信息 记录的 id 删除一条记录
	public int deleteUserAppointmentById(int storeId, int id) {
		UserAppointment userAppointment = userAppointmentRepository.getUserAppointmentById(storeId, id);
		if(null != userAppointment) {
			// 解除床位和技师被占用时间
			markBedAndTechnicianOccupancyTime(userAppointment.getBedId(), userAppointment.getTechnicianId());
		}
		return userAppointmentRepository.deleteUserAppointmentById(storeId, id);
	}
	
	// 根据 storeId + userId 删除该用户下所有预约信息
	public int deleteUserAppointmentByUserId(int storeId, int userId) {
		List<UserAppointment> userAppointments = userAppointmentRepository.getUserAppointmentByUserId(storeId, userId);
		if(userAppointments.isEmpty()) {
			for(UserAppointment userAppointment : userAppointments) {
				// 解除床位和技师被占用时间
				markBedAndTechnicianOccupancyTime(userAppointment.getBedId(), userAppointment.getTechnicianId());
			}
		}
		return userAppointmentRepository.deleteUserAppointmentByUserId(storeId, userId);
	}
	
	// 根据 storeId 删除该门店下所有用户预约信息
	public int deleteUserAppointmentByStoreId(int storeId) {
		List<UserAppointment> userAppointments = userAppointmentRepository.getgetUserAppointmentByStoreId(storeId);
		if(userAppointments.isEmpty()) {
			for(UserAppointment userAppointment : userAppointments) {
				// 解除床位和技师被占用时间
				markBedAndTechnicianOccupancyTime(userAppointment.getBedId(), userAppointment.getTechnicianId());
			}
		}
		return userAppointmentRepository.deleteUserAppointmentByStoreId(storeId);
	}
	
	// 解除技师和床位被占用时间段
	public void markBedAndTechnicianOccupancyTime(int bedId, int technicianId) {
		// 解除床位被占用时间段
		bedInfoManageRepository.markOccupancyTime(DateFormat.FORMAT3.getFormat(), DateFormat.FORMAT3.getFormat(), bedId);
		// 解除技师被占用时间段
		employeeRepository.markOccupancyTime(DateFormat.FORMAT3.getFormat(), DateFormat.FORMAT3.getFormat(), technicianId);
	}
}
