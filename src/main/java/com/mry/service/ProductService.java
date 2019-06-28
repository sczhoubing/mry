package com.mry.service;

import com.mry.model.Product;
import com.mry.repository.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: cddufu@cn.ibm.com
 * @date:
 * @desc:
 */
@Service
@Transactional
public class ProductService {
    @Resource
    private ProductRepository productRepository;

    // 添加一条产品规则信息
    public int addProduct(Product product) {
        Product tempProduct = productRepository.exist(product.getStoreId(), product.getItemId(), product.getPrice(),
                product.getBrand(), product.getInfo(), product.getType());
        if(null != tempProduct) {
            product.setId(tempProduct.getId());
        }
        productRepository.save(product);
        return product.getId();
    }

    // 编辑一条产品规则信息
    public int editProduct(Product product) {
        productRepository.save(product);
        return product.getId();
    }

    // 根据 storeId 查询一组产品规则信息
    public List<Product> getProductByStoreId(int storeId) {
        return productRepository.getProductByStoreId(storeId);
    }

    // 根据 storeId + id 查询一条产品规则信息
    public Product getProductById(int storeId, int id) {
        return productRepository.getProductById(storeId, id);
    }

    // 根据 storeId + type 返回一组产品规则信息
    public List<Product> getProductByType(int storeId, String type) {
        return productRepository.getProductByType(storeId, type);
    }

    // 根据 storeId + id 删除一条产品规则信息
    public int deleteProductById(int storeId, int id) {
        return productRepository.deleteProductById(storeId, id);
    }

    // 根据 storeId 删除一组产品规则信息
    public int deleteProductByStoreId(int storeId) {
        return productRepository.deleteProductByStoreId(storeId);
    }
}
