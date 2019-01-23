package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="late_forfeit")
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
	public String getLateTime() {
		return lateTime;
	}
	public void setLateTime(String lateTime) {
		this.lateTime = lateTime;
	}
	public String getLateMoney() {
		return lateMoney;
	}
	public void setLateMoney(String lateMoney) {
		this.lateMoney = lateMoney;
	}
	@Override
	public String toString() {
		return "LateForfeit [id=" + id + ", storeId=" + storeId + ", lateTime=" + lateTime + ", lateMoney=" + lateMoney
				+ "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LateForfeit other = (LateForfeit) obj;
		/*if (id != other.id)
			return false;*/
		if (lateMoney == null) {
			if (other.lateMoney != null)
				return false;
		} else if (!lateMoney.equals(other.lateMoney))
			return false;
		if (lateTime == null) {
			if (other.lateTime != null)
				return false;
		} else if (!lateTime.equals(other.lateTime))
			return false;
		if (storeId != other.storeId)
			return false;
		return true;
	}
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
