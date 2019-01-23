package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.ExtCard;
import com.mry.model.ExtCardItem;
import com.mry.param.ExtCardParam;
import com.mry.repository.ExtCardItemRepository;
import com.mry.repository.ExtCardRepository;

@Service
@Transactional
public class ExtCardService {
	@Resource
	private ExtCardRepository extCardRepository;
	@Resource
	private ExtCardItemRepository extCardItemRepository;
	
	// 添加一条拓客卡信息
	public int addExtCardInfo(ExtCardParam params) {
		ExtCard extCard = params.getExtCard();
		ExtCard originExtCard = extCardRepository.getExtCardByName(extCard.getStoreId(), extCard.getCardName());
		// 保存拓客卡信息
		// 如果已经存在相同记录，就将其覆盖
		if(null != originExtCard) {
			extCard.setId(originExtCard.getId());
			extCardItemRepository.deleteExtCardItemsByExtCardId(extCard.getStoreId(), extCard.getId());
		}
		extCardRepository.save(extCard);
		
		// 保存拓客卡项目信息
		// 关联拓客卡项目和拓客卡信息
		List<ExtCardItem> extCardItems = ExtCardItem.setExtCardItemExtCardIdAndStoreId(params.getExtCardItems(), 
				params.getStoreId(), extCard.getId());
		extCardItemRepository.saveAll(extCardItems);
		return params.getStoreId();
	}
	
	// 根据 storeId + 拓客卡名称 查询一条拓客卡信息
	public ExtCard getExtCardByCardName(int storeId, String cardName) {
		ExtCard extCard = extCardRepository.getExtCardByName(storeId, cardName);
		if(null != extCard) {
			List<ExtCardItem> extCardItems = extCardItemRepository.getExtCardItemsByExtCardId(storeId, extCard.getId());
			extCard.setExtCardItems(extCardItems);
		}
		return extCard;
	}
	
	// 根据 storeId 查询所有拓客卡信息
	public List<ExtCard> getExtCardByStoreId(int storeId) {
		List<ExtCard> extCards = extCardRepository.getExtCardByStoreId(storeId);
		if(extCards.size() > 0) {
			List<ExtCardItem> extCardItems = extCardItemRepository.getExtCardItemsByStoreId(storeId);
			extCards = ExtCard.bindExtCardItems(extCards, extCardItems);
		}
		return extCards;
	}
	
	// 根据 storeId + 拓客卡名称 删除一条拓客卡信息
	public int deleteExtCardByCardName(int storeId, String cardName) {
		ExtCard extCard = extCardRepository.getExtCardByName(storeId, cardName);
		int delExtCardNum = 0, delExtCardItemNum = 0;
		if(null != extCard) {
			delExtCardNum = extCardRepository.deleteExtCardByCardName(storeId, cardName);
			delExtCardItemNum = extCardItemRepository.deleteExtCardItemsByExtCardId(storeId, extCard.getId());
		}
		return delExtCardNum + delExtCardItemNum;
	}
	     
	// 根据 storeId 删除所有拓客卡信息
	public int deleteExtCardByStoreId(int storeId) {
		int delExtCardNum = extCardRepository.deleteExtCardByStoreId(storeId);
		int delExtCardItemNum = extCardItemRepository.deleteExtCardItemsByStoreId(storeId);
		return delExtCardNum + delExtCardItemNum;
	}
	
}
