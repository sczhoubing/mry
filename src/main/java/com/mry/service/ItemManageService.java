package com.mry.service;

import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import com.google.common.collect.Lists;
import com.mry.model.Product;
import com.mry.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.enums.ItemType;
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
	@Resource
	private ProductRepository productRepository;
	
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

		// 设置该项目将使用哪些产品
		List<Integer> productIds = params.getProductIds();
		productRepository.bindItems(itemManage.getId(), productIds);
		return params.getStoreId();
	}
	
	// 根据 storeId 返回所有 ItemManage 信息
	public List<ItemManage> getItemManagesByStoreId(int storeId) {
		List<ItemManage> itemManages = itemManageRepository.getItemManagesByStoreId(storeId);
		return bindProduct(itemManages);
	}
	
	// 根据 storeId + itemName 返回一条 ItemManage 信息
	public ItemManage getItemManageByItemName(int storeId, String itemName) {
		ItemManage itemManage = itemManageRepository.getItemManageByItemName(storeId, itemName);
		return bindProduct(itemManage);
	}
	
	// 根据 storeId + symptom 返回同一症状的记录
	public List<ItemManage> getItemManageBySymptom(int storeId, String symptom) {
		List<ItemManage> itemManages = itemManageRepository.getItemManageBySymptom(storeId, symptom);
		return bindProduct(itemManages);
	}

	// 根据 type + name 返回同一类别的记录
	public Map<String, Object> getItemManageByItemTypeAndName(int storeId, String type, String name) {
		Map<String, Object> result = new HashMap<>();
		ItemTypeManage itemTypeManage = itemTypeManageRepository.getItemTypeManageByTypeName(storeId, type, name);
		if(null != itemTypeManage) {
			if(itemTypeManage.getItemType().equals(ItemType.TYPE_FACE.getCode())) {
				List<ItemManage> itemManages = itemManageRepository.getItemManageByItemFace(storeId, itemTypeManage.getId() + "");
				result.put(ItemType.TYPE_FACE.getDesc() + "-" + name, bindProduct(itemManages));
			} else if(itemTypeManage.getItemType().equals(ItemType.TYPE_BODY.getCode())) {
				List<ItemManage> itemManages = itemManageRepository.getItemManageByItemBody(storeId, itemTypeManage.getId() + "");
				result.put(ItemType.TYPE_BODY.getDesc() + "-" + name, bindProduct(itemManages));
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
					result.put(ItemType.TYPE_FACE.getDesc() + "-" + itemTypeManage.getTypeName(), bindProduct(resultItemManages));
				}
				if(itemTypeManage.getItemType().equals(ItemType.TYPE_BODY.getCode())) {
					List<ItemManage> resultItemManages = getItemManageByBodyType(itemManages, itemTypeManage.getId() + "");
					result.put(ItemType.TYPE_BODY.getDesc() + "-" + itemTypeManage.getTypeName(), bindProduct(resultItemManages));
				}
			}	
		}
		return result;
	}
	
	public List<ItemManage> getItemManageByFaceType(List<ItemManage> itemManages, String typeCode) {
		List<ItemManage> resultItemManages = new ArrayList<>();
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

	public List<ItemManage> bindProduct(List<ItemManage> itemManages) {
		if(!itemManages.isEmpty()) {
			List<Integer> itemIds = itemManages.stream().map(e -> e.getId()).collect(Collectors.toList());
			List<Product> products = productRepository.getProductByItemIds(itemIds);
			itemManages = ItemManage.bindProducts(itemManages, products);
		}
		return itemManages;
	}

	public ItemManage bindProduct(ItemManage itemManage) {
		if(null != itemManage) {
			List<Product> products = productRepository.getProductByItemIds(Lists.newArrayList(itemManage.getId()));
			itemManage.setProducts(products);
		}
		return itemManage;
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
