package com.mry.service;

import com.mry.model.UserCardManageRecords;
import com.mry.repository.UserCardManageRecordsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: cddufu@cn.ibm.com
 * @date:
 * @desc:
 */
@Service
@Transactional
public class UserCardManageRecordsService {
    @Resource
    private UserCardManageRecordsRepository userCardManageRecordsRepository;

    // 根据 storeId 返回该门店下所有的消费记录
    public List<UserCardManageRecords> getUserCardManageRecordsByStoreId(int storeId) {
        return userCardManageRecordsRepository.getUserCardManageRecordsByStoreId(storeId);
    }

    // 根据 userId 返回该用户所有的消费记录
    public List<UserCardManageRecords>  getUserCardManageRecordsByUserId(int userId) {
        return userCardManageRecordsRepository.getUserCardManageRecordsByUserId(userId);
    }
}
