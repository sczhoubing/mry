package com.mry.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.enums.DateFormat;
import com.mry.model.BedInfoManage;
import com.mry.repository.BedInfoManageRepository;

@Service
@Transactional
public class BedInfoManageService {
	@Resource
	private BedInfoManageRepository bedInfoManageRepository;
	
	// 添加一条床位信息
	public int addBedInfoManageInfo(BedInfoManage bedInfoManage) {
		BedInfoManage originBedInfoManage = bedInfoManageRepository.getBedInfoManageByBedNumAndBedName(bedInfoManage.getStoreId(), 
				bedInfoManage.getBedNum(), bedInfoManage.getBedName());
		if(null != originBedInfoManage) {
			bedInfoManage.setId(originBedInfoManage.getId());
		}
		bedInfoManage.setStartTime(DateFormat.FORMAT3.getFormat());
		bedInfoManage.setEndTime(DateFormat.FORMAT3.getFormat());
		bedInfoManageRepository.save(bedInfoManage);
		return bedInfoManage.getStoreId();
	}
	
	// 编辑一条床位信息
	public int editBedInfoManageInfo(BedInfoManage bedInfoManage) {
		bedInfoManage.setStartTime(DateFormat.FORMAT3.getFormat());
		bedInfoManage.setEndTime(DateFormat.FORMAT3.getFormat());
		bedInfoManageRepository.save(bedInfoManage);
		return bedInfoManage.getId();
	}
	
	// 根据 storeId + id 返回一条床位信息
	public BedInfoManage getBedInfoManageById(int storeId, int id) {
		return bedInfoManageRepository.getBedInfoManageById(storeId, id);
	}
	
	// 根据 storeId + bedNum + bedName 返回一条床位信息
	public BedInfoManage getBedInfoManageByBedNumAndBedName(int storeId, String bedNum, String bedName) {
		return bedInfoManageRepository.getBedInfoManageByBedNumAndBedName(storeId, bedNum, bedName);
	}
	
	// 根据 storeId + startTime + endTime 返回可用的床位信息
	public List<BedInfoManage> getBedInfoManageByStartTimeAndEndTime(int storeId, String startTime, String endTime) {
		return bedInfoManageRepository.getBedInfoManageByStartTimeAndEndTime(storeId, startTime, endTime);
	}
	
	// 根据 storeId + bedNum 返回一组床位信息
	public List<BedInfoManage> getBedInfoManageByBedNum(int storeId, String bedNum) {
		return bedInfoManageRepository.getBedInfoManageByBedNum(storeId, bedNum);
	}
	
	// 根据 storeId + bedName 返回一组床位信息
	public List<BedInfoManage> getBedInfoManageByBedName(int storeId, String bedName) {
		return bedInfoManageRepository.getBedInfoManageByBedName(storeId, bedName);
	}
	
	// 根据 storeId 返回该门店下所有床位信息
	public List<BedInfoManage> getBedInfoManageByStoreId(int storeId) {
		return bedInfoManageRepository.getBedInfoManageByStoreId(storeId);
	}
	
	// 根据 id 删除一条
	public int deleteBedInfoManageById(int storeId, int id) {
		return bedInfoManageRepository.deleteBedInfoManageById(storeId, id);
	}
	
	// 根据 storeId + bedNum + bedName 删除一条床位信息
	public int deleteBedInfoManageByBedNumAndBedName(int storeId, String bedNum, String bedName) {
		return bedInfoManageRepository.deleteBedInfoManageByBedNumAndBedName(storeId, bedNum, bedName);
	}
	
	// 根据 storeId + bedNum 删除一组床位信息
	public int deleteBedInfoManageByBedNum(int storeId, String bedNum) {
		return bedInfoManageRepository.deleteBedInfoManageByBedNum(storeId, bedNum);
	}
	
	// 根据 storeId + bedName 删除一组床位信息
	public int deleteBedInfoManageByBedName(int storeId, String bedName) {
		return bedInfoManageRepository.deleteBedInfoManageByBedName(storeId, bedName);
	}
	
	// 根据 storeId 返回该门店下所有床位信息
	public int deleteBedInfoManageByStoreId(int storeId) {
		return bedInfoManageRepository.deleteBedInfoManageByStoreId(storeId);
	}
}
