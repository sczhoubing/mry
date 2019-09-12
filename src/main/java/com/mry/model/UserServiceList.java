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
    @Column(name = "type")
    private int type;
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
