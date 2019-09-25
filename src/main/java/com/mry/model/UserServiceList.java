package com.mry.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
/**
 * @author: cddufu@cn.ibm.com
 * @date: 2019-06-14
 * @desc: 用户服务单
 */
@Entity
@Table(name = "user_service_list")
@Data
public class UserServiceList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.mry.generator.IdGenerator")
    private String id;
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "user_id")
    private int userId;
    /* 1. 服务单(默认), 2. 现金单 */
    @Column(name = "type")
    private int type;
    @Column(name = "ad_type")
    private int adType;
    @Column(name = "sd_type")
    private int sdType;
    @Column(name = "technician")
    private String technician;
    @Column(name = "is_anonymous")
    private String isAnonymous;
    @Column(name = "is_appoint")
    private String isAppoint;
    @Column(name = "is_pre_sale")
    private String isPreSale;
    @Column(name = "bed_number")
    private String bedNumber;
    @Column(name = "project")
    private String project;
    @Column(name = "child_project")
    private String childProject;
    @Column(name = "status")
    private String status;
    @Column(name = "update_date")
    private String updateDate;
    @Column(name = "pay_type")
    private String payType;
    @Column(name = "pay_money")
    private String payMoney;
}
