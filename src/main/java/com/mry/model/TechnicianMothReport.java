package com.mry.model;

import com.mry.utils.CommonUtils;
import lombok.Data;

@Data
public class TechnicianMothReport implements Comparable<TechnicianMothReport> {
    // 日期
    private String date;
    // 客流
    private Integer passengerFlow;
    // 项目数
    private Integer projectNumber;
    // 面部实操指定
    private Integer faceAppoint;
    // 面部实操非指定
    private Integer faceNotAppoint;
    // 身体实操指定
    private Integer bodyAppoint;
    // 身体实操非指定
    private Integer bodyNotAppoint;
    // 实操总计
    private Integer operationCapability;
    // 现金业绩
    private Double cashAchievement;
    // 卡扣
    private Double cardDeduction;
    // 新客人数
    private Integer newCustomer;
    // 成交人数
    private Integer dealCustomer;
    // 售前业绩
    private Double preSaleAchievement;
    // 卡扣项目
    private Integer cardDeductionProjects;
    // 高端项目
    private Integer adProject;
    // 超扣项目
    private Integer sdProject;
    // 备注信息
    private String remarks;

    @Override
    public int compareTo(TechnicianMothReport o) {
        if(CommonUtils.afterDate(this.date, o.getDate())) {
            return 0;
        }
        return -1;
    }
}
