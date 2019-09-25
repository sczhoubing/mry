package com.mry.model;

import com.mry.utils.CommonUtils;
import lombok.Data;

@Data
public class StoreDailyReport implements Comparable<StoreDailyReport> {
    /* 日期 */
    private String time;
    /* 员工姓名 */
    private String empName;
    /* 员工电话 */
    private String phoneNum;
    /* 顾客姓名 */
    private String clientName;
    /* 面部指定 */
    private Integer faceAppoint;
    /* 面部非指定 */
    private Integer faceNotAppoint;
    /* 身体指定 */
    private Integer bodyAppoint;
    /* 身体非指定 */
    private Integer bodyNotAppoint;
    /* 产品现金 */
    private Double productCash;
    /* 产品卡扣 */
    private Double productCardDeduction;
    /* 服务项目 */
    private String projects;
    /* 服务项目金额 */
    private Double projectsMoney;
    /* 实操总计 */
    private Integer operationCapability;
    /* 售前业绩 */
    private Double preSaleAchievement;
    /* 售后业绩 */
    private Double afterSaleAchievement;
    /* 高端项目 */
    private Integer adProject;
    /* 超扣项目 */
    private Integer sdProject;

    @Override
    public int compareTo(StoreDailyReport o) {
        if(this.empName.equals(o.empName)) {
            return 0;
        }
        return -1;
    }
}




