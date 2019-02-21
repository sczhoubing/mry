package com.mry.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.enums.ItemType;
import com.mry.enums.SystemSpec;
import com.mry.model.ItemManage;
import com.mry.model.ItemTypeManage;
import com.mry.param.ItemManageParam;
import com.mry.repository.ItemManageRepository;
import com.mry.repository.ItemTypeManageRepository;

@Service
@Transactional
public class ItemManageService {
	@Resource
	private ItemTypeManageRepository itemTypeManageRepository;
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
	
	// 根据 itemType + itemName 返回同一类别的记录
	// 参数格式： 1@美白 --> 面部美白
	public Map<String, Object> getItemManageByItemTypeAndName(int storeId, String typeAndName) {
		Map<String, Object> result = new HashMap<String, Object>();
		String[] typeAndNames = typeAndName.split(SystemSpec.SEPARATOR2.getCode());
		ItemTypeManage itemTypeManage = itemTypeManageRepository.getItemTypeManageByTypeName(storeId, typeAndNames[0], typeAndNames[1]);
		if(null != itemTypeManage) {
			if(itemTypeManage.getItemType().equals(ItemType.TYPE_FACE.getCode())) {
				result.put(ItemType.TYPE_FACE.getDesc() + "-" + typeAndNames[1], itemManageRepository.getItemManageByItemFace(storeId, itemTypeManage.getId() + ""));
			} else if(itemTypeManage.getItemType().equals(ItemType.TYPE_BODY.getCode())) {
				result.put(ItemType.TYPE_BODY.getDesc() + "-" + typeAndNames[1], itemManageRepository.getItemManageByItemBody(storeId, itemTypeManage.getId() + ""));
			}
		}
		return result;
	}
	
	// 根据保养类型选项(例如：美白，补水) 分组所有项目记录并返回
	public Map<String, Object> getItemManagesByGroup(int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 取出所有 ItemManage 记录
		List<ItemManage> itemManages = itemManageRepository.getItemManagesByStoreId(storeId);
		// 取出所有 itemTypeManage 记录
		List<ItemTypeManage> itemTypeManages = itemTypeManageRepository.getItemTypeManageByStoreId(storeId);
		if(!itemManages.isEmpty() && !itemTypeManages.isEmpty()) {
			for(ItemTypeManage itemTypeManage : itemTypeManages) {
				if(itemTypeManage.getItemType().equals(ItemType.TYPE_FACE.getCode())) {
					List<ItemManage> resultItemManages = getItemManageByFaceType(itemManages, itemTypeManage.getId() + "");
					result.put(ItemType.TYPE_FACE.getDesc() + "-" + itemTypeManage.getTypeName(), resultItemManages);
				}
				if(itemTypeManage.getItemType().equals(ItemType.TYPE_BODY.getCode())) {
					List<ItemManage> resultItemManages = getItemManageByBodyType(itemManages, itemTypeManage.getId() + "");
					result.put(ItemType.TYPE_BODY.getDesc() + "-" + itemTypeManage.getTypeName(), resultItemManages);
				}
			}	
		}
		return result;
	}
	
	public List<ItemManage> getItemManageByFaceType(List<ItemManage> itemManages, String typeCode) {
		List<ItemManage> resultItemManages = new ArrayList<ItemManage>();
		for(ItemManage itemManage : itemManages) {
			if(itemManage.getFace().equals(typeCode)) {
				resultItemManages.add(itemManage);
			} 
		}
		return resultItemManages;
	}
	
	
	public List<ItemManage> getItemManageByBodyType(List<ItemManage> itemManages, String typeCode) {
		List<ItemManage> resultItemManages = new ArrayList<ItemManage>();
		for(ItemManage itemManage : itemManages) {
			if(itemManage.getBody().equals(typeCode)) {
				resultItemManages.add(itemManage);
			} 
		}
		return resultItemManages;
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
