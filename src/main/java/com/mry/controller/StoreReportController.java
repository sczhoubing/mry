package com.mry.controller;

import com.mry.service.StoreReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/store/report")
public class StoreReportController {
    @Resource
    private StoreReportService storeReportService;

    @GetMapping("/moth/{storeId}")
    public Map<String, Object> storeMonthReport(@PathVariable("storeId")Integer storeId, String moth) {
        Map<String, Object> result = new HashMap<>();
        result.put("storeMothReport", storeReportService.storeMonthReport(storeId, moth));
        return result;
    }

    @GetMapping("/technician/{storeId}")
    public Map<String, Object> technicianMothReport(@PathVariable("storeId")Integer storeId, String moth, String name) {
        Map<String, Object> result = new HashMap<>();
        result.put("technicianMothReport", storeReportService.technicianMothReport(storeId, moth, name));
        return result;
    }

    @GetMapping("/daily/{storeId}")
    public Map<String, Object> storeDailyReport(@PathVariable("storeId")Integer storeId, String date) {
        Map<String, Object> result = new HashMap<>();
        result.put("storeDailyReport", storeReportService.storeDailyReport(storeId, date));
        return result;
    }
}
