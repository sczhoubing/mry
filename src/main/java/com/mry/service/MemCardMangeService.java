package com.mry.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.enums.DateFormat;
import com.mry.model.MemCardItems;
import com.mry.model.MemCardManage;
import com.mry.param.MemCardManageParam;
import com.mry.repository.MemCardItemsRepository;
import com.mry.repository.MemCardManageRepository;
import com.mry.utils.CommonUtils;

@Service
@Transactional
public class MemCardMangeService {
	@Resource
	private MemCardManageRepository memCardManageRepository;
	@Resource
	private MemCardItemsRepository memCardItemsRepository;
	
	
	// 添加一组会员卡管理记录
	public int addMemCardManageInfo(List<MemCardManage> memCardManages) {
		int storeId = memCardManages.get(0).getStoreId();
		List<MemCardManage> originMemCardManages = memCardManageRepository.getMemCardManageByStoreId(storeId);
		// 去掉重复记录
		List<MemCardManage> uniqueMemCardManages = MemCardManage.removeDuplicateMemCardManage(originMemCardManages, memCardManages);
		// 批量设置建卡日期
		List<MemCardManage> resultMemCardManages = MemCardManage.setCardCreateDate(uniqueMemCardManages);
		memCardManageRepository.saveAll(resultMemCardManages);
		return storeId;
	} 
	
	// 更新一条会员卡记录
	public int editMemCardManageInfo(MemCardManageParam memCardManageParam) {
		MemCardManage memCardManage = memCardManageParam.getMemCardManage();
		// 如果前端没有传递日期，后台将保存当前日期为建卡日期
		if(CommonUtils.isBlank(memCardManage.getCreateDate())) {
			memCardManage.setCreateDate(CommonUtils.formatDate(new Date(), DateFormat.FORMAT1.getFormat()));
		}
		// 保存会员卡基本信息
		memCardManageRepository.save(memCardManage);
		// 获取传递过来的会员卡尊享项目
		List<MemCardItems> memCardItems = memCardManageParam.getMemCardItems();
		// 获取会员卡可能已存在的会员卡尊享项目
		List<MemCardItems> originMemCardItems = memCardItemsRepository.getMemCardItemsByMemCardId(memCardManage.getStoreId(), memCardManage.getId());
		// 如果已经存在有会员卡尊享项目，就先清空，重新添加
		if(!originMemCardItems.isEmpty()) {
			memCardItemsRepository.deleteAll(originMemCardItems);
		}
		// 保存会员卡尊享项目
		memCardItemsRepository.saveAll(memCardItems);
		return memCardManage.getId();
	}
	
	// 根据 storeId + 会员卡名查询一条会员卡记录, 以及与会员卡关联的尊享项目记录
	public MemCardManage getMemCardManageInfoByCardName(int storeId, String cardName) {
		// 获取会员卡基本信息
		MemCardManage memCardManage = memCardManageRepository.getMemCardManageByCardName(storeId, cardName);
		if(null != memCardManage) {
			List<MemCardItems> memCardItems = memCardItemsRepository.getMemCardItemsByMemCardId(storeId, memCardManage.getId());
			memCardManage.setMemCardItems(memCardItems);
			return memCardManage;
		}
		return null;
	}
	
	// 根据 storeId 查询所有会员卡记录, 以及与会员卡关联的尊享项目记录
	public List<MemCardManage> getMemCardManageInfoByStoreId(int storeId) {
		// 获取会员卡基本信息
		List<MemCardManage> memCardManages = memCardManageRepository.getMemCardManageByStoreId(storeId);
		if(!memCardManages.isEmpty()) {
			// 取出查询出的会员卡的 id
			List<Integer> memCardIds = memCardManages.stream().map(MemCardManage::getId).collect(Collectors.toList());
			// 获取会员卡尊享项目
			List<MemCardItems> memCardItems = memCardItemsRepository.getMemCardItemsByMemCardIds(storeId, memCardIds);
			// 绑定会员卡和对应的尊享项目
			return MemCardManage.bindMemCardItems(memCardManages, memCardItems);
		}
		return null;
	}
	
	// 根据 storeId + 会员卡状态查询一组会员卡记录，以及与会员卡关联的尊享项目记录
	public List<MemCardManage> getMemCardManageInfoByCardStatus(int storeId, String cardStatus) {
		// 获取会员卡基本信息
		List<MemCardManage> memCardManages = memCardManageRepository.getMemCardManageInfoByCardStatus(storeId, cardStatus);
		if(!memCardManages.isEmpty()) {
			// 取出查询出的会员卡的 id
			List<Integer> memCardIds = memCardManages.stream().map(MemCardManage::getId).collect(Collectors.toList());
			// 获取会员卡尊享项目
			List<MemCardItems> memCardItems = memCardItemsRepository.getMemCardItemsByMemCardIds(storeId, memCardIds);
			// 绑定会员卡和对应的尊享项目
			return MemCardManage.bindMemCardItems(memCardManages, memCardItems);
		}
		return null;
	}
	
	// 根据 storeId + id 删除一条会员卡记录以及与会员卡关联的尊享项目记录
	public int deleteMemCardManageInfoById(int storeId, int id) {
		// 删除基本的会员卡信息
		int deleteMemCardNum = memCardManageRepository.deleteMemCardManageInfoById(storeId, id);
		// 删除会员卡关联的尊享项目记录
		int deleteMemCardItemsNum = memCardItemsRepository.deleteMemCardItemsByMemCardId(storeId, id);
		return deleteMemCardNum + deleteMemCardItemsNum;
	}
	
	// 根据 storeId 删除所有会员卡记录, 以及与会员卡关联的尊享项目记录
	public int deleteMemCardManageInfoByStoreId(int storeId) {
		// 删除基本的会员卡信息
		int deleteMemCardNum = memCardManageRepository.deleteMemCardManageByStoreId(storeId);
		// 删除会员卡关联的尊享项目记录
		int deleteMemCardItemsNum = memCardItemsRepository.deleteMemCardItemsByStoreId(storeId);
		return deleteMemCardNum + deleteMemCardItemsNum;
	}
	
	// 根据 storeId + 会员卡名 删除一条记录, 以及与会员卡关联的尊享项目记录
	public int deleteMemCardManageInfoByCardName(int storeId, String cardName) {
		MemCardManage memCardManage = memCardManageRepository.getMemCardManageByCardName(storeId, cardName);
		if(null != memCardManage) {
			// 删除基本的会员卡信息
			int deleteMemCardNum = memCardManageRepository.deleteMemCardManageByCardName(storeId, cardName);
			// 删除会员卡关联的尊享项目记录
			int deleteMemCardItemsNum = memCardItemsRepository.deleteMemCardItemsByMemCardId(storeId, memCardManage.getId());
			return deleteMemCardNum + deleteMemCardItemsNum; 
		}
		return 0;
	}
}
