package com.mry.controller;

import com.mry.service.UserCardManageRecordsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: cddufu@cn.ibm.com
 * @date:
 * @desc:
 */
@RestController
@RequestMapping("/user/card/manage/records")
public class UserCardManageRecordsController {
    @Resource
    private UserCardManageRecordsService userCardManageRecordsService;

    @GetMapping("/get")
    public Map<String, Object> getUserCardManageRecords(int storeId, int userId) {
        Map<String, Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(storeId) && StringUtils.isEmpty(userId)) {
            result.put("userCardManageRecordsInfo", userCardManageRecordsService.getUserCardManageRecordsByStoreId(storeId));
        } else {
            result.put("userCardManageRecordsInfo", userCardManageRecordsService.getUserCardManageRecordsByUserId(userId));
        }
        return result;
    }
}
