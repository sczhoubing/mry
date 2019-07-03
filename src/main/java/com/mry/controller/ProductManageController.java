package com.mry.controller;

import com.alibaba.fastjson.JSONObject;
import com.mry.model.ProductManage;
import com.mry.service.ProductManageService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/product/manage")
public class ProductManageController {
    @Resource
    private ProductManageService productManageService;

    @PostMapping("/add")
    public Map<String, Object> addProductManage(@RequestBody ProductManage productManage) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", productManageService.addProductManage(productManage));
        return result;
    }

    @PostMapping("/edit")
    public Map<String, Object> editProductManage(@RequestBody ProductManage productManage) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", productManageService.editProductManage(productManage));
        return result;
    }

    @PostMapping("/subtract")
    public Map<String, Object> subtractProductManage(@RequestBody JSONObject params) {
        Map<String, Object> result = new HashMap<>();
        Integer id = params.getInteger("id");
        Integer number = params.getInteger("number");
        String status = params.getString("status");
        if(StringUtils.isEmpty(number)) {
            number = 1;
        }
        if(StringUtils.isEmpty(status)) {
            status = "1";
        }
        result.put("msg", productManageService.subtractProductManage(id, number, status));
        return result;
    }

    @GetMapping("/store/{storeId}")
    public Map<String, Object> getProductManage(@PathVariable("storeId")int storeId, Integer id, String productName) {
        Map<String, Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(id) && StringUtils.isEmpty(productName)) {
            result.put("productManageInfo", productManageService.getProductManageById(id, storeId));
        } else if(StringUtils.isEmpty(id) && !StringUtils.isEmpty(productName)) {
            result.put("productManageInfo", productManageService.getProductManageByProductName(storeId, productName));
        } else if(StringUtils.isEmpty(id) && StringUtils.isEmpty(productName)) {
            result.put("productManageInfo", productManageService.getProductManageByStoreId(storeId));
        }
        return result;
    }

    @GetMapping("/page/{currentPage}")
    public Map<String, Object> getProductManageByPage(@PathVariable("currentPage")int currentPage, Integer pageSize, String condition) {
        Map<String, Object> result = new HashMap<>();
        if(StringUtils.isEmpty(pageSize)) {
            pageSize = 10;
        }
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        if(!StringUtils.isEmpty(condition)) {
            result.put("productManageInfo", productManageService.getProductManageByPage(pageable, condition));
        } else {
            result.put("productManageInfo", productManageService.getProductManageByPage(pageable));
        }
        return result;
    }

    @GetMapping("/delete/{storeId}")
    public Map<String, Object> deleteProductManage(@PathVariable("storeId")int storeId, Integer id, String productName) {
        Map<String, Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(id) && StringUtils.isEmpty(productName)) {
            result.put("msg", productManageService.deleteProductManageById(id, storeId));
        } else if(StringUtils.isEmpty(id) && !StringUtils.isEmpty(productName)) {
            result.put("msg", productManageService.deleteProductManageByProductName(storeId, productName));
        } else if(StringUtils.isEmpty(id) && StringUtils.isEmpty(productName)) {
            result.put("msg", productManageService.deleteProductManageByStoreId(storeId));
        }
        return result;
    }
}
