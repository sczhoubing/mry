package com.mry.model;

import com.mry.utils.CommonUtils;
import lombok.Data;

@Data
public class StoreMothReport implements Comparable<StoreMothReport> {
    /* 日期 */
    private String date;
    /* 总业绩 */
    private Double achievement;
    /* 总客流 */
    private Integer passengerFlow;
    /* 总实操 */
    private Integer operationCapability;
    /* 产品现金 */
    private Double productMoney;
    /* 产品卡扣 */
    private Double productCardDeduction;
    /* 卡扣项目 */
    private Integer cardProjects;
    /* 售前新客人数 */
    private Integer preSaleNewCustomer;
    /* 售前成交人数 */
    private Integer preSaleDealCustomer;
    /* 售前业绩 */
    private Double preSaleAchievement;
    /* 备注 */
    private String remarks;

    @Override
    public int compareTo(StoreMothReport o) {
        if(CommonUtils.afterDate(this.date, o.getDate())) {
            return 0;
        }
       return -1;
    }
}
