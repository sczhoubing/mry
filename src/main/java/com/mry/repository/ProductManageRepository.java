package com.mry.repository;

import com.mry.model.ProductManage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductManageRepository extends JpaRepository<ProductManage, Integer> {
    @Query(value = "select * from product_manage where store_id = :storeId and product_name = :productName" +
            " and product_number = :productNumber and product_source = :productSource and expire_date = :expireDate", nativeQuery = true)
    ProductManage exist(@Param("storeId")int storeId, @Param("productName")String productName, @Param("productNumber")int productNumber,
                        @Param("productSource")String productSource, @Param("expireDate")String expireDate);

    @Query(value = "select * from product_manage where store_id = :storeId", nativeQuery = true)
    List<ProductManage> getProductManageByStoreId(@Param("storeId")int storeId);

    @Query(value = "select * from product_manage where id = :id and store_id = :storeId", nativeQuery = true)
    ProductManage getProductManageById(@Param("id")int id, @Param("storeId")int storeId);

    @Query(value = "select * from product_manage where store_id = :storeId and product_name = :productName", nativeQuery = true)
    List<ProductManage> getProductManageByProductName(@Param("storeId")int storeId, @Param("productName")String productName);

    @Query(value="select * from product_manage where product_name like %:condition% or product_source like %:condition%",
           countQuery="select count(*) from product_manage where product_name like %:condition% or product_source like %:condition%",
           nativeQuery=true)
    Page<ProductManage> getProductManageByPage(Pageable pageable, @Param("condition") String condition);

    @Query(value = "delete from product_manage where id = :id and store_id = :storeId", nativeQuery = true)
    @Modifying
    int deleteProductManageById(@Param("id")int id, @Param("storeId")int storeId);

    @Query(value = "delete from product_manage where store_id = :storeId", nativeQuery = true)
    @Modifying
    int deleteProductManageByStoreId(@Param("storeId")int storeId);

    @Query(value = "delete from product_manage where store_id = :storeId and product_name = :productName", nativeQuery = true)
    @Modifying
    int deleteProductManageByProductName(@Param("storeId")int storeId, @Param("productName")String productName);
}
