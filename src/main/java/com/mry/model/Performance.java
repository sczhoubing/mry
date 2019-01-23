package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="performance")
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
	public String getEmpPay() {
		return empPay;
	}
	public void setEmpPay(String empPay) {
		this.empPay = empPay;
	}
	public String getPerOption() {
		return perOption;
	}
	public void setPerOption(String perOption) {
		this.perOption = perOption;
	}
	public String getAchMoney() {
		return achMoney;
	}
	public void setAchMoney(String achMoney) {
		this.achMoney = achMoney;
	}
	public String getRetMoney() {
		return retMoney;
	}
	public void setRetMoney(String retMoney) {
		this.retMoney = retMoney;
	}
	public String getnAchMoney() {
		return nAchMoney;
	}
	public void setnAchMoney(String nAchMoney) {
		this.nAchMoney = nAchMoney;
	}
	public String getDedMoney() {
		return dedMoney;
	}
	public void setDedMoney(String dedMoney) {
		this.dedMoney = dedMoney;
	}
	@Override
	public String toString() {
		return "Performance [id=" + id + ", storeId=" + storeId + ", empPay=" + empPay + ", perOption=" + perOption
				+ ", achMoney=" + achMoney + ", retMoney=" + retMoney + ", nAchMoney=" + nAchMoney + ", dedMoney="
				+ dedMoney + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Performance other = (Performance) obj;
		if (achMoney == null) {
			if (other.achMoney != null)
				return false;
		} else if (!achMoney.equals(other.achMoney))
			return false;
		if (dedMoney == null) {
			if (other.dedMoney != null)
				return false;
		} else if (!dedMoney.equals(other.dedMoney))
			return false;
		if (empPay == null) {
			if (other.empPay != null)
				return false;
		} else if (!empPay.equals(other.empPay))
			return false;
		/*if (id != other.id)
			return false;*/
		if (nAchMoney == null) {
			if (other.nAchMoney != null)
				return false;
		} else if (!nAchMoney.equals(other.nAchMoney))
			return false;
		if (perOption == null) {
			if (other.perOption != null)
				return false;
		} else if (!perOption.equals(other.perOption))
			return false;
		if (retMoney == null) {
			if (other.retMoney != null)
				return false;
		} else if (!retMoney.equals(other.retMoney))
			return false;
		if (storeId != other.storeId)
			return false;
		return true;
	}
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
