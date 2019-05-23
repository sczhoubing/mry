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
@Table(name="practical_commission")
@Data
@EqualsAndHashCode(exclude = "id")
public class PracticalCommission {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="practical_range")
	private String practicalRange;
	@Column(name="low_limit")
	private String lowLimit;
	@Column(name="high_limit")
	private String highLimit;
	@Column(name="face_designated")
	private String faceDesignated;
	@Column(name="nface_designated")
	private String nFaceDesignated;
	@Column(name="body_designated")
	private String bodyDesignated;
	@Column(name="nbody_designated")
	private String nBodyDesignated;

	// 校验两个对象是否除了 id 之外相等
	public static PracticalCommission validDuplicate(List<PracticalCommission> practicalCommissions, PracticalCommission practicalCommission) {
		PracticalCommission originPracticalCommission = null;
		for(PracticalCommission tempPracticalCommission : practicalCommissions) {
			if(tempPracticalCommission.equals(practicalCommission)) {
				originPracticalCommission = tempPracticalCommission;
				break;
			}
		}
		return originPracticalCommission;
	}
}
