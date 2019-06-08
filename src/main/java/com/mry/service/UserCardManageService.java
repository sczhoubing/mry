package com.mry.service;

import java.util.List;
import javax.annotation.Resource;

import com.mry.enums.DateFormat;
import com.mry.enums.UserCardTypes;
import com.mry.model.*;
import com.mry.repository.*;
import com.mry.utils.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
	@Resource
	private UserCardManageRecordsRepository userCardManageRecordsRepository;

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
		if(UserCardTypes.equals(cardType, UserCardTypes.memCardType)) {
			MemCardManage memCardManage = memCardManageRepository.getMemCardManageByCardName(userCardManage.getStoreId(), userCardManage.getCardOption());
			if(null != memCardManage) {
				List<MemCardItems> memCardItems = memCardItemsRepository.getMemCardItemsByMemCardId(memCardManage.getStoreId(), memCardManage.getId());
				// 创建与用户卡关联的项目
				List<UserCardMemItem> userCardMemItems = UserCardMemItem.bindUserCardMemItemInfo(userCardManage.getStoreId(), userCardManage.getUserId(), userCardManage.getId(), memCardItems);
				// 如果已经有关于卡关联的卡项目信息，将其删除，重新添加
				userCardMemItemRepository.deleteUserCardMemItemByScuId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
				userCardMemItemRepository.saveAll(userCardMemItems);
			}
		// 卡类型是 拓客卡
		} else if(UserCardTypes.equals(cardType, UserCardTypes.extCardType)) {
			ExtCard extCard = extCardRepository.getExtCardByName(userCardManage.getStoreId(), userCardManage.getCardOption());
			if(null != extCard) {
				List<ExtCardItem> extCardItems = extCardItemRepository.getExtCardItemsByExtCardId(extCard.getStoreId(), extCard.getId());
				List<UserCardExtItem> userCardExtItems = UserCardExtItem.bindUserCardExtItemInfo(userCardManage.getStoreId(), userCardManage.getUserId(), userCardManage.getId(), extCardItems);
				// 如果已经有关于卡关联的卡项目信息，将其删除，重新添加
				userCardExtItemRepository.deleteUserCardExtItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
				userCardExtItemRepository.saveAll(userCardExtItems);
			}
		// 卡类型是 活动卡
		} else if(UserCardTypes.equals(cardType, UserCardTypes.actCardType)) {
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

	// 修改卡余额
	public double editUserCardBalance(int id, double money, String discount, String consDesc) {
		// 获取卡的余额
		UserCardManage userCardManage = userCardManageRepository.getUserCardManageById(id);
		// 没有卡的记录
		if(null == userCardManage) {
			return -1;
		}
		// 卡的状态不可用(不为1)
		if(!userCardManage.getCardStatus().equals("1")) {
			return -2;
		}
		// 卡是否过期
		if(!CommonUtils.validExpireDate(userCardManage.getCardExceDate())) {
			return -3;
		}
		String sCardBalance = userCardManage.getCardBalance();
		// 卡的余额为空
		if(StringUtils.isEmpty(sCardBalance)) {
			return -4;
		} else {
			// 卡上余额
			Double dCardBalance = Double.parseDouble(sCardBalance);
			// 卡的余额不足
			if(dCardBalance <= 0) {
				return -5;
			}
			// 计算要扣除的金额
			// 先处理克扣格式
			Double dDiscount = parseDiscount(discount);
			Double dedMoney = money;
			// 如果有折扣
			if(dDiscount > 0.0) {
				// 折扣的钱
				Double disCountMoney = CommonUtils.doubleCalculation(money, dDiscount, "*");
				// 要扣除的钱
				dedMoney = CommonUtils.doubleCalculation(dedMoney, disCountMoney, "-");
				// 需要记录到卡扣历史记录中
				UserCardManageRecords userCardManageRecords = recordCardConsumption(userCardManage, dedMoney, consDesc);
				userCardManageRecordsRepository.save(userCardManageRecords);
			}
			// 扣除完毕后，卡上的余额
			Double balance = CommonUtils.doubleCalculation(dCardBalance, dedMoney, "-");
			// 更新卡的余额
			userCardManageRepository.editUserCardBalance(id, Double.toString(balance));
			return balance;
		}
	}

	// 生成消费记录
	public UserCardManageRecords recordCardConsumption(UserCardManage userCardManage, Double dedMoney, String consDesc) {
		UserCardManageRecords userCardManageRecords = new UserCardManageRecords();
		userCardManageRecords.setStoreId(userCardManage.getStoreId());
		userCardManageRecords.setUserId(userCardManage.getUserId());
		userCardManageRecords.setCardId(userCardManage.getId());
		userCardManageRecords.setCardItemId(0);
		userCardManageRecords.setCardType(userCardManage.getCardType());
		userCardManageRecords.setCardOption(userCardManage.getCardOption());
		userCardManageRecords.setConsType(UserCardTypes.consType1.type());
		userCardManageRecords.setConsMoney(String.valueOf(dedMoney));
		userCardManageRecords.setConsDate(CommonUtils.currentDate(DateFormat.FORMAT1.getFormat()));
		userCardManageRecords.setConsDesc(consDesc);
		userCardManageRecords.setCardItem("");
		return userCardManageRecords;
	}

	// 处理折扣的格式
	public static Double parseDiscount(String disCount) {
		if(disCount.contains("%")) {
			disCount = disCount.replace("%", "");
			return CommonUtils.doubleCalculation(Double.parseDouble(disCount), 100, "/");
		}
		return CommonUtils.doubleCalculation(Double.parseDouble(disCount), 100, "/");
	}

	public static void main(String[] args) {
		Double o = parseDiscount("0");
		System.out.println(o);
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
		if(UserCardTypes.equals(cardType, UserCardTypes.memCardType)) {
			List<UserCardMemItem> userCardMemItems = userCardMemItemRepository.getUserCardMemItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
			userCardManage.getCardItem().put("userCardMemItem", userCardMemItems);
		// 卡类型是 拓客卡
		} else if(UserCardTypes.equals(cardType, UserCardTypes.extCardType)) {
			List<UserCardExtItem> userCardExtItems = userCardExtItemRepository.getUserCardExtItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
			userCardManage.getCardItem().put("userCardExtItem", userCardExtItems);
		// 卡类型是 活动卡
		} else if(UserCardTypes.equals(cardType, UserCardTypes.actCardType)) {
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
		if(UserCardTypes.equals(cardType, UserCardTypes.memCardType)) {
			userCardItemNum = userCardMemItemRepository.deleteUserCardMemItemByScuId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
		// 卡类型是 拓客卡
		} else if(UserCardTypes.equals(cardType, UserCardTypes.extCardType)) {
			userCardItemNum = userCardExtItemRepository.deleteUserCardExtItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
		// 卡类型是 活动卡
		} else if(UserCardTypes.equals(cardType, UserCardTypes.actCardType)) {
			userCardItemNum = userCardActItemRepository.deleteUserCardActItemByUserId(userCardManage.getStoreId(), userCardManage.getId(), userCardManage.getUserId());
		}
		return userCardItemNum;
	}
}
