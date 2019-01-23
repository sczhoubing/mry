package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="performance_commission")
public class PerformanceCommission {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="per_comtype")
	private String perComType;
	@Column(name="low_limit")
	private String lowLimit;
	@Column(name="high_limit")
	private String highLimit;
	@Column(name="stored_value")
	private String storedValue;
	@Column(name="cash_treatment")
	private String cashTreatment;
	@Column(name="cash_products")
	private String cashProducts;
	@Column(name="card_treatment")
	private String cardTreatment;
	@Column(name="card_products")
	private String cardProducts;
	@Column(name="discount_products")
	private String discountProducts;
	@Column(name="high_end_products")
	private String highEndProducts;
	@Column(name="other")
	private String other;
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
	public String getPerComType() {
		return perComType;
	}
	public void setPerComType(String perComType) {
		this.perComType = perComType;
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
	public String getStoredValue() {
		return storedValue;
	}
	public void setStoredValue(String storedValue) {
		this.storedValue = storedValue;
	}
	public String getCashTreatment() {
		return cashTreatment;
	}
	public void setCashTreatment(String cashTreatment) {
		this.cashTreatment = cashTreatment;
	}
	public String getCashProducts() {
		return cashProducts;
	}
	public void setCashProducts(String cashProducts) {
		this.cashProducts = cashProducts;
	}
	public String getCardTreatment() {
		return cardTreatment;
	}
	public void setCardTreatment(String cardTreatment) {
		this.cardTreatment = cardTreatment;
	}
	public String getCardProducts() {
		return cardProducts;
	}
	public void setCardProducts(String cardProducts) {
		this.cardProducts = cardProducts;
	}
	public String getDiscountProducts() {
		return discountProducts;
	}
	public void setDiscountProducts(String discountProducts) {
		this.discountProducts = discountProducts;
	}
	public String getHighEndProducts() {
		return highEndProducts;
	}
	public void setHighEndProducts(String highEndProducts) {
		this.highEndProducts = highEndProducts;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	@Override
	public String toString() {
		return "PerformanceCommission [id=" + id + ", storeId=" + storeId + ", perComType=" + perComType + ", lowLimit="
				+ lowLimit + ", highLimit=" + highLimit + ", storedValue=" + storedValue + ", cashTreatment="
				+ cashTreatment + ", cashProducts=" + cashProducts + ", cardTreatment=" + cardTreatment
				+ ", cardProducts=" + cardProducts + ", discountProducts=" + discountProducts + ", highEndProducts="
				+ highEndProducts + ", other=" + other + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerformanceCommission other = (PerformanceCommission) obj;
		if (cardProducts == null) {
			if (other.cardProducts != null)
				return false;
		} else if (!cardProducts.equals(other.cardProducts))
			return false;
		if (cardTreatment == null) {
			if (other.cardTreatment != null)
				return false;
		} else if (!cardTreatment.equals(other.cardTreatment))
			return false;
		if (cashProducts == null) {
			if (other.cashProducts != null)
				return false;
		} else if (!cashProducts.equals(other.cashProducts))
			return false;
		if (cashTreatment == null) {
			if (other.cashTreatment != null)
				return false;
		} else if (!cashTreatment.equals(other.cashTreatment))
			return false;
		if (discountProducts == null) {
			if (other.discountProducts != null)
				return false;
		} else if (!discountProducts.equals(other.discountProducts))
			return false;
		if (highEndProducts == null) {
			if (other.highEndProducts != null)
				return false;
		} else if (!highEndProducts.equals(other.highEndProducts))
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
		if (this.other == null) {
			if (other.other != null)
				return false;
		} else if (!this.other.equals(other.other))
			return false;
		if (perComType == null) {
			if (other.perComType != null)
				return false;
		} else if (!perComType.equals(other.perComType))
			return false;
		if (storeId != other.storeId)
			return false;
		if (storedValue == null) {
			if (other.storedValue != null)
				return false;
		} else if (!storedValue.equals(other.storedValue))
			return false;
		return true;
	}
	// 比较两个对象是否除了 id 其他的属性相同
	public static PerformanceCommission validDuplicate(List<PerformanceCommission> performanceCommissions, PerformanceCommission performanceCommission) {
		PerformanceCommission originPerformanceCommission = null;
		for(PerformanceCommission tempPerformanceCommission : performanceCommissions) {
			if(tempPerformanceCommission.equals(performanceCommission)) {
				originPerformanceCommission = tempPerformanceCommission;
				break;
			}
		}
		return originPerformanceCommission;
	}
}
