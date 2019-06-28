package com.mry.repository;

import com.mry.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: cddufu@cn.ibm.com
 * @date:
 * @desc:
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from product where store_id = :storeId and item_id = :itemId and price = :price" +
            " and brand = :brand and info = :info and type = :type", nativeQuery = true)
    Product exist(@Param("storeId")int storeId, @Param("itemId")int itemId, @Param("price")String price,
                  @Param("brand")String brand, @Param("info")String info, @Param("type")String type);

    @Query(value = "select * from product where store_id = :storeId", nativeQuery = true)
    List<Product> getProductByStoreId(@Param("storeId")int storeId);

    @Query(value = "select * from product where store_id = :storeId and id = :id", nativeQuery = true)
    Product getProductById(@Param("storeId")int storeId, @Param("id")int id);

    @Query(value = "select * from product where store_id = :storeId and type = :type", nativeQuery = true)
    List<Product> getProductByType(@Param("storeId")int storeId, @Param("type")String type);

    @Query(value = "select * from product where item_id in(:itemIds)", nativeQuery = true)
    List<Product> getProductByItemIds(@Param("itemIds")List<Integer> itemIds);

    @Query(value = "update product set item_id = :itemId where id in(:productIds)", nativeQuery = true)
    @Modifying
    void bindItems(@Param("itemId")int itemId, @Param("productIds")List<Integer> productIds);

    @Query(value = "delete from product where store_id = :storeId and id = :id", nativeQuery = true)
    @Modifying
    int deleteProductById(@Param("storeId")int storeId, @Param("id")int id);

    @Query(value = "delete from product where store_id = :storeId", nativeQuery = true)
    @Modifying
    int deleteProductByStoreId(@Param("storeId")int storeId);
}
