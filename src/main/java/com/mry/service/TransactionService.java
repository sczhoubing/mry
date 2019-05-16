package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.Transaction;
import com.mry.repository.TransactionRepository;

@Service
@Transactional
public class TransactionService {
	@Resource
	private TransactionRepository transactionRepository;
	
	// 添加一条交易记录
	public int addTransaction(Transaction transaction) {
		Transaction tempTransaction = transactionRepository.getTransactionByUserId(transaction.getStoreId(), transaction.getUserId());
		// 如果已经存在相同的记录
		if(null != tempTransaction) {
			transaction.setId(tempTransaction.getId());
		}
		transactionRepository.save(transaction);
		return transaction.getStoreId();
	}
	
	// 编辑一条交易记录
	public int editTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
		return transaction.getId();
	}
	
	// 根据 storeId 返回一组交易记录
	public List<Transaction> getTransactionByStoreId(int storeId) {
		return transactionRepository.getTransactionByStoreId(storeId);
	}
	
	// 根据 storeId + userId 返回一条交易记录
	public Transaction getTransactionByUserId(int storeId, int userId) {
		return transactionRepository.getTransactionByUserId(storeId, userId);
	}
	
	// 根据 storeId 删除一组交易记录
	public int deleteTransactionByStoreId(int storeId) {
		return transactionRepository.deleteTransactionByStoreId(storeId);
	}

	// 根据 storeId + userId 删除一条交易记录
	public int deleteTransactionByUserId(int storeId, int userId) {
		return transactionRepository.deleteTransactionByUserId(storeId, userId);
	}
}
