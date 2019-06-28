package com.mry.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: cddufu@cn.ibm.com
 * @date:
 * @desc: 管理系统的产品，定义规则
 */
@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "price")
    private String price;
    @Column(name = "brand")
    private String brand;
    @Column(name = "info")
    private String info;
    @Column(name = "type")
    private String type;
}
