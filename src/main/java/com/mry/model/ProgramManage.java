package com.mry.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="program_manage")
public class ProgramManage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="symptom")
	private String symptom;
	@Column(name="program_type")
	private int programType;
	@Column(name="program_name")
	private String programName;
	@Column(name="program_price")
	private String programPrice;
	@Transient
	private List<ProgramItem> programItems;
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
	public int getProgramType() {
		return programType;
	}
	public void setProgramType(int programType) {
		this.programType = programType;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getProgramPrice() {
		return programPrice;
	}
	public void setProgramPrice(String programPrice) {
		this.programPrice = programPrice;
	}
	public List<ProgramItem> getProgramItems() {
		return programItems;
	}
	public void setProgramItems(List<ProgramItem> programItems) {
		this.programItems = programItems;
	}
	// 将项目与方案关联
	public List<ProgramItem> setProgramIdAndType() {
		for(ProgramItem programItem : this.programItems) {
			programItem.setProgramId(this.id);
			programItem.setProgramType(this.programType);
		}
		return this.programItems;
	}
	// 将项目与方案绑定
	public static List<ProgramManage> bindProgramItem(List<ProgramManage> programManages, List<ProgramItem> programItems) {
		for (ProgramManage programManage : programManages) {
			List<ProgramItem> items = new ArrayList<ProgramItem>();
			for (ProgramItem programItem : programItems) {
				if (programManage.getId() == programItem.getProgramId()) {
					items.add(programItem);
				}
			}
			programManage.setProgramItems(items);
		}
		return programManages;
	}
	@Override
	public String toString() {
		return "ProgramManage [id=" + id + ", storeId=" + storeId + ", symptom=" + symptom + ", programType="
				+ programType + ", programName=" + programName + ", programPrice=" + programPrice + ", programItems="
				+ programItems + "]";
	}
}
