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
@Table(name="late_forfeit")
@Data
@EqualsAndHashCode(exclude = "id")
public class LateForfeit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="late_time")
	private String lateTime;
	@Column(name="late_money")
	private String lateMoney;

	// 校验是否除了 id 之外其他的属性相等
	public static LateForfeit validDuplicateLateForfeit(LateForfeit lateForfeit, List<LateForfeit> lateForfeits) {
		LateForfeit duplicateLateForfeit = null;
		for(LateForfeit tempLateForfeit : lateForfeits) {
			if(tempLateForfeit.equals(lateForfeit)) {
				duplicateLateForfeit = tempLateForfeit;
				break;
			}
		}
		return duplicateLateForfeit;
	}
}
