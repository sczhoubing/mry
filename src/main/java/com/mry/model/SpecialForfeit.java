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
@Table(name="special_forfeit")
@Data
@EqualsAndHashCode(exclude = "id")
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
