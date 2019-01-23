package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.ActivityCard;
import com.mry.model.ActivityCardRechargeGift;
import com.mry.model.ActivityCardRechargeItem;
import com.mry.model.ActivityCardRechargeLottery;
import com.mry.param.ActivityCardParam;
import com.mry.repository.ActivityCardRechargeGiftRepository;
import com.mry.repository.ActivityCardRechargeItemRepository;
import com.mry.repository.ActivityCardRechargeLotteryRepository;
import com.mry.repository.ActivityCardRepository;

@Service
@Transactional
public class ActivityCardService {
	@Resource
	private ActivityCardRepository activityCardRepository;
	@Resource
	private ActivityCardRechargeItemRepository activityCardRechargeItemRepository;
	@Resource
	private ActivityCardRechargeGiftRepository activityCardRechargeGiftRepository;
	@Resource
	private ActivityCardRechargeLotteryRepository activityCardRechargeLotteryRepository;
	
	// 添加一条活动卡信息
	public int addActivityCardInfo(ActivityCardParam params) {
		ActivityCard activityCard = params.getActivityCard();
		ActivityCard originActivityCard = activityCardRepository.getActivityCardByActiName(params.getStoreId(), activityCard.getActiName());
		// 如果有重复值就将其覆盖
		if(null != originActivityCard) {
			activityCard.setId(originActivityCard.getId());
			activityCard.setStoreId(params.getStoreId());
			// 先删除掉原来活动卡关联的:充值赠送项目, 充值赠送产品, 充值摇奖活动 等记录
			activityCardRechargeItemRepository.deleteActivityCardRechargeItemByActCardId(params.getStoreId(), originActivityCard.getId());
			activityCardRechargeGiftRepository.deleteActivityCardRechargeGiftByActCardId(params.getStoreId(), originActivityCard.getId());
			activityCardRechargeLotteryRepository.deleteActivityCardRechargeLotteryByActCardId(params.getStoreId(), originActivityCard.getId());
		}
		// 保存活动卡信息
		activityCardRepository.save(activityCard);
		
		// 保存充值赠送项目
		if(!params.getActivityCardRechargeItems().isEmpty()) {
			List<ActivityCardRechargeItem> activityCardRechargeItems = ActivityCardRechargeItem.setActivityCardRechargeItemStoreIdAndActCardId(params.getActivityCardRechargeItems(), 
					params.getStoreId(), activityCard.getId());
			activityCardRechargeItemRepository.saveAll(activityCardRechargeItems);
		}
		
		// 保存充值赠送产品
		if(!params.getActivityCardRechargeGifts().isEmpty()) {
			List<ActivityCardRechargeGift> activityCardRechargeGifts = ActivityCardRechargeGift.setActivityCardRechargeGiftStoreIdAndActCardId(params.getActivityCardRechargeGifts(), 
					params.getStoreId(), activityCard.getId());
			activityCardRechargeGiftRepository.saveAll(activityCardRechargeGifts);
		}
		
		// 保存充值摇奖活动
		if(!params.getActivityCardRechargeLotteries().isEmpty()) {
			List<ActivityCardRechargeLottery> activityCardRechargeLotteries = ActivityCardRechargeLottery.setActivityCardRechargeLotteryStoreIdAndActCardId(params.getActivityCardRechargeLotteries(), 
					params.getStoreId(), activityCard.getId());
			activityCardRechargeLotteryRepository.saveAll(activityCardRechargeLotteries);
		}
		
		return params.getStoreId();
	}
	
	// 根据 storeId + actiName 查询一条活动卡信息
	public ActivityCard getActivityCardInfoByActiName(int storeId, String actiName) {
		// 获取活动卡基本信息
		ActivityCard activityCard = activityCardRepository.getActivityCardByActiName(storeId, actiName);
		if(null != activityCard) {
			// 获取充值赠送项目信息
			List<ActivityCardRechargeItem> activityCardRechargeItems = activityCardRechargeItemRepository.getActivityCardRechargeItemByActCardId(storeId, activityCard.getId());
			activityCard.setActivityCardRechargeItems(activityCardRechargeItems);
			// 获取充值赠送产品信息
			List<ActivityCardRechargeGift> activityCardRechargeGifts = activityCardRechargeGiftRepository.getActivityCardRechargeGiftByActCardId(storeId, activityCard.getId());
			activityCard.setActivityCardRechargeGifts(activityCardRechargeGifts);
			// 获取充值摇奖活动信息
			List<ActivityCardRechargeLottery> activityCardRechargeLotteries = activityCardRechargeLotteryRepository.getActivityCardRechargeLotteryByActCardId(storeId, activityCard.getId());
			activityCard.setActivityCardRechargeLotteries(activityCardRechargeLotteries);
		}
		return activityCard;
	}
	
	// 根据 storeId 查询出所有活动卡信息
	public List<ActivityCard> getActivityCardInfoByStoreId(int storeId) {
		// 获取活动卡基本信息
		List<ActivityCard> activityCards = activityCardRepository.getActivityCardByStoreId(storeId);
		// 获取充值赠送项目信息
		List<ActivityCardRechargeItem> activityCardRechargeItems = activityCardRechargeItemRepository.getActivityCardRechargeItemByStoreId(storeId);
		// 获取充值赠送产品信息
		List<ActivityCardRechargeGift> activityCardRechargeGifts = activityCardRechargeGiftRepository.getActivityCardRechargeGiftByStoreId(storeId);
		// 获取充值摇奖活动信息
		List<ActivityCardRechargeLottery> activityCardRechargeLotteries = activityCardRechargeLotteryRepository.getActivityCardRechargeLotteryByStoreId(storeId);
		// 批量绑定 充值赠送项目信息 + 充值赠送产品信息 + 充值摇奖活动信息
		activityCards = ActivityCard.bindActivityCardItemsAndGiftsAndLottries(activityCards, activityCardRechargeItems, activityCardRechargeGifts, activityCardRechargeLotteries);
		return activityCards;
	}
	
	// 根据 storeId + actiName 删除一条活动卡信息
	public int deleteActivityCardInfoByActiName(int storeId, String actiName) {
		// 获取活动卡基本信息
		ActivityCard activityCard = activityCardRepository.getActivityCardByActiName(storeId, actiName);
		int delCount = 0;
		if(null != activityCard) {
			int delActCardNum = activityCardRepository.deleteActivityCardByActiName(storeId, actiName);
			int delActItemNum = activityCardRechargeItemRepository.deleteActivityCardRechargeItemByActCardId(storeId, activityCard.getId());
			int delActGiftNum = activityCardRechargeGiftRepository.deleteActivityCardRechargeGiftByActCardId(storeId, activityCard.getId());
			int delActLottNum = activityCardRechargeLotteryRepository.deleteActivityCardRechargeLotteryByActCardId(storeId, activityCard.getId());
			delCount = delActCardNum + delActItemNum + delActGiftNum + delActLottNum;
		}
		return delCount;
	}
	
	// 根据 storeId 删除所有活动卡信息
	public int deleteActivityCardInfoByStoreId(int storeId) {
		int delActCardNum = activityCardRepository.deleteActivityCardByStoreId(storeId);
		int delActItemNum = activityCardRechargeItemRepository.deleteActivityCardRechargeItemByStoreId(storeId);
		int delActGiftNum = activityCardRechargeGiftRepository.deleteActivityCardRechargeGiftByStoreId(storeId);
		int delActLottNum = activityCardRechargeLotteryRepository.deleteActivityCardRechargeLotteryByStoreId(storeId);
		return delActCardNum + delActItemNum + delActGiftNum + delActLottNum;
	}
}
