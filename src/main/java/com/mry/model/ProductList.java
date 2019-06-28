package com.mry.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_list")
@Data
public class ProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "update_date")
    private String updateDate;
    @Column(name = "status")
    private String status;
}
