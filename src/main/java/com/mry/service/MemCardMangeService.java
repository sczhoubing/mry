package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.MemCardItems;
import com.mry.model.MemCardManage;
import com.mry.model.MemCardRising;
import com.mry.param.MemCardManageParam;
import com.mry.repository.MemCardItemsRepository;
import com.mry.repository.MemCardManageRepository;
import com.mry.repository.MemCardRisingRepository;

@Service
@Transactional
public class MemCardMangeService {
	@Resource
	private MemCardManageRepository memCardManageRepository;
	@Resource
	private MemCardRisingRepository memCardRisingRepository;
	@Resource
	private MemCardItemsRepository memCardItemsRepository;
	
	// 添加一条会员卡管理记录
	public int addMemCardManageInfo(MemCardManageParam params) {
		// 如果数据库已经存在 card_name 相同的记录, 原来的记录将被覆盖
		int storeId = params.getStoreId();
		MemCardManage memCardManage = params.getMemCardManage();
		MemCardManage originMemCardManage = memCardManageRepository.getMemCardManageByCardName(storeId, memCardManage.getCardName());
		if(null != originMemCardManage) {
			memCardManage.setId(originMemCardManage.getId());
			// 将与会员卡记录关联的升级卡信息全部删除
			memCardRisingRepository.deleteMemCardRisingByMemCardId(storeId, memCardManage.getId());
			// 将与会员卡记录关联的尊享项目信息全部删除
			memCardItemsRepository.deleteMemCardItemsByMemCardId(storeId, memCardManage.getId());
		}
		// 保存会员卡管理记录基本信息
		memCardManage.setStoreId(storeId);
		memCardManageRepository.save(memCardManage);
		// 设置升级卡记录的 storeId 并保存
		List<MemCardRising> memCardRisings = params.setMemCardIdForMemCardRisings(memCardManage.getId());
		memCardRisingRepository.saveAll(memCardRisings);
		
		// 设置尊享项目记录的  storeId 并保存
		List<MemCardItems> memCardItems = params.setMemCardIdForMemCardItems(memCardManage.getId());
		memCardItemsRepository.saveAll(memCardItems);
		return storeId;
	} 
	
	// 更新一条会员卡记录
	public int updateMemCardManageInfo(MemCardManageParam params) {
		// 暂定
		return params.getStoreId();
	}
	
	// 根据 storeId 查询所有会员卡记录, 以及与会员卡关联的升级卡记录和尊享项目记录
	public List<MemCardManage> getMemCardManageInfoByStoreId(int storeId) {
		List<MemCardManage> memCardManages = memCardManageRepository.getMemCardManageByStoreId(storeId);
		if(memCardManages.size() > 0) {
			List<MemCardRising> memCardRisings = memCardRisingRepository.getMemCardRisingByStoreId(storeId);
			List<MemCardItems> memCardItems = memCardItemsRepository.getMemCardItemsByStoreId(storeId);
			List<MemCardManage> memCardManagesInfo = MemCardManage.bindMemCardRisingAndCardItems(memCardManages, memCardRisings, memCardItems);
			return memCardManagesInfo;
		}
		return null;
	}
	
	// 根据 storeId + 会员卡名 查询一条会员卡记录, 以及与会员卡关联的升级卡记录和尊享项目记录
	public MemCardManage getMemCardManageInfoByCardName(int storeId, String cardName) {
		MemCardManage memCardManage = memCardManageRepository.getMemCardManageByCardName(storeId, cardName);
		if(null != memCardManage) {
			List<MemCardRising> memCardRisings = memCardRisingRepository.getCardRisingByMemCardId(storeId, memCardManage.getId());
			List<MemCardItems> memCardItems = memCardItemsRepository.getMemCardItemsByMemCardId(storeId, memCardManage.getId());
			memCardManage.setMemCardRisings(memCardRisings);
			memCardManage.setMemCardItems(memCardItems);
			return memCardManage;
		}
		return null;
	}
	
	// 根据 storeId 删除所有会员卡记录, 以及与会员卡关联的升级卡记录和尊享项目记录
	public int deleteMemCardManageInfoByStoreId(int storeId) {
		int delMemCard = memCardManageRepository.deleteMemCardManageByStoreId(storeId);
		int delCardRising = memCardRisingRepository.deleteMemCardRisingByStoreId(storeId);
		int delCardItems = memCardItemsRepository.deleteMemCardItemsByStoreId(storeId);
		return delMemCard + delCardRising + delCardItems;
	}
	
	// 根据 storeId + 会员卡名 删除一条记录, 以及与会员卡关联的升级卡记录和尊享项目记录
	public int deleteMemCardManageInfoByCardName(int storeId, String cardName) {
		MemCardManage memCardManage = memCardManageRepository.getMemCardManageByCardName(storeId, cardName);
		if(null != memCardManage) {
			int delMemCard = memCardManageRepository.deleteMemCardManageByCardName(storeId, cardName);
			int delCardRising = memCardRisingRepository.deleteMemCardRisingByMemCardId(storeId, memCardManage.getId());
			int delCardItems = memCardItemsRepository.deleteMemCardItemsByMemCardId(storeId, memCardManage.getId());
			return delMemCard + delCardRising + delCardItems;
		}
		return 0;
	}
}
