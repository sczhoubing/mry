package com.mry.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.model.ItemTypeManage;
import com.mry.repository.ItemTypeManageRepository;

@Service
@Transactional
public class ItemTypeManageService {
	@Resource
	private ItemTypeManageRepository itemTypeManageRepository;
	
	// 添加一组项目类别管理信息
	public int addItemTypeManage(List<ItemTypeManage> itemTypeManages) {
		int storeId = itemTypeManages.get(0).getStoreId();
		List<ItemTypeManage> originItemTypeManages = itemTypeManageRepository.getItemTypeManageByStoreId(storeId);
		// 去除已经存在的项目类别管理信息
		List<ItemTypeManage> resultItemTypeManages = ItemTypeManage.removeDuplicateItemTypeManages(originItemTypeManages, itemTypeManages);
		if(!resultItemTypeManages.isEmpty()) {
			itemTypeManageRepository.saveAll(resultItemTypeManages);
		}
		return storeId;
	}
	
	// 编辑一条项目类别管理信息
	public int editItemTypeManage(ItemTypeManage itemTypeManage) {
		itemTypeManageRepository.save(itemTypeManage);
		return itemTypeManage.getId();
	}
	
	// 根据 storeId + itemType + typeName 获取一条项目类别管理信息
	public ItemTypeManage getItemTypeManageByTypeName(int storeId, String itemType, String typeName) {
		return itemTypeManageRepository.getItemTypeManageByTypeName(storeId, itemType, typeName);
	}
	
	// 根据 storeId + itemType 获取一组项目类别管理信息
	public List<ItemTypeManage> getItemTypeManageByItemType(int storeId, String itemType) {
		return itemTypeManageRepository.getItemTypeManageByItemType(storeId, itemType);
	}
	
	// 根据 storeId 获取该门店下所有项目类别信息
	public List<ItemTypeManage> getItemTypeManageByStoreId(int storeId) {
		return itemTypeManageRepository.getItemTypeManageByStoreId(storeId);
	}
	
	// 根据 storeId + itemType + typeName 删除一条项目类别管理信息
	public int deleteItemTypeManageByTypeName(int storeId, String itemType, String typeName) {
		return itemTypeManageRepository.deleteItemTypeManageByTypeName(storeId, itemType, typeName);
	}
	
	// 根据 storeId + itemType 删除一组项目类别管理信息
	public int deleteItemTypeManageByItemType(int storeId, String itemType) {
		return itemTypeManageRepository.deleteItemTypeManageByItemType(storeId, itemType);
	}
	
	// 根据 storeId 删除该门店下所有项目类别信息
	public int deleteItemTypeManageByStoreId(int storeId) {
		return itemTypeManageRepository.deleteItemTypeManageByStoreId(storeId);
	}
}
