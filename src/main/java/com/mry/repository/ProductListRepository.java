package com.mry.repository;

import com.mry.model.ProductList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductListRepository extends JpaRepository<ProductList, Integer> {
    @Query(value = "select * from product_list where store_id = :storeId", nativeQuery = true)
    List<ProductList> getProductListByStoreId(@Param("storeId")int storeId);

    @Query(value = "select * from product_list where store_id = :storeId and id = :id", nativeQuery = true)
    ProductList getProductListById(@Param("storeId")int storeId, @Param("id")int id);

    @Query(value = "select * from product_list where store_id = :storeId and product_id = :productId", nativeQuery = true)
    List<ProductList> getProductListByProductId(@Param("storeId")int storeId, @Param("productId") int productId);

    @Query(value = "select * from product_list where store_id = :storeId and product_name = :productName", nativeQuery = true)
    List<ProductList> getProductListByProductName(@Param("storeId")int storeId, @Param("productName")String productName);

    @Query(value = "select * from product_list where product_name like %:condition%",
           countQuery = "select count(*) from product_list where product_name like %:condition%",
           nativeQuery = true)
    Page<ProductList> getProductListByPage(Pageable pageable, @Param("condition")String condition);

    @Query(value = "delete from product_list where store_id = :storeId", nativeQuery = true)
    @Modifying
    int deleteProductListByStoreId(@Param("storeId")int storeId);

    @Query(value = "delete from product_list where store_id = :storeId and id = :id", nativeQuery = true)
    @Modifying
    int deleteProductListById(@Param("storeId")int storeId, @Param("id")int id);

    @Query(value = "delete from product_list where store_id = :storeId and product_id = :productId", nativeQuery = true)
    @Modifying
    int deleteProductListByProductId(@Param("storeId")int storeId, @Param("productId") int productId);
}
