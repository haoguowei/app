package com.hao.app.commons.utils;

import com.hao.app.commons.entity.result.TableKey;
import org.apache.commons.lang.StringUtils;

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
                return zhanbi2Str(leafIds, costTable, incomeTable);
            }

            //利润率
            if (type3 == -103) {
                return lirong2Str(leafIds, costTable, incomeTable);
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
//                BigDecimal bg = zhanbi(projectId, leafIds, costTable, incomeTable);
//                return bg.toString() + "%";
                return zhanbiStr(projectId, leafIds, costTable, incomeTable);
            }

            //利润率
            if (type3 == -103) {
                return lirongStr(projectId, leafIds, costTable, incomeTable);
            }
        }

        return "0";
    }


    private static String lirongStr(int projectId, Set<Integer> leafIds, Map<TableKey, BigDecimal> costTable, Map<TableKey, BigDecimal> incomeTable) {
        BigDecimal cost = totalAmount(projectId, leafIds, costTable);
        BigDecimal income = getIncomeAmount(projectId, incomeTable);
        if (income.doubleValue() == 0D) {
            return "";
        }

        BigDecimal zhanbi = cost.multiply(BigDecimal.valueOf(100.0)).divide(income, 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal val = BigDecimal.valueOf(100).subtract(zhanbi);
        return val.toString() + "%";

    }

    private static String zhanbiStr(int projectId, Set<Integer> leafIds, Map<TableKey, BigDecimal> costTable, Map<TableKey, BigDecimal> incomeTable) {
        BigDecimal cost = totalAmount(projectId, leafIds, costTable);
        BigDecimal income = getIncomeAmount(projectId, incomeTable);
        if (income.doubleValue() == 0D) {
            return "";
        }

        return cost.multiply(BigDecimal.valueOf(100.0)).divide(income, 2, BigDecimal.ROUND_HALF_UP).toString() + "%";

    }

    private static String zhanbi2Str(Set<Integer> leafIds, Map<TableKey, BigDecimal> costTable, Map<TableKey, BigDecimal> incomeTable) {
        BigDecimal cost = totalAmount2(leafIds, costTable);
        BigDecimal income = getIncomeAmount(0, incomeTable);
        if (income.doubleValue() == 0D) {
            return "";
        }

        BigDecimal zhanbi = cost.multiply(BigDecimal.valueOf(100.0)).divide(income, 2, BigDecimal.ROUND_HALF_UP);
        return zhanbi.toString() + "%";
    }

    private static String lirong2Str(Set<Integer> leafIds, Map<TableKey, BigDecimal> costTable, Map<TableKey, BigDecimal> incomeTable) {
        BigDecimal cost = totalAmount2(leafIds, costTable);
        BigDecimal income = getIncomeAmount(0, incomeTable);
        if (income.doubleValue() == 0D) {
            return "";
        }

        BigDecimal zhanbi = cost.multiply(BigDecimal.valueOf(100.0)).divide(income, 2, BigDecimal.ROUND_HALF_UP);

        BigDecimal val = BigDecimal.valueOf(100).subtract(zhanbi);
        return val.toString() + "%";
    }

    public static String fmtBigDecimal(BigDecimal v) {
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

    public static String getMonthName(int val) {
        if (val == 0) {
            return "合计";
        }

        String v = String.valueOf(val);
        return v.substring(0, 4) + "年" + v.substring(5, 6) + "月";
    }

    public static BigDecimal getMshouru(int month, Map<TableKey, BigDecimal> incomeTable) {
        if (incomeTable == null) {
            incomeTable = new HashMap<>();
        }

        if (month == 0) {
            BigDecimal total = BigDecimal.valueOf(0);
            for (TableKey key : incomeTable.keySet()) {
                BigDecimal val = incomeTable.get(key);
                total = total.add(val);
            }
            return total;
        } else {
            TableKey key = new TableKey(month);
            BigDecimal value = incomeTable.get(key);
            return value == null ? BigDecimal.valueOf(0) : value;
        }
    }

    public static String getZhanbi(int month, String tmp, Map<TableKey, BigDecimal> incomeTable) {
        if (StringUtils.isBlank(tmp) || tmp.contains("%")) {
            return "";
        }

        BigDecimal total = getMshouru(month, incomeTable);
        BigDecimal tmpv = BigDecimal.valueOf(Double.valueOf(tmp));
        if (total.doubleValue() == 0D) {
            return "";
        }
        return tmpv.multiply(BigDecimal.valueOf(100.0)).divide(total, 2, BigDecimal.ROUND_HALF_UP).toString() + "%";
    }

    public static BigDecimal getZhuanxiangAmount(int projectId, int month, Map<TableKey, BigDecimal> costTable) {
        if (costTable == null) {
            costTable = new HashMap<>();
        }

        if (projectId == 0 && month == 0) {
            BigDecimal total = BigDecimal.valueOf(0);
            for (TableKey key : costTable.keySet()) {
                total = total.add(costTable.get(key));
            }
            return total;
        }

        if (projectId == 0) {
            BigDecimal total = BigDecimal.valueOf(0);
            for (TableKey key : costTable.keySet()) {
                if (key.getType3() == month) {
                    total = total.add(costTable.get(key));
                }
            }
            return total;
        }
        if (month == 0) {
            BigDecimal total = BigDecimal.valueOf(0);
            for (TableKey key : costTable.keySet()) {
                if (key.getProjectId() == projectId) {
                    total = total.add(costTable.get(key));
                }
            }
            return total;
        }

        BigDecimal bigDecimal = costTable.get(new TableKey(projectId, month));
        if (bigDecimal == null) {
            return BigDecimal.valueOf(0);
        } else {
            return bigDecimal;
        }
    }

}
