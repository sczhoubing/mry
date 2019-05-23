package com.mry.model;

import lombok.Data;

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
@Data
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
}
