package com.mry.controller;

import com.alibaba.fastjson.JSONObject;
import com.mry.model.UserCardMemItem;
import com.mry.service.UserCardMemItemService;
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
@RequestMapping("/user/card/mem/item")
public class UserCardMemItemController {
    @Resource
    private UserCardMemItemService userCardMemItemService;

    @PostMapping("/add")
    public Map<String, Object> addUserCardMemItem(@RequestBody UserCardMemItem userCardMemItem) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", userCardMemItemService.addUserCardMemItem(userCardMemItem));
        return result;
    }

    @PostMapping("/edit")
    public Map<String, Object> editUserCardMemItem(@RequestBody UserCardMemItem userCardMemItem) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", userCardMemItemService.editUserCardMemItem(userCardMemItem));
        return result;
    }

    @PostMapping("/times")
    public Map<String, Object> markUserCardMemItemTimes(@RequestBody JSONObject params) {
        Map<String, Object> result = new HashMap<>();
        int id = params.getInteger("id");
        int times = params.getInteger("times");
        String consDesc = params.getString("consDesc");
        result.put("itemTimes", userCardMemItemService.editUserCardMemItemTimes(id, times, consDesc));
        return result;
    }

    @GetMapping("/id/{id}")
    public Map<String, Object> getUserCardMemItem(@PathVariable("id") int id) {
        Map<String, Object> result = new HashMap<>();
        result.put("itemInfo", userCardMemItemService.getUserCardMemItemById(id));
        return result;
    }

    @GetMapping("/userId/{userId}")
    public Map<String, Object> getUserCardMemItemByUserId(@PathVariable("userId") int userId) {
        Map<String, Object> result = new HashMap<>();
        result.put("itemInfo", userCardMemItemService.getUserCardMemItemByUserId(userId));
        return result;
    }

    @GetMapping("/store/{storeId}")
    public Map<String, Object> getUserCardMemItem(@PathVariable("storeId") int storeId, int cardId, int userId) {
        Map<String, Object> result = new HashMap<>();
        result.put("itemInfo", userCardMemItemService.getUserCardMemItemByUserId(storeId, cardId, userId));
        return result;
    }

    @GetMapping("/delete/id/{id}")
    public Map<String, Object> deleteUserCardMemItem(@PathVariable("id") int id) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", userCardMemItemService.deleteUserCardMemItemById(id));
        return result;
    }

    @GetMapping("/delete/store/{storeId}")
    public Map<String, Object> deleteUserCardMemItem(@PathVariable("storeId") int storeId, Integer cardId, Integer userId) {
        Map<String, Object> result = new HashMap<>();
        if(null != cardId && null == userId) {
            result.put("msg", userCardMemItemService.deleteUserCardMemItemByCardId(storeId, cardId));
        } else if(null == cardId && null != userId) {
            result.put("msg", userCardMemItemService.deleteUserCardMemItemByUserId(storeId, userId));
        } else if(null == cardId && null == userId) {
            result.put("msg", userCardMemItemService.deleteUserCardMemItemByStoreId(storeId));
        }
        return result;
    }

}
