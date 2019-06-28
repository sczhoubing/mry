package com.mry.controller;

import com.alibaba.fastjson.JSONObject;
import com.mry.model.UserServiceList;
import com.mry.service.UserServiceListService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: cddufu@cn.ibm.com
 * @date:
 * @desc:
 */
@RestController
@RequestMapping("/user/service/list")
public class UserServiceListController {
    @Resource
    private UserServiceListService userServiceListService;

    @PostMapping("/add")
    public Map<String, Object> addUserServiceList(@RequestBody UserServiceList userServiceList) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", userServiceListService.addUserServiceList(userServiceList));
        return result;
    }

    @PostMapping("/edit")
    public Map<String, Object> editUserServiceList(@RequestBody UserServiceList userServiceList) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", userServiceListService.editUserServiceList(userServiceList));
        return result;
    }

    @PostMapping("/status")
    public Map<String, Object> editUserServiceListStatus(@RequestBody JSONObject params) {
        Map<String, Object> result = new HashMap<>();
        String id = params.getString("id");
        String status = params.getString("status");
        result.put("msg", userServiceListService.editUserServiceListStatus(id, status));
        return result;
    }

    @GetMapping("/store/{storeId}")
    public Map<String, Object> getUserServiceList(@PathVariable("storeId")int storeId, String param) {
        Map<String, Object> result = new HashMap<>();
        result.put("userServiceListInfo", userServiceListService.getUserServiceList(storeId, param));
        return result;
    }

    @GetMapping("/delete/{storeId}")
    public Map<String, Object> deleteUserServiceList(@PathVariable("storeId")int storeId, String id, Integer userId) {
        Map<String, Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(id) && StringUtils.isEmpty(userId)) {
            result.put("msg", userServiceListService.deleteUserServiceListById(storeId, id));
        } else if (StringUtils.isEmpty(id) && !StringUtils.isEmpty(userId)) {
            result.put("msg", userServiceListService.deleteUserServiceListsByUserId(storeId, userId));
        } else if(StringUtils.isEmpty(id) && StringUtils.isEmpty(userId)) {
            result.put("msg", userServiceListService.deleteUserServiceListsByStoreId(storeId));
        }
        return result;
    }
}
