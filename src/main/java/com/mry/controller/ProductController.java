package com.mry.controller;

import com.mry.model.Product;
import com.mry.service.ProductService;
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
@RequestMapping("/products")
public class ProductController {
    @Resource
    private ProductService productService;

    @PostMapping("/add")
    public Map<String, Object> addProduct(@RequestBody Product product) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", productService.addProduct(product));
        return result;
    }

    @PostMapping("/edit")
    public Map<String, Object> editProduct(@RequestBody Product product) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", productService.editProduct(product));
        return result;
    }

    @GetMapping("/store/{storeId}")
    public Map<String, Object> getProduct(@PathVariable("storeId")int storeId, Integer id, String type) {
        Map<String, Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(id) && StringUtils.isEmpty(type)) {
            result.put("product", productService.getProductById(storeId, id));
        } else if(StringUtils.isEmpty(id) && !StringUtils.isEmpty(type)) {
            result.put("product", productService.getProductByType(storeId, type));
        } else if(StringUtils.isEmpty(id) && StringUtils.isEmpty(type)) {
            result.put("product", productService.getProductByStoreId(storeId));
        }
        return result;
    }

    @GetMapping("/delete/{storeId}")
    public Map<String, Object> deleteProduct(@PathVariable("storeId")int storeId, Integer id) {
        Map<String, Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(id)) {
            result.put("msg", productService.deleteProductById(storeId, id));
        } else {
            result.put("msg", productService.deleteProductByStoreId(storeId));
        }
        return result;
    }
}
