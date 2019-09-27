package com.mry.controller;

import com.mry.enums.DateFormat;
import com.mry.service.EmpSalaryService;
import com.mry.utils.CommonUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/emp/salary")
public class EmpSalaryController {
    @Resource
    private EmpSalaryService empSalaryService;

    @GetMapping("/store/{storeId}")
    public Map<String, Object> getEmpSalaryByStoreId(@PathVariable("storeId")Integer storeId, String month) {
        Map<String, Object> result = new HashMap<>();
        // 如果传递的月份为空，则默认指定当前月份
        if(StringUtils.isEmpty(month)) {
            month = CommonUtils.currentDate(DateFormat.FORMAT7.getFormat());
        }
        result.put("salaryInfo", empSalaryService.getEmpSalaryByStoreId(storeId, month));

        return result;
    }

    @GetMapping("/desc")
    public Map<String, Object> getEmpSalaryByStoreId() {
        Map<String, Object> result = new HashMap<>();
        result.put("salaryDesc", empSalaryService.salaryDescription());
        return result;
    }
}
