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
@Table(name="specific_rules")
@Data
@EqualsAndHashCode(exclude = "id")
public class SpecificRules {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="low_limit")
	private String lowLimit;
	@Column(name="high_limit")
	private String highLimit;
	@Column(name="money")
	private String money;
	@Column(name="base_pay")
	private String basePay;
	@Column(name="emp_type")
	private String empType;
	@Column(name="emp_level")
	private String empLevel;
	@Column(name="emp_salary")
	private String empSalary;
	@Column(name="base_salary_rule")
	private String baseSalaryRule;
	@Column(name="base_salary_option")
	private String baseSalaryOption;

	// 判断两个对象是否除了 id 之外其他属性相等
	public static SpecificRules validDuplicate(List<SpecificRules> specificRules, SpecificRules specificRule) {
		SpecificRules originSpecificRules = null;
		for(SpecificRules tempSpecificRule : specificRules) {
			if(tempSpecificRule.equals(specificRule)) {
				originSpecificRules = tempSpecificRule;
				break;
			}
		}
		return originSpecificRules;
	}
}
