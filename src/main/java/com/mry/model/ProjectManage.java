package com.mry.model;

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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectPrice() {
		return projectPrice;
	}
	public void setProjectPrice(String projectPrice) {
		this.projectPrice = projectPrice;
	}
	public String getProjectDate() {
		return projectDate;
	}
	public void setProjectDate(String projectDate) {
		this.projectDate = projectDate;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectManage other = (ProjectManage) obj;
		/*if (id != other.id)
			return false;*/
		if (projectDate == null) {
			if (other.projectDate != null)
				return false;
		} else if (!projectDate.equals(other.projectDate))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (projectPrice == null) {
			if (other.projectPrice != null)
				return false;
		} else if (!projectPrice.equals(other.projectPrice))
			return false;
		if (projectStatus == null) {
			if (other.projectStatus != null)
				return false;
		} else if (!projectStatus.equals(other.projectStatus))
			return false;
		if (projectType == null) {
			if (other.projectType != null)
				return false;
		} else if (!projectType.equals(other.projectType))
			return false;
		if (storeId != other.storeId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
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
	
	@Override
	public String toString() {
		return "ProjectManage [id=" + id + ", userId=" + userId + ", storeId=" + storeId + ", projectType="
				+ projectType + ", projectName=" + projectName + ", projectPrice=" + projectPrice + ", projectDate="
				+ projectDate + ", projectStatus=" + projectStatus + "]";
	}
}
