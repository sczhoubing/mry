package com.mry.service;

import com.mry.enums.DateFormat;
import com.mry.enums.UserCardTypes;
import com.mry.model.UserCardManage;
import com.mry.model.UserCardManageRecords;
import com.mry.model.UserCardMemItem;
import com.mry.repository.UserCardManageRecordsRepository;
import com.mry.repository.UserCardManageRepository;
import com.mry.repository.UserCardMemItemRepository;
import com.mry.utils.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author: cddufu@cn.ibm.com
 * @date:
 * @desc:
 */
@Service
@Transactional
public class UserCardMemItemService {
    @Resource
    private UserCardMemItemRepository userCardMemItemRepository;
    @Resource
    private UserCardManageRepository userCardManageRepository;
    @Resource
    private UserCardManageRecordsRepository userCardManageRecordsRepository;

    // 手动添加一条卡项记录(不过不建议，因为卡关联的记录默认是从管理系统设置项取出的)
    public int addUserCardMemItem(UserCardMemItem userCardMemItem) {
        // 根据 storeId + cardId + userId 查询出所有记录
        List<UserCardMemItem> tempUserCardMemItems = userCardMemItemRepository.
                getUserCardMemItemByUserId(userCardMemItem.getStoreId(), userCardMemItem.getCardId(), userCardMemItem.getUserId());
        // 校验是否记录已经存在
        UserCardMemItem originUserCardMemItem = UserCardMemItem.validUserCardMemItem(userCardMemItem, tempUserCardMemItems);
        if(!StringUtils.isEmpty(originUserCardMemItem)) {
            userCardMemItem.setId(originUserCardMemItem.getId());
        }
        userCardMemItemRepository.save(userCardMemItem);
        return userCardMemItem.getStoreId();
    }

    // 修改一条卡项记录
    public int editUserCardMemItem(UserCardMemItem userCardMemItem) {
        userCardMemItemRepository.save(userCardMemItem);
        return userCardMemItem.getId();
    }

    // 修改一条卡项记录
    public int editUserCardMemItemTimes(int id, int times, String consDesc) {
        Optional<UserCardMemItem> optionalUserCardMemItem = userCardMemItemRepository.findById(id);
        // 如果记录不存在
        if(!optionalUserCardMemItem.isPresent()) {
            return -1;
        }
        UserCardMemItem userCardMemItem = optionalUserCardMemItem.get();
        // 卡项已过期
        if(!CommonUtils.validExpireDate(userCardMemItem.getItemExce())) {
            return -2;
        }
        // 获取当前卡项剩余消费次数
        String sCurrentTimes = userCardMemItem.getItemTime();
        int iCurrentTimes = Integer.parseInt(sCurrentTimes);
        // 该卡项消费记录为 0，已无法继续消费
        if(iCurrentTimes <= 0) {
           return -3;
        }
        // 如果本次消费记录已经大于卡项剩余记录
        if(times > iCurrentTimes) {
            return -4;
        }
        // 卡项关联次数减去传递的消费次数
        int residualTimes = iCurrentTimes - times;
        String updateDate = CommonUtils.currentDate(DateFormat.FORMAT1.getFormat());
        userCardMemItemRepository.editUserCardMemItemTimes(id, String.valueOf(residualTimes), updateDate);
        // 需要记录到卡扣历史记录中
        UserCardManage userCardManage = userCardManageRepository.getUserCardManageById(userCardMemItem.getCardId());
        UserCardManageRecords userCardManageRecords = recordCardConsumption(userCardManage, userCardMemItem, consDesc);
        userCardManageRecordsRepository.save(userCardManageRecords);
        return residualTimes;
    }

    // 生成消费记录
    public UserCardManageRecords recordCardConsumption(UserCardManage userCardManage, UserCardMemItem userCardMemItem, String consDesc) {
        UserCardManageRecords userCardManageRecords = new UserCardManageRecords();
        userCardManageRecords.setStoreId(userCardManage.getStoreId());
        userCardManageRecords.setUserId(userCardManage.getUserId());
        userCardManageRecords.setCardId(userCardManage.getId());
        userCardManageRecords.setCardItemId(userCardMemItem.getId());
        userCardManageRecords.setCardType(userCardManage.getCardType());
        userCardManageRecords.setCardOption(userCardManage.getCardOption());
        userCardManageRecords.setConsType(UserCardTypes.consType2.type());
        userCardManageRecords.setConsMoney("");
        userCardManageRecords.setConsDate(CommonUtils.currentDate(DateFormat.FORMAT1.getFormat()));
        userCardManageRecords.setConsDesc(consDesc);
        userCardManageRecords.setCardItem(userCardMemItem.getItemName());
        return userCardManageRecords;
    }

    // 根据 id 返回一条卡项记录
    public UserCardMemItem getUserCardMemItemById(int id) {
        return userCardMemItemRepository.findById(id).get();
    }

    // 根据 userId 返回该用户下所有的卡项记录
    public List<UserCardMemItem> getUserCardMemItemByUserId(int userId) {
        return userCardMemItemRepository.getUserCardMemItemByUserId(userId);
    }

    // 根据 storeId + cardId + userId 获取一组卡项记录
    public List<UserCardMemItem> getUserCardMemItemByUserId(int storeId, int cardId, int userId) {
        return userCardMemItemRepository.getUserCardMemItemByUserId(storeId, cardId, userId);
    }
}
