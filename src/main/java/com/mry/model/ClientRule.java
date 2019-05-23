package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_rule")
@Data
public class ClientRule {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="rule_one")
	private String ruleOne;
	@Column(name="rule_two")
	private String ruleTwo;
	@Column(name="rule_three")
	private String ruleThree;
	@Column(name="rule_four")
	private String ruleFour;
}
