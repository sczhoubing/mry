package com.mry.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.config.SmsSetting;
import com.mry.enums.DateFormat;
import com.mry.model.Customer;
import com.mry.model.Employee;
import com.mry.repository.CustomerRepository;
import com.mry.repository.EmployeeRepository;
import com.mry.sms.SendSms;

@Service
@Transactional
public class EmployeeService {
	@Resource
	private EmployeeRepository employeeRepository;
	@Resource
	private CustomerRepository customerRepository;
	@Resource
	private SmsSetting smsSetting;
	@Resource
	private SendSms sendSms;
	
	// 添加一条员工信息
	public int addEmployeeInfo(Employee employee) {
		Employee originEmployee = employeeRepository.getEmployeeByIdCard(employee.getStoreId(), employee.getIdCard());
		// 员工已经存在的话将会覆盖
		if(null != originEmployee) {
			employee.setId(originEmployee.getId());
		}
		employee.setStartTime(DateFormat.FORMAT3.getFormat());
		employee.setEndTime(DateFormat.FORMAT3.getFormat());
		employeeRepository.save(employee);

		// 同时需要添加到 customer 表，用户才可以登陆
		String account = employee.getPhoneNum();
		Customer originCustomer = customerRepository.getCustomerByAccount(account);
		Customer customer = new Customer();
		if(null != originCustomer) {
			customer.setId(originCustomer.getId());
		}
		customer.setAccount(account);
		// 初始密码是手机号码的后 6 位
		String password = account.substring(5, account.length());
		customer.setPassword(password);
		customer.setUserName(account);
		customer.setStaffName(employee.getEmpName());
		customer.setRole(employee.getEmpType());
		// 状态码为 2 表示用户处于临时状态，需要修改账户和密码
		customer.setStatus("2");
		customerRepository.save(customer);
		// 发送临时账户和密码给用户，提醒修改账户信息
		String message = "{\"userName\":\"" + account + "\",\"password\":\"" + password + "\"}";
		sendSms.sendSms(account, smsSetting.getRemindMsg(), message);
		return employee.getStoreId();
	}
	
	// 修改一条员工信息
	public int editEmployee(Employee employee) {
		employee.setStartTime(DateFormat.FORMAT3.getFormat());
		employee.setEndTime(DateFormat.FORMAT3.getFormat());
		employeeRepository.save(employee);
		return employee.getId();
	}
	
	// 根据 empName 查询员工信息
	public List<Employee> getEmployeeByEmpName(int storeId, String empName) {
		return employeeRepository.getEmployeeByEmpName(storeId, empName);
	}
	
	// 根据员工 id 查询一条员工信息
	public Employee getEmployeeById(int storeId, int id) {
		return employeeRepository.getEmployeeById(storeId, id);
	} 
	
	// 根据 storeId 查询门店下所有员工信息
	public List<Employee> getEmployeeByStoreId(int storeId) {
		return employeeRepository.getEmployeeByStoreId(storeId);
	}
	
	// 根据 storeId + startTime + endTime 返回不在该时间段的员工信息
	public List<Employee> getEmployeeByStartTimeAndEndTime(int storeId, String startTime, String endTime) {
		return employeeRepository.getEmployeeByStartTimeAndEndTime(storeId, startTime, endTime);
	}
	
	// 根据 idCard 删除一条员工信息
	public int deleteEmployeeByIdCard(int storeId, String idCard) {
		Employee employee = employeeRepository.getEmployeeByIdCard(storeId, idCard);
		int delEmp = 0, delCus = 0;
		if(null != employee) {
			delCus = customerRepository.deleteCustomerByAccount(employee.getPhoneNum());
			delEmp = employeeRepository.deleteEmployeeByIdCard(storeId, idCard);
		}
		return delCus + delEmp;
	}
	
	// 根据 storeId 删除门店下所有员工信息
	public int deleteEmployeeByStoreId(int storeId) {
		List<Employee> employees = employeeRepository.getEmployeeByStoreId(storeId);
		List<String> accounts = new ArrayList<String>();
		for(Employee employee : employees) {
			accounts.add(employee.getPhoneNum());
		}
		int delCus = customerRepository.deleteCustomer(accounts);
		int delEmp = employeeRepository.deleteEmployeeBystoreId(storeId);
		return delCus + delEmp;
	}
	
	// 标记员工状态
	public int markEmpStatus(int storeId, int id, String account, String status) {
		// 当状态等于 5 的时候表示离职状态
		if(status.equals("5")) {
			customerRepository.lockCustomerLoginAccess(account);
		}
		employeeRepository.editEmployeeStatusById(storeId, id, status);
		return id;
	}
}
