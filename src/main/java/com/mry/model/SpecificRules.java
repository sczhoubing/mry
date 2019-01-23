package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="specific_rules")
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getLowLimit() {
		return lowLimit;
	}
	public void setLowLimit(String lowLimit) {
		this.lowLimit = lowLimit;
	}
	public String getHighLimit() {
		return highLimit;
	}
	public void setHighLimit(String highLimit) {
		this.highLimit = highLimit;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getBasePay() {
		return basePay;
	}
	public void setBasePay(String basePay) {
		this.basePay = basePay;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getEmpLevel() {
		return empLevel;
	}
	public void setEmpLevel(String empLevel) {
		this.empLevel = empLevel;
	}
	public String getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(String empSalary) {
		this.empSalary = empSalary;
	}
	public String getBaseSalaryRule() {
		return baseSalaryRule;
	}
	public void setBaseSalaryRule(String baseSalaryRule) {
		this.baseSalaryRule = baseSalaryRule;
	}
	public String getBaseSalaryOption() {
		return baseSalaryOption;
	}
	public void setBaseSalaryOption(String baseSalaryOption) {
		this.baseSalaryOption = baseSalaryOption;
	}
	@Override
	public String toString() {
		return "SpecificRules [id=" + id + ", storeId=" + storeId + ", lowLimit=" + lowLimit + ", highLimit="
				+ highLimit + ", money=" + money + ", basePay=" + basePay + ", empType=" + empType + ", empLevel="
				+ empLevel + ", empSalary=" + empSalary + ", baseSalaryRule=" + baseSalaryRule + ", baseSalaryOption="
				+ baseSalaryOption + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpecificRules other = (SpecificRules) obj;
		if (basePay == null) {
			if (other.basePay != null)
				return false;
		} else if (!basePay.equals(other.basePay))
			return false;
		if (baseSalaryOption == null) {
			if (other.baseSalaryOption != null)
				return false;
		} else if (!baseSalaryOption.equals(other.baseSalaryOption))
			return false;
		if (baseSalaryRule == null) {
			if (other.baseSalaryRule != null)
				return false;
		} else if (!baseSalaryRule.equals(other.baseSalaryRule))
			return false;
		if (empLevel == null) {
			if (other.empLevel != null)
				return false;
		} else if (!empLevel.equals(other.empLevel))
			return false;
		if (empSalary == null) {
			if (other.empSalary != null)
				return false;
		} else if (!empSalary.equals(other.empSalary))
			return false;
		if (empType == null) {
			if (other.empType != null)
				return false;
		} else if (!empType.equals(other.empType))
			return false;
		if (highLimit == null) {
			if (other.highLimit != null)
				return false;
		} else if (!highLimit.equals(other.highLimit))
			return false;
		/*if (id != other.id)
			return false;*/
		if (lowLimit == null) {
			if (other.lowLimit != null)
				return false;
		} else if (!lowLimit.equals(other.lowLimit))
			return false;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
			return false;
		if (storeId != other.storeId)
			return false;
		return true;
	}
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
