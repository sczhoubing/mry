package com.mry.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="practical_commission")
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
	public String getPracticalRange() {
		return practicalRange;
	}
	public void setPracticalRange(String practicalRange) {
		this.practicalRange = practicalRange;
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
	public String getFaceDesignated() {
		return faceDesignated;
	}
	public void setFaceDesignated(String faceDesignated) {
		this.faceDesignated = faceDesignated;
	}
	public String getnFaceDesignated() {
		return nFaceDesignated;
	}
	public void setnFaceDesignated(String nFaceDesignated) {
		this.nFaceDesignated = nFaceDesignated;
	}
	public String getBodyDesignated() {
		return bodyDesignated;
	}
	public void setBodyDesignated(String bodyDesignated) {
		this.bodyDesignated = bodyDesignated;
	}
	public String getnBodyDesignated() {
		return nBodyDesignated;
	}
	public void setnBodyDesignated(String nBodyDesignated) {
		this.nBodyDesignated = nBodyDesignated;
	}
	@Override
	public String toString() {
		return "PracticalCommission [id=" + id + ", storeId=" + storeId + ", practicalRange=" + practicalRange
				+ ", lowLimit=" + lowLimit + ", highLimit=" + highLimit + ", faceDesignated=" + faceDesignated
				+ ", nFaceDesignated=" + nFaceDesignated + ", bodyDesignated=" + bodyDesignated + ", nBodyDesignated="
				+ nBodyDesignated + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PracticalCommission other = (PracticalCommission) obj;
		if (bodyDesignated == null) {
			if (other.bodyDesignated != null)
				return false;
		} else if (!bodyDesignated.equals(other.bodyDesignated))
			return false;
		if (faceDesignated == null) {
			if (other.faceDesignated != null)
				return false;
		} else if (!faceDesignated.equals(other.faceDesignated))
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
		if (nBodyDesignated == null) {
			if (other.nBodyDesignated != null)
				return false;
		} else if (!nBodyDesignated.equals(other.nBodyDesignated))
			return false;
		if (nFaceDesignated == null) {
			if (other.nFaceDesignated != null)
				return false;
		} else if (!nFaceDesignated.equals(other.nFaceDesignated))
			return false;
		if (practicalRange == null) {
			if (other.practicalRange != null)
				return false;
		} else if (!practicalRange.equals(other.practicalRange))
			return false;
		if (storeId != other.storeId)
			return false;
		return true;
	}
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
