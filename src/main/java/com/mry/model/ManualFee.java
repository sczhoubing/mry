package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="manual_fee")
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
	public String getProjectFee() {
		return projectFee;
	}
	public void setProjectFee(String projectFee) {
		this.projectFee = projectFee;
	}
	public String getClassifyFee() {
		return classifyFee;
	}
	public void setClassifyFee(String classifyFee) {
		this.classifyFee = classifyFee;
	}
	public String getFaceFee() {
		return faceFee;
	}
	public void setFaceFee(String faceFee) {
		this.faceFee = faceFee;
	}
	public String getBodyPartFee() {
		return bodyPartFee;
	}
	public void setBodyPartFee(String bodyPartFee) {
		this.bodyPartFee = bodyPartFee;
	}
	public String getBodyWholeFee() {
		return bodyWholeFee;
	}
	public void setBodyWholeFee(String bodyWholeFee) {
		this.bodyWholeFee = bodyWholeFee;
	}
	public String getPhotodiodeFee() {
		return photodiodeFee;
	}
	public void setPhotodiodeFee(String photodiodeFee) {
		this.photodiodeFee = photodiodeFee;
	}
	public String getEmbroideryFee() {
		return embroideryFee;
	}
	public void setEmbroideryFee(String embroideryFee) {
		this.embroideryFee = embroideryFee;
	}
	@Override
	public String toString() {
		return "ManualFee [id=" + id + ", storeId=" + storeId + ", projectFee=" + projectFee + ", classifyFee="
				+ classifyFee + ", faceFee=" + faceFee + ", bodyPartFee=" + bodyPartFee + ", bodyWholeFee="
				+ bodyWholeFee + ", photodiodeFee=" + photodiodeFee + ", embroideryFee=" + embroideryFee + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManualFee other = (ManualFee) obj;
		if (embroideryFee == null) {
			if (other.embroideryFee != null)
				return false;
		} else if (!embroideryFee.equals(other.embroideryFee))
			return false;
		if (bodyPartFee == null) {
			if (other.bodyPartFee != null)
				return false;
		} else if (!bodyPartFee.equals(other.bodyPartFee))
			return false;
		if (bodyWholeFee == null) {
			if (other.bodyWholeFee != null)
				return false;
		} else if (!bodyWholeFee.equals(other.bodyWholeFee))
			return false;
		if (classifyFee == null) {
			if (other.classifyFee != null)
				return false;
		} else if (!classifyFee.equals(other.classifyFee))
			return false;
		if (faceFee == null) {
			if (other.faceFee != null)
				return false;
		} else if (!faceFee.equals(other.faceFee))
			return false;
		/*if (id != other.id)
			return false;*/
		if (photodiodeFee == null) {
			if (other.photodiodeFee != null)
				return false;
		} else if (!photodiodeFee.equals(other.photodiodeFee))
			return false;
		if (projectFee == null) {
			if (other.projectFee != null)
				return false;
		} else if (!projectFee.equals(other.projectFee))
			return false;
		if (storeId != other.storeId)
			return false;
		return true;
	}
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
