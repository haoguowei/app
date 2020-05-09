package com.hao.app.commons.utils;

import com.hao.app.commons.entity.result.TableKey;

import java.math.BigDecimal;
import java.util.Map;

public class WebUtils {

    public static BigDecimal getIncomeAmount(int projectId, Map<TableKey, BigDecimal> incomeTable) {
        if (incomeTable == null || incomeTable.isEmpty()) {
            return BigDecimal.valueOf(0);
        }

        TableKey key = new TableKey(projectId);
        BigDecimal value = incomeTable.get(key);
        return value == null ? BigDecimal.valueOf(0) : value;
    }

    public static BigDecimal getCostAmount(int projectId, int type3, Map<TableKey, BigDecimal> costTable) {
        if (costTable == null || costTable.isEmpty()) {
            return BigDecimal.valueOf(0);
        }

        TableKey key = new TableKey(projectId, type3);
        BigDecimal value = costTable.get(key);
        return value == null ? BigDecimal.valueOf(0) : value;
    }

}
