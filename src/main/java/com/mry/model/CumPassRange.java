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
@Table(name="cum_pass_range")
@Data
@EqualsAndHashCode(exclude = "id")
public class CumPassRange {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="low_limit")
	private String lowLimit;
	@Column(name="high_limit")
	private String highLimit;
	@Column(name="reward_money")
	private String rewardMoney;
	@Column(name="reward_rule")
	private String rewardRule;

	// 校验是否除了 id 之外其他的属性相等
	public static CumPassRange validDuplicateCumPassRange(CumPassRange cumPassRange, List<CumPassRange> cumPassRanges) {
		CumPassRange duplicateCumPassRange = null;
		for(CumPassRange tempCumPassRange : cumPassRanges) {
			if(tempCumPassRange.equals(cumPassRange)) {
				duplicateCumPassRange = tempCumPassRange;
				break;
			}
		}
		return duplicateCumPassRange;
	}
}
