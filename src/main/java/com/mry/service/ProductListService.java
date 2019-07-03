package com.mry.service;

import com.mry.enums.DateFormat;
import com.mry.model.ProductList;
import com.mry.repository.ProductListRepository;
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
public class ProductListService {
    @Resource
    private ProductListRepository productListRepository;

    // 添加/编辑 一条产品具体信息
    public int addProductList(ProductList productList) {
        productListRepository.save(productList);
        return productList.getProductId();
    }

    // 修改一条产品具体信息的状态
    public int editProductListStatus(int id, String status) {
        Optional<ProductList> optional = productListRepository.findById(id);
        if(!optional.isPresent()) {
            return -1;
        }
        ProductList productList = optional.get();
        productList.setStatus(status);
        productList.setUpdateDate(CommonUtils.currentDate(DateFormat.FORMAT1.getFormat()));
        productListRepository.save(productList);
        return productList.getId();
    }

    // 根据条件分页查询
    public Page<ProductList> getProductListByPage(Pageable pageable, int storeId, String condition) {
        return productListRepository.getProductListByPage(pageable, storeId, condition);
    }

    // 分页查询
    public Page<ProductList> getProductListByPage(Pageable pageable, int storeId) {
        return productListRepository.getProductListByPage(pageable, storeId);
    }

    // 根据 storeId 返回一组产品具体信息
    public List<ProductList> getProductListByStoreId(int storeId) {
        return productListRepository.getProductListByStoreId(storeId);
    }

    // 根据 storeId + id 返回一条产品具体信息
    public ProductList getProductListById(int storeId, int id) {
        return productListRepository.getProductListById(storeId, id);
    }

    // 根据 storeId + 库存 id 返回一组产品具体信息
    public List<ProductList> getProductListByProductId(int storeId, int productId) {
        return productListRepository.getProductListByProductId(storeId, productId);
    }

    // 根据 storeId + 产品名 返回一组产品具体信息
    public List<ProductList> getProductListByProductName(int storeId, String productName) {
        return productListRepository.getProductListByProductName(storeId, productName);
    }

    // 根据 storeId 删除一组产品具体信息
    public int deleteProductListByStoreId(int storeId) {
        return productListRepository.deleteProductListByStoreId(storeId);
    }

    // 根据 storeId + id 删除一条产品具体信息
    public int deleteProductListById(int storeId, int id) {
        return productListRepository.deleteProductListById(storeId, id);
    }

    // 根据 storeId + productId 删除一组产品具体信息
    public int deleteProductListByProductId(int storeId, int productId) {
        return productListRepository.deleteProductListByProductId(storeId, productId);
    }
}
