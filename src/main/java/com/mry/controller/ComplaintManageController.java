package com.mry.controller;

import com.mry.model.ComplaintManage;
import com.mry.service.ComplaintManageService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/complaint/manage")
public class ComplaintManageController {
    @Resource
    private ComplaintManageService complaintManageService;

    @PostMapping("/add")
    public Map<String, Object> addComplaintManage(@RequestBody ComplaintManage complaintManage) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", complaintManageService.addComplaintManage(complaintManage));
        return result;
    }

    @GetMapping("/store/{storeId}")
    public Map<String, Object> getComplaintManage(@PathVariable("storeId") Integer storeId, Integer userId, Integer id) {
        Map<String, Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(userId) && StringUtils.isEmpty(id)) {
            result.put("complaintManageInfo", complaintManageService.getComplaintManageByUserId(storeId, userId));
        } else if(StringUtils.isEmpty(userId) && !StringUtils.isEmpty(id)) {
            result.put("complaintManageInfo", complaintManageService.getComplaintManageById(storeId, id));
        } else if(StringUtils.isEmpty(userId) && StringUtils.isEmpty(id)) {
            result.put("complaintManageInfo", complaintManageService.getComplaintManageByStoreId(storeId));
        }
        return result;
    }

    @GetMapping("/delete/{storeId}")
    public Map<String, Object> deleteComplaintManage(@PathVariable("storeId") Integer storeId, Integer userId, Integer id) {
        Map<String, Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(userId) && StringUtils.isEmpty(id)) {
            result.put("msg", complaintManageService.deleteComplaintManageByUserId(storeId, userId));
        } else if(StringUtils.isEmpty(userId) && !StringUtils.isEmpty(id)) {
            result.put("msg", complaintManageService.deleteComplaintManageById(storeId, id));
        } else if(StringUtils.isEmpty(userId) && StringUtils.isEmpty(id)) {
            result.put("msg", complaintManageService.deleteComplaintManageByStoreId(storeId));
        }
        return result;
    }
}
