package com.mry.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "complaint_manage")
@Data
public class ComplaintManage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "content")
    private String content;
    @Column(name = "update_date")
    private String updateDate;
    @Column(name = "status")
    private String status;
}
