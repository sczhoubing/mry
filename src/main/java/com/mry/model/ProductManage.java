package com.mry.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "product_manage")
@Data
@EqualsAndHashCode(exclude = "id")
public class ProductManage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="store_id")
    private int storeId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_number")
    private int productNumber;
    @Column(name = "product_source")
    private String productSource;
    @Column(name = "expire_date")
    private String expireDate;
}
