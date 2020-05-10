package com.hao.app.commons.utils;

import com.hao.app.commons.entity.result.TableKey;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WebUtils {

    public static BigDecimal getIncomeAmount(int projectId, Map<TableKey, BigDecimal> incomeTable) {
        if (incomeTable == null) {
            incomeTable = new HashMap<>();
        }

        if (projectId == 0) {
            BigDecimal total = BigDecimal.valueOf(0);
            for (TableKey key : incomeTable.keySet()) {
                BigDecimal val = incomeTable.get(key);
                total = total.add(val);
            }
            return total;
        } else {
            TableKey key = new TableKey(projectId);
            BigDecimal value = incomeTable.get(key);
            return value == null ? BigDecimal.valueOf(0) : value;
        }
    }

    public static String getCostAmount(int projectId, int type3, Set<Integer> leafIds, Map<TableKey, BigDecimal> costTable, Map<TableKey, BigDecimal> incomeTable) {
        if (costTable == null) {
            costTable = new HashMap<>();
        }

        if (projectId == 0) {
            if (type3 > 0) {
                BigDecimal total = BigDecimal.valueOf(0);
                for (TableKey key : costTable.keySet()) {
                    if (key.getType3() == type3) {
                        BigDecimal val = costTable.get(key);
                        total = total.add(val);
                    }

                }
                return fmtBigDecimal(total);
            }

            //费用合计
            if (type3 == -1 || type3 == -101) {
                BigDecimal total = totalAmount2(leafIds, costTable);
                return fmtBigDecimal(total);
            }

            //费用占比
            if (type3 == -2 || type3 == -102) {
                BigDecimal bg = zhanbi2(leafIds, costTable, incomeTable);
                return bg.toString() + "%";
            }

            //利润率
            if (type3 == -103) {
                BigDecimal bg = zhanbi2(leafIds, costTable, incomeTable);
                BigDecimal val = BigDecimal.valueOf(100).subtract(bg);
                return fmtBigDecimal(val) + "%";
            }
        } else {
            if (type3 > 0) {
                TableKey key = new TableKey(projectId, type3);
                BigDecimal value = costTable.get(key);
                return fmtBigDecimal(value);
            }
            //费用合计
            if (type3 == -1 || type3 == -101) {
                BigDecimal total = totalAmount(projectId, leafIds, costTable);
                return fmtBigDecimal(total);
            }

            //费用占比
            if (type3 == -2 || type3 == -102) {
                BigDecimal bg = zhanbi(projectId, leafIds, costTable, incomeTable);
                return bg.toString() + "%";
            }

            //利润率
            if (type3 == -103) {
                BigDecimal bg = zhanbi(projectId, leafIds, costTable, incomeTable);
                BigDecimal val = BigDecimal.valueOf(100).subtract(bg);
                return fmtBigDecimal(val) + "%";
            }
        }

        return "0";
    }

    private static BigDecimal zhanbi(int projectId, Set<Integer> leafIds, Map<TableKey, BigDecimal> costTable, Map<TableKey, BigDecimal> incomeTable) {
        BigDecimal cost = totalAmount(projectId, leafIds, costTable);
        BigDecimal income = getIncomeAmount(projectId, incomeTable);
        if (income.doubleValue() == 0D) {
            return BigDecimal.valueOf(100);
        }

        return cost.multiply(BigDecimal.valueOf(100.0)).divide(income, 2, BigDecimal.ROUND_HALF_UP);

    }

    private static BigDecimal zhanbi2(Set<Integer> leafIds, Map<TableKey, BigDecimal> costTable, Map<TableKey, BigDecimal> incomeTable) {
        BigDecimal cost = totalAmount2(leafIds, costTable);
        BigDecimal income = getIncomeAmount(0, incomeTable);
        if (income.doubleValue() == 0D) {
            return BigDecimal.valueOf(100);
        }

        return cost.multiply(BigDecimal.valueOf(100.0)).divide(income, 2, BigDecimal.ROUND_HALF_UP);

    }

    private static String fmtBigDecimal(BigDecimal v) {
        if (v == null) {
            return "0";
        }
        return v.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    private static BigDecimal totalAmount(int projectId, Set<Integer> leafIds, Map<TableKey, BigDecimal> costTable) {
        BigDecimal total = BigDecimal.valueOf(0);
        for (TableKey key : costTable.keySet()) {
            if (key.getProjectId() == projectId && leafIds.contains(key.getType3())) {
                BigDecimal val = costTable.get(key);
                total = total.add(val);
            }
        }
        return total;
    }

    private static BigDecimal totalAmount2(Set<Integer> leafIds, Map<TableKey, BigDecimal> costTable) {
        BigDecimal total = BigDecimal.valueOf(0);
        for (TableKey key : costTable.keySet()) {
            if (leafIds.contains(key.getType3())) {
                BigDecimal val = costTable.get(key);
                total = total.add(val);
            }
        }
        return total;
    }

}
