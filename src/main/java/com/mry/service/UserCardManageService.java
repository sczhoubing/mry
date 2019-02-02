package com.mry.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.model.ActivityCard;
import com.mry.model.ActivityCardRechargeItem;
import com.mry.model.ExtCard;
import com.mry.model.ExtCardItem;
import com.mry.model.MemCardItems;
import com.mry.model.MemCardManage;
import com.mry.model.UserCardActItem;
import com.mry.model.UserCardExtItem;
import com.mry.model.UserCardManage;
import com.mry.model.UserCardMemItem;
import com.mry.repository.ActivityCardRechargeItemRepository;
import com.mry.repository.ActivityCardRepository;
import com.mry.repository.ExtCardItemRepository;
import com.mry.repository.ExtCardRepository;
import com.mry.repository.MemCardItemsRepository;
import com.mry.repository.MemCardManageRepository;
import com.mry.repository.UserCardActItemRepository;
import com.mry.repository.UserCardExtItemRepository;
import com.mry.repository.UserCardManageRepository;
import com.mry.repository.UserCardMemItemRepository;

@Service
@Transactional
public class UserCardManageService {
	@Resource
	private UserCardManageRepository userCardManageRepository;
	@Resource
	private MemCardManageRepository memCardManageRepository;
	@Resource
	private MemCardItemsRepository memCardItemsRepository;
	@Resource
	private ExtCardRepository extCardRepository;
	@Resource
	private ExtCardItemRepository extCardItemRepository;
	@Resource
	private ActivityCardRepository activityCardRepository;
	@Resource
	private ActivityCardRechargeItemRepository activityCardRechargeItemRepository;
	@Resource
	private UserCardMemItemRepository userCardMemItemRepository;
	@Resource
	private UserCardExtItemRepository userCardExtItemRepository;
	@Resource
	private UserCardActItemRepository userCardActItemRepository;
	// 定义全局用户卡类型
	private String memCardType1 = "1", memCardType2 = "会员卡";
	private String extCardType1 = "2", extCardType2 = "拓客卡";
	private String actCardType1 = "3", actCardType2 = "活动卡";

	// 添加一条用户卡管理记录
	public int addUserCardManageInfo(UserCardManage userCardManage) {
		UserCardManage originUserCardManage = userCardManageRepository.getUserCardManageByCardType(userCardManage.getStoreId(), userCardManage.getUserId(), userCardManage.getCardType());
		// 如果有重复记录就将其覆盖
		if(null != originUserCardManage) {
			userCardManage.setId(originUserCardManage.getId());
		}
		userCardManageRepository.save(userCardManage);
		
		// 查出关于用户卡的详情，初始化为用户关联卡项目
		String cardType = userCardManage.getCardType();
		// 卡类型是 会员卡
		if(cardType.equals(memCardType1) || cardType.equals(memCardType2)) {
			MemCardManage memCardManage = memCardManageRepository.getMemCardManageByCardName(userCardManage.getStoreId(), userCardManage.getCardOption());
			if(null != memCardManage) {
				List<MemCardItems> memCardItems = memCardItemsRepository.getMemCardItemsByMemCardId(memCardManage.getStoreId(), memCardManage.getId());
				// 创建与用户卡关联的项目
				List<UserCardMemItem> userCardMemItems = UserCardMemItem.bindUserCardMemItemInfo(userCardManage.getStoreId(), userCardManage.getUserId(), userCardManage.getId(), memCardItems);
				// 如果已经有关于卡关联的卡项目信息，将其删除，重新添加
				userCardMemItemRepository.deleteUserCardMemItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
				userCardMemItemRepository.saveAll(userCardMemItems);
			}
		// 卡类型是 拓客卡
		} else if(cardType.equals(extCardType1) || cardType.equals(extCardType2)) {
			ExtCard extCard = extCardRepository.getExtCardByName(userCardManage.getStoreId(), userCardManage.getCardOption());
			if(null != extCard) {
				List<ExtCardItem> extCardItems = extCardItemRepository.getExtCardItemsByExtCardId(extCard.getStoreId(), extCard.getId());
				List<UserCardExtItem> userCardExtItems = UserCardExtItem.bindUserCardExtItemInfo(userCardManage.getStoreId(), userCardManage.getUserId(), userCardManage.getId(), extCardItems);
				// 如果已经有关于卡关联的卡项目信息，将其删除，重新添加
				userCardExtItemRepository.deleteUserCardExtItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
				userCardExtItemRepository.saveAll(userCardExtItems);
			}
		// 卡类型是 活动卡
		} else if(cardType.equals(actCardType1) || cardType.equals(actCardType2)) {
			ActivityCard activityCard = activityCardRepository.getActivityCardByActiName(userCardManage.getStoreId(), userCardManage.getCardOption());
			if(null != activityCard) {
				List<ActivityCardRechargeItem> actCardItems = activityCardRechargeItemRepository.getActivityCardRechargeItemByActCardId(activityCard.getStoreId(), activityCard.getId());
				List<UserCardActItem> userCardActItems = UserCardActItem.bindUserCardActItemInfo(userCardManage.getStoreId(), userCardManage.getUserId(), userCardManage.getId(), actCardItems);
				// 如果已经有关于卡关联的卡项目信息，将其删除，重新添加
				userCardActItemRepository.deleteUserCardActItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
				userCardActItemRepository.saveAll(userCardActItems);
			}
		}
		return userCardManage.getStoreId();
	}
	
	// 编辑一条用户卡管理信息
	public int editUserCardManageInfo(UserCardManage userCardManage) {
		userCardManageRepository.save(userCardManage);
		return userCardManage.getId();
	}
	
	// 根据 storeId + id 查询一条用户卡管理记录
	public UserCardManage getUserCardManageById(int storeId, int id) {
		return getUserCardItem(userCardManageRepository.getUserCardManageById(storeId, id));
	}
	
	// 根据 storeId + userId + cardName 查询一条用户卡管理记录
	public UserCardManage getUserCardManageByCardType(int storeId, int userId, String cardType) {
		return getUserCardItem(userCardManageRepository.getUserCardManageByCardType(storeId, userId, cardType));
	}
	
	// 根据 storeId + userId 查询该用户下所有卡记录
	public List<UserCardManage> getUserCardManageByUserId(int storeId, int userId) {
		List<UserCardManage> userCardManages = userCardManageRepository.getUserCardManageByUserId(storeId, userId);
		for(UserCardManage userCardManage : userCardManages) {
			userCardManage = getUserCardItem(userCardManage);
		}
		return userCardManages;
	}
	
	// 根据 storeId 查询该门店下所有卡记录
	public List<UserCardManage> getUserCardManageByStoreId(int storeId) {
		List<UserCardManage> userCardManages = userCardManageRepository.getUserCardManageByStoreId(storeId);
		for(UserCardManage userCardManage : userCardManages) {
			userCardManage = getUserCardItem(userCardManage);
		}
		return userCardManages;
	}
	
	// 根据 storeId + id 删除一条用户卡记录
	public int deleteUserCardManageById(int storeId, int id) {
		UserCardManage userCardManage = getUserCardManageById(storeId, id);
		int deleteCount = 0;
		if(null != userCardManage) {
			deleteCount += deleteUserCardItem(userCardManage);
			deleteCount += userCardManageRepository.deleteUserCardManageById(storeId, id);
		}
		return deleteCount;
	}
	
	// 根据 storeId + userId 删除该用户所有卡记录
	public int deleteUserCardManageByUserId(int storeId, int userId) {
		List<UserCardManage> userCardManages = getUserCardManageByUserId(storeId, userId);
		int deleteCount = 0;
		if(!userCardManages.isEmpty()) {
			for(UserCardManage userCardManage : userCardManages) {
				deleteCount += deleteUserCardItem(userCardManage);
			}
			deleteCount += userCardManageRepository.deleteUserCardManageByUserId(storeId, userId);
		}
		return deleteCount;
	}
	
	// 根据 storeId 删除该门店下所有用户卡记录
	public int deleteUserCardManageByStoreId(int storeId) {
		List<UserCardManage> userCardManages = getUserCardManageByStoreId(storeId);
		int deleteCount = 0;
		if(!userCardManages.isEmpty()) { 
			for(UserCardManage userCardManage : userCardManages) {
				deleteCount += deleteUserCardItem(userCardManage);
				deleteCount += userCardManageRepository.deleteUserCardManageByStoreId(storeId);
			}
		}
		return deleteCount;
	}
	
	// 查询与用户卡关联的卡项目
	public UserCardManage getUserCardItem(UserCardManage userCardManage) {
		// 查出关于用户卡的详情，获取用户关联卡项目
		String cardType = userCardManage.getCardType();
		// 卡类型是 会员卡
		if(cardType.equals(memCardType1) || cardType.equals(memCardType2)) {
			List<UserCardMemItem> userCardMemItems = userCardMemItemRepository.getUserCardMemItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
			userCardManage.getCardItem().put("userCardMemItem", userCardMemItems);
		// 卡类型是 拓客卡
		} else if(cardType.equals(extCardType1) || cardType.equals(extCardType2)) {
			List<UserCardExtItem> userCardExtItems = userCardExtItemRepository.getUserCardExtItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
			userCardManage.getCardItem().put("userCardExtItem", userCardExtItems);
		// 卡类型是 活动卡
		} else if(cardType.equals(actCardType1) || cardType.equals(actCardType2)) {
			List<UserCardActItem> userCardActItems = userCardActItemRepository.getUserCardActItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
			userCardManage.getCardItem().put("userCardActItem", userCardActItems);
		}
		return userCardManage;
	}
	
	// 删除与用户卡关联的卡项目
	public int deleteUserCardItem(UserCardManage userCardManage) {
		// 查出关于用户卡的详情，获取用户关联卡项目
		String cardType = userCardManage.getCardType();
		int userCardItemNum = 0;
		// 卡类型是 会员卡
		if(cardType.equals(memCardType1) || cardType.equals(memCardType2)) {
			userCardItemNum = userCardMemItemRepository.deleteUserCardMemItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
		// 卡类型是 拓客卡
		} else if(cardType.equals(extCardType1) || cardType.equals(extCardType2)) {
			userCardItemNum = userCardExtItemRepository.deleteUserCardExtItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
		// 卡类型是 活动卡
		} else if(cardType.equals(actCardType1) || cardType.equals(actCardType2)) {
			userCardItemNum = userCardActItemRepository.deleteUserCardActItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
		}
		return userCardItemNum;
	}
}
