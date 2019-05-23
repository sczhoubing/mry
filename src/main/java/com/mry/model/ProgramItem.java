package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="program_item")
@Data
public class ProgramItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="program_id")
	private int programId;
	@Column(name="program_type")
	private int programType;
	@Column(name="item_order")
	private int itemOrder;
	@Column(name="item_name")
	private String itemName;
	@Column(name="item_price")
	private String itemPrice;
	@Column(name="item_interval")
	private String itemInterval;
}
