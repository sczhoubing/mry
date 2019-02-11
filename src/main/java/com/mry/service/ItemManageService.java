package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.ItemManage;
import com.mry.param.ItemManageParam;
import com.mry.repository.ItemManageRepository;

@Service
@Transactional
public class ItemManageService {
	@Resource
	private ItemManageRepository itemManageRepository;
	
	// 添加一条 ItemManage 信息
	public int addItemManage(ItemManageParam params) {
		List<ItemManage> itemManages = itemManageRepository.getItemManagesByStoreId(params.getStoreId());
		ItemManage itemManage = params.getItemManage();
		// 如果提交的数据中项目名称相同，就覆盖之前的记录
		for(ItemManage item : itemManages) {
			if(item.getItemName().equals(itemManage.getItemName())) {
				itemManage.setId(item.getId());
				break;
			}
		}
		itemManageRepository.save(itemManage);
		return params.getStoreId();
	}
	
	// 根据 storeId 返回所有 ItemManage 信息
	public List<ItemManage> getItemManagesByStoreId(int storeId) {
		return itemManageRepository.getItemManagesByStoreId(storeId);
	}
	
	// 根据 storeId + itemName 返回一条 ItemManage 信息
	public ItemManage getItemManageByItemName(int storeId, String itemName) {
		return itemManageRepository.getItemManageByItemName(storeId, itemName);
	}
	
	// 根据 storeId + symptom 返回同一症状的记录
	public List<ItemManage> getItemManageBySymptom(int storeId, String symptom) {
		return itemManageRepository.getItemManageBySymptom(storeId, symptom);
	}
	
	// 根据 storeId 删除所有 ItemManage 信息
	public int deleteItemManageByStoreId(int storeId) {
		return itemManageRepository.deleteItemManageByStoreId(storeId);
	}
	
	// 根据 storeId + itemName 删除一条 ItemManage 信息
	public int deleteItemManageByItemName(int storeId, String itemName) {
		return itemManageRepository.deleteItemManageByItemName(storeId, itemName);
	}
}
