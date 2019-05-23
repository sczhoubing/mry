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
@Table(name="manual_fee")
@Data
@EqualsAndHashCode(exclude = "id")
public class ManualFee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="project_fee")
	private String projectFee;
	@Column(name="classify_fee")
	private String classifyFee;
	@Column(name="face_fee")
	private String faceFee;
	@Column(name="body_part_fee")
	private String bodyPartFee;
	@Column(name="body_whole_fee")
	private String bodyWholeFee;
	@Column(name="photodiode_fee")
	private String photodiodeFee;
	@Column(name="embroidery_fee")
	private String embroideryFee;

	// 校验是否有除 id 之外相等的手工费用信息
	public static ManualFee validDuplicateManualFee(ManualFee manualFee, List<ManualFee> manualFees) {
		ManualFee duplicateManualFee = null;
		for(ManualFee tempManualFee : manualFees) {
			if(tempManualFee.equals(manualFee)) {
				duplicateManualFee = tempManualFee;
				break;
			}
		}
		return duplicateManualFee;
	}
}
