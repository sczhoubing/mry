package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member_manage")
public class MemMange {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="job_title")
	private String jobTitle;
	@Column(name="resp_content")
	private String respCont;
	@Column(name="workflow")
	private String workflow;
	@Column(name="post_grade")
	private String postGrade;
	@Column(name="level_type")
	private String levelType;
	@Column(name="level_num")
	private String levelNum;
	@Column(name="mem_rank")
	private String memRank;
	@Column(name="mem_rank_cash")
	private String memRankCash;
	@Column(name="mem_rank_pflow")
	private String memRankPflow;
	@Column(name="mem_rank_pexer")
	private String memRankPexer;
	@Column(name="mem_rank_prod")
	private String memRankProd;
	@Column(name="mem_rank_cons")
	private String memRankCons;
	@Column(name="dvidend_mange")
	private String dvidendMange;
	@Column(name="prim_stock")
	private String primStock;
	@Column(name="prim_stock_money")
	private String primStockMoney;
	@Column(name="real_stock")
	private String realStock;
	@Column(name="real_stock_money")
	private String realStockMoney;
	@Column(name="dry_stock")
	private String dryStock;
	@Column(name="dry_stock_money")
	private String dryStockMoney;
	@Column(name="bon_stock")
	private String bonStock;
	@Column(name="bon_stock_money")
	private String bonStockMoney;
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
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getRespCont() {
		return respCont;
	}
	public void setRespCont(String respCont) {
		this.respCont = respCont;
	}
	public String getWorkflow() {
		return workflow;
	}
	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
	public String getPostGrade() {
		return postGrade;
	}
	public void setPostGrade(String postGrade) {
		this.postGrade = postGrade;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public String getLevelNum() {
		return levelNum;
	}
	public void setLevelNum(String levelNum) {
		this.levelNum = levelNum;
	}
	public String getMemRank() {
		return memRank;
	}
	public void setMemRank(String memRank) {
		this.memRank = memRank;
	}
	public String getMemRankCash() {
		return memRankCash;
	}
	public void setMemRankCash(String memRankCash) {
		this.memRankCash = memRankCash;
	}
	public String getMemRankPflow() {
		return memRankPflow;
	}
	public void setMemRankPflow(String memRankPflow) {
		this.memRankPflow = memRankPflow;
	}
	public String getMemRankPexer() {
		return memRankPexer;
	}
	public void setMemRankPexer(String memRankPexer) {
		this.memRankPexer = memRankPexer;
	}
	public String getMemRankProd() {
		return memRankProd;
	}
	public void setMemRankProd(String memRankProd) {
		this.memRankProd = memRankProd;
	}
	public String getMemRankCons() {
		return memRankCons;
	}
	public void setMemRankCons(String memRankCons) {
		this.memRankCons = memRankCons;
	}
	public String getDvidendMange() {
		return dvidendMange;
	}
	public void setDvidendMange(String dvidendMange) {
		this.dvidendMange = dvidendMange;
	}
	public String getPrimStock() {
		return primStock;
	}
	public void setPrimStock(String primStock) {
		this.primStock = primStock;
	}
	public String getPrimStockMoney() {
		return primStockMoney;
	}
	public void setPrimStockMoney(String primStockMoney) {
		this.primStockMoney = primStockMoney;
	}
	public String getRealStock() {
		return realStock;
	}
	public void setRealStock(String realStock) {
		this.realStock = realStock;
	}
	public String getRealStockMoney() {
		return realStockMoney;
	}
	public void setRealStockMoney(String realStockMoney) {
		this.realStockMoney = realStockMoney;
	}
	public String getDryStock() {
		return dryStock;
	}
	public void setDryStock(String dryStock) {
		this.dryStock = dryStock;
	}
	public String getDryStockMoney() {
		return dryStockMoney;
	}
	public void setDryStockMoney(String dryStockMoney) {
		this.dryStockMoney = dryStockMoney;
	}
	public String getBonStock() {
		return bonStock;
	}
	public void setBonStock(String bonStock) {
		this.bonStock = bonStock;
	}
	public String getBonStockMoney() {
		return bonStockMoney;
	}
	public void setBonStockMoney(String bonStockMoney) {
		this.bonStockMoney = bonStockMoney;
	}
	@Override
	public String toString() {
		return "MemMange [id=" + id + ", storeId=" + storeId + ", jobTitle=" + jobTitle + ", respCont=" + respCont
				+ ", workflow=" + workflow + ", postGrade=" + postGrade + ", levelType=" + levelType + ", levelNum="
				+ levelNum + ", memRank=" + memRank + ", memRankCash=" + memRankCash + ", memRankPflow=" + memRankPflow
				+ ", memRankPexer=" + memRankPexer + ", memRankProd=" + memRankProd + ", memRankCons=" + memRankCons
				+ ", dvidendMange=" + dvidendMange + ", primStock=" + primStock + ", primStockMoney=" + primStockMoney
				+ ", realStock=" + realStock + ", realStockMoney=" + realStockMoney + ", dryStock=" + dryStock
				+ ", dryStockMoney=" + dryStockMoney + ", bonStock=" + bonStock + ", bonStockMoney=" + bonStockMoney
				+ "]";
	}
}
