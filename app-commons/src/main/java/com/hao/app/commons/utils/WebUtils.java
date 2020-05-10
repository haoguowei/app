package com.hao.app.commons.utils;

import com.hao.app.commons.entity.result.TableKey;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class WebUtils {

    public static BigDecimal getIncomeAmount(int projectId, Map<TableKey, BigDecimal> incomeTable) {
        if (incomeTable == null || incomeTable.isEmpty()) {
            return BigDecimal.valueOf(0);
        }

        TableKey key = new TableKey(projectId);
        BigDecimal value = incomeTable.get(key);
        return value == null ? BigDecimal.valueOf(0) : value;
    }

    public static BigDecimal getCostAmount(int projectId, int type3, Set<Integer> leafIds, Map<TableKey, BigDecimal> costTable) {
        if (costTable == null || costTable.isEmpty()) {
            return BigDecimal.valueOf(0);
        }

        if (type3 > 0) {
            TableKey key = new TableKey(projectId, type3);
            BigDecimal value = costTable.get(key);
            return value == null ? BigDecimal.valueOf(0) : value;
        }

        BigDecimal total = BigDecimal.valueOf(0);
        for (TableKey key : costTable.keySet()) {
            if (key.getProjectId() == projectId && leafIds.contains(key.getType3())) {
                BigDecimal val = costTable.get(key);
                total = total.add(val);
            }
        }
        return total;
    }

}
