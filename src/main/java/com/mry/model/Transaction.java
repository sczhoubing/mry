package com.mry.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="transaction")
@Data
@EqualsAndHashCode(exclude = "id")
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="user_id")
	private int userId;
	@Column(name="user_name")
	private String username;
	@Column(name="money")
	private String money;
	@Column(name="consumption")
	private String consumption;
	@Column(name="payout_type")
	private String payoutType;
	@Column(name="date")
	private String date;

	// 校验是否有除了id外相同的记录
	public static Transaction validDuplicateTransaction(Transaction originTransaction, List<Transaction> transactionList) {
		for(Transaction transaction : transactionList) {
			if(transaction.equals(originTransaction)) {
				return transaction;
			}
		}
		return null;
	}
}
