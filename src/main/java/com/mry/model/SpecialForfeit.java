package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="special_forfeit")
public class SpecialForfeit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="forfeit_type")
	private String forfeitType;
	@Column(name="low_limit")
	private String lowLimit;
	@Column(name="high_limit")
	private String highLimit;
	@Column(name="forfeit_money")
	private String forfeitMoney;
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
	public String getForfeitType() {
		return forfeitType;
	}
	public void setForfeitType(String forfeitType) {
		this.forfeitType = forfeitType;
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
	public String getForfeitMoney() {
		return forfeitMoney;
	}
	public void setForfeitMoney(String forfeitMoney) {
		this.forfeitMoney = forfeitMoney;
	}
	@Override
	public String toString() {
		return "SpecialForfeit [id=" + id + ", storeId=" + storeId + ", forfeitType=" + forfeitType + ", lowLimit="
				+ lowLimit + ", highLimit=" + highLimit + ", forfeitMoney=" + forfeitMoney + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpecialForfeit other = (SpecialForfeit) obj;
		if (forfeitMoney == null) {
			if (other.forfeitMoney != null)
				return false;
		} else if (!forfeitMoney.equals(other.forfeitMoney))
			return false;
		if (forfeitType == null) {
			if (other.forfeitType != null)
				return false;
		} else if (!forfeitType.equals(other.forfeitType))
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
		if (storeId != other.storeId)
			return false;
		return true;
	}
	//校验是否除了 id 之外其他的属性相等
	public static SpecialForfeit validDuplicatePerforForfeit(SpecialForfeit perforForfeit, List<SpecialForfeit> perforForfeits) {
		SpecialForfeit duplicatePerforForfeit = null;
		for(SpecialForfeit tempPerforForfeit : perforForfeits) {
			if(tempPerforForfeit.equals(perforForfeit)) {
				duplicatePerforForfeit = tempPerforForfeit;
				break;
			}
		}
		return duplicatePerforForfeit;
	}
}
