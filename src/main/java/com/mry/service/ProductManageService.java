package com.mry.service;

import com.mry.model.ProductManage;
import com.mry.repository.ProductManageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ProductManageService {
    @Resource
    private ProductManageRepository productManageRepository;

    // 添加一条库存记录
    public int addProductManage(ProductManage productManage) {
        ProductManage tempProductManage = productManageRepository.existProductManage(productManage.getStoreId(),
                productManage.getProductName(), productManage.getProductNumber(), productManage.getProductSource(),
                productManage.getExpireDate());
        // 数据库已经有相同的数据，应将其覆盖
        if(null != tempProductManage) {
            productManage.setId(tempProductManage.getId());
            // todo 要同时更新小库存的关联信息

        }
        productManageRepository.save(productManage);
        return productManage.getStoreId();
    }

    // 编辑一条库存记录
    public int editProductManage(ProductManage productManage) {
        productManageRepository.save(productManage);
        // todo 要同时更新小库存的关联信息
        return productManage.getId();
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
    public Page<ProductManage> getProductManageByPage(Pageable pageable, String condition) {
        return productManageRepository.getProductManageByPage(pageable, condition);
    }

    // 分页查询
    public Page<ProductManage> getProductManageByPage(Pageable pageable) {
        return productManageRepository.findAll(pageable);
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
