package com.mry.param;

import com.mry.model.Salary;
import lombok.Data;

@Data
public class SalaryParam {
	private int storeId;
	private Salary salary;
}
