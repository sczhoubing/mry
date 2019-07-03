package com.mry.service;

import com.mry.enums.DateFormat;
import com.mry.model.Product;
import com.mry.model.ProductList;
import com.mry.model.ProductManage;
import com.mry.repository.ProductListRepository;
import com.mry.repository.ProductManageRepository;
import com.mry.utils.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductManageService {
    @Resource
    private ProductManageRepository productManageRepository;
    @Resource
    private ProductListRepository productListRepository;

    // 添加一条库存记录
    public int addProductManage(ProductManage productManage) {
        ProductManage tempProductManage = productManageRepository.exist(productManage.getStoreId(),
                productManage.getProductName(), productManage.getProductNumber(), productManage.getProductSource(),
                productManage.getExpireDate());
        if(null != tempProductManage) {
            productManage.setId(tempProductManage.getId());
        }
        productManageRepository.save(productManage);
        return productManage.getId();
    }

    // 编辑一条库存记录
    public int editProductManage(ProductManage productManage) {
        productManageRepository.save(productManage);
        return productManage.getId();
    }

    // 减去大库存记录，并添加到小库存中
    public Integer subtractProductManage(int id, int number, String status) {
        Optional<ProductManage> optional = productManageRepository.findById(id);
        // 如果库存记录不存在
        if(!optional.isPresent()) {
            return -1;
        }
        ProductManage productManage = optional.get();
        // 大库存商品当前剩余次数
        Integer originNumber = productManage.getProductNumber();
        if(originNumber <= 0) {
            return -2;
        }
        // 大库存记录减去指定次数后的次数
        Integer currentNumber = originNumber - number;
        if(currentNumber < 0) {
            return -3;
        }
        // 大库存商品过期了
        if(!CommonUtils.validExpireDate(productManage.getExpireDate())) {
            return -4;
        }
        // 大库存记录减去指定次数
        productManageRepository.subtractManage(currentNumber, id);
        // 添加一条记录到小库存
        ProductList productList = new ProductList();
        productList.setProductId(id);
        productList.setStoreId(productManage.getStoreId());
        productList.setProductName(productManage.getProductName());
        productList.setUpdateDate(CommonUtils.currentDate(DateFormat.FORMAT1.getFormat()));
        productList.setStatus(status);
        productListRepository.save(productList);
        return id;
    }

    // 根据 storeId + id 返回一条库存记录
    public ProductManage getProductManageById(int id, int storeId) {
        return productManageRepository.getProductManageById(id, storeId);
    }

    // 根据 storeId 返回一组库存记录
    public List<ProductManage> getProductManageByStoreId(int storeId) {
        return productManageRepository.getProductManageByStoreId(storeId);
    }

    // 根据 storeId + productName 返回一组库存记录
    public List<ProductManage> getProductManageByProductName(int storeId, String productName) {
        return productManageRepository.getProductManageByProductName(storeId, productName);
    }

    // 分页模糊查询
    public Page<ProductManage> getProductManageByPage(Pageable pageable, int storeId, String condition) {
        return productManageRepository.getProductManageByPage(pageable, storeId, condition);
    }

    // 分页查询
    public Page<ProductManage> getProductManageByPage(Pageable pageable, int storeId) {
        return productManageRepository.getProductManageByPage(pageable, storeId);
    }

    // 根据 storeId + id 删除一条库存记录
    public int deleteProductManageById(int id, int storeId) {
        return productManageRepository.deleteProductManageById(id, storeId);
    }

    // 根据 storeId 删除一组库存记录
    public int deleteProductManageByStoreId(int storeId) {
        return productManageRepository.deleteProductManageByStoreId(storeId);
    }

    // 根据 storeId + productName 删除一组库存记录
    public int deleteProductManageByProductName(int storeId, String productName) {
        return productManageRepository.deleteProductManageByProductName(storeId, productName);
    }
}
