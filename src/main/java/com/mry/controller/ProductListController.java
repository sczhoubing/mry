package com.mry.controller;

import com.alibaba.fastjson.JSONObject;
import com.mry.model.ProductList;
import com.mry.service.ProductListService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/product/list")
public class ProductListController {
    @Resource
    private ProductListService productListService;

    @PostMapping("/add")
    public Map<String, Object> addProductList(@RequestBody ProductList productList) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", productListService.addProductList(productList));
        return result;
    }

    @PostMapping("/status")
    public Map<String, Object> editProductListStatus(@RequestBody JSONObject params) {
        Map<String, Object> result = new HashMap<>();
        int id = params.getInteger("id");
        String status = params.getString("status");
        result.put("msg", productListService.editProductListStatus(id, status));
        return result;
    }

    @GetMapping("/store/{storeId}")
    public Map<String, Object> getProductList(@PathVariable("storeId")int storeId, Integer id, Integer productId, String productName) {
        Map<String, Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(id) && StringUtils.isEmpty(productId) && StringUtils.isEmpty(productName)) {
            result.put("productList", productListService.getProductListById(storeId, id));
        } else if(StringUtils.isEmpty(id) && !StringUtils.isEmpty(productId) && StringUtils.isEmpty(productName)) {
            result.put("productList", productListService.getProductListByProductId(storeId, productId));
        } else if(StringUtils.isEmpty(id) && StringUtils.isEmpty(productId) && !StringUtils.isEmpty(productName)) {
            result.put("productList", productListService.getProductListByProductName(storeId, productName));
        } else if(StringUtils.isEmpty(id) && StringUtils.isEmpty(productId) && StringUtils.isEmpty(productName)) {
            result.put("productList", productListService.getProductListByStoreId(storeId));
        }
        return result;
    }

    @GetMapping("/delete/{storeId}")
    public Map<String, Object> deleteProductList(@PathVariable("storeId")int storeId, Integer id, Integer productId) {
        Map<String, Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(id) && StringUtils.isEmpty(productId)) {
            result.put("msg", productListService.deleteProductListById(storeId, id));
        } else if(StringUtils.isEmpty(id) && !StringUtils.isEmpty(productId)) {
            result.put("msg", productListService.deleteProductListByProductId(storeId, productId));
        } else if(StringUtils.isEmpty(id) && StringUtils.isEmpty(productId)) {
            result.put("msg", productListService.deleteProductListByStoreId(storeId));
        }
        return result;
    }
}
