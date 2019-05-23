package com.mry.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project_manage")
@Data
@EqualsAndHashCode(exclude = "id")
public class ProjectManage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="user_id")
	private int userId;
	@Column(name="store_id")
	private int storeId;
	@Column(name="project_type")
	private String projectType;
	@Column(name="project_name")
	private String projectName;
	@Column(name="project_price")
	private String projectPrice;
	@Column(name="project_date")
	private String projectDate;
	@Column(name="project_status")
	private String projectStatus;
	@Column(name="symptom")
	private String symptom;

	// 校验是否除了 id 之外其他属性相等的对象 
	public static List<ProjectManage> removeDuplicateProjectManages(List<ProjectManage> originProjectManages, List<ProjectManage> targetProjectManages) {
		List<ProjectManage> resultProjectManages = new ArrayList<ProjectManage>();
		resultProjectManages.addAll(targetProjectManages);
		for(ProjectManage originProjectManage : originProjectManages) {
			for(ProjectManage targetProjectManage : targetProjectManages) {
				if(originProjectManage.equals(targetProjectManage)) {
					resultProjectManages.remove(targetProjectManage);
				}
			}
		}
		return resultProjectManages;
	}
}
