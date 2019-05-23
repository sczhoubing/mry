package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member_manage")
@Data
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
}
