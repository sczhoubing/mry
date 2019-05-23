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
@Table(name="performance_commission")
@Data
@EqualsAndHashCode(exclude = "id")
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
