package com.mry.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="performance")
@Data
@EqualsAndHashCode(exclude = "id")
public class Performance {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="emp_pay")
	private String empPay;
	@Column(name="per_option")
	private String perOption;
	@Column(name="ach_money")
	private String achMoney;
	@Column(name="ret_money")
	private String retMoney;
	@Column(name="nach_money")
	private String nAchMoney;
	@Column(name="ded_money")
	private String dedMoney;

	// 校验除了 id 之外其他属性相同的记录
	public static Performance validDuplicatePerformance(Performance performance, List<Performance> performances) {
		Performance duplicatePerformance = null;
		for(Performance tempPerformance : performances) {
			if(tempPerformance.equals(performance)) {
				duplicatePerformance = tempPerformance;
				break;
			}
		}
		return duplicatePerformance;
	}
}
