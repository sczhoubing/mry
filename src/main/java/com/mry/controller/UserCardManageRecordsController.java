package com.mry.controller;

import com.mry.service.UserCardManageRecordsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Map<String, Object> getUserCardManageRecords(Integer storeId, Integer userId) {
        Map<String, Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(storeId) && StringUtils.isEmpty(userId)) {
            result.put("userCardManageRecordsInfo", userCardManageRecordsService.getUserCardManageRecordsByStoreId(storeId));
        } else if(StringUtils.isEmpty(storeId) && !StringUtils.isEmpty(userId)) {
            result.put("userCardManageRecordsInfo", userCardManageRecordsService.getUserCardManageRecordsByUserId(userId));
        }
        return result;
    }

    @GetMapping("/delete/{storeId}")
    public Map<String, Object> deleteUserCardManageRecords(@PathVariable("storeId") Integer storeId, Integer userId, Integer id) {
        Map<String, Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(userId) && StringUtils.isEmpty(id)) {
            result.put("msg", userCardManageRecordsService.deleteUserCardManageRecordsByUserId(storeId, userId));
        } else if(StringUtils.isEmpty(userId) && !StringUtils.isEmpty(id)) {
            result.put("msg", userCardManageRecordsService.deleteUserCardManageRecordsById(storeId, id));
        } else if(StringUtils.isEmpty(userId) && StringUtils.isEmpty(id)) {
            result.put("msg", userCardManageRecordsService.deleteUserCardManageRecordsStoreId(storeId));
        }
        return result;
    }
}
