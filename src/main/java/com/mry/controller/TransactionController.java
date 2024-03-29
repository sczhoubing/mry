package com.mry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.mry.utils.CommonUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mry.model.Transaction;
import com.mry.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Resource
	private TransactionService transactionService;
	
	@PostMapping("/add")
	public Map<String, Object> addTransaction(@RequestBody Transaction transaction) {
		Map<String, Object> result = new HashMap<>();
		result.put("msg", transactionService.addTransaction(transaction));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editTransaction(@RequestBody Transaction transaction) { 
		Map<String, Object> result = new HashMap<>();
		result.put("msg", transactionService.editTransaction(transaction));
		return result;
	}

	@GetMapping("/id/{id}")
	public Map<String, Object> getTransaction(@PathVariable("id") int id) {
		Map<String, Object> result = new HashMap<>();
		result.put("transactionInfo", transactionService.getTransactionById(id));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getTransaction(@PathVariable("storeId")Integer storeId, Integer userId, Integer pageNum, Integer pageSize) {
		Map<String, Object> result = new HashMap<>();
		Pageable pageable = CommonUtils.initPage(pageNum, pageSize);
		if(null != userId) {
			result.put("transactionInfo", transactionService.getTransactionByUserId(pageable, storeId, userId));
		} else {
			result.put("transactionInfo", transactionService.getTransactionByStoreId(pageable, storeId));
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteTransaction(@PathVariable("storeId")Integer storeId, Integer userId) {
		Map<String, Object> result = new HashMap<>();
		if(null != userId) {
			result.put("msg", transactionService.deleteTransactionByUserId(storeId, userId));
		} else {
			result.put("msg", transactionService.deleteTransactionByStoreId(storeId));
		}
		return result;
	}
}
