package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.IncomeQueryParam;
import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.AmountTable;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.TableKey;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.IncomeMapper;
import com.hao.app.pojo.IncomeDO;
import com.hao.app.service.IncomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Resource
    private IncomeMapper incomeMapper;

    @Override
    public Map<TableKey, BigDecimal> getIncomeTable(TableQueryParam param) {
        List<AmountTable> list = incomeMapper.searchIncomeTable(param);
        Map<TableKey, BigDecimal> map = new HashMap<>();
        if (list != null) {
            for (AmountTable table : list) {
                map.put(new TableKey(table.getProjects(), table.getType3()), table.getAmount());
            }
        }
        return map;
    }


    @Override
    public JsonResult<IncomeDO> search(IncomeQueryParam param) {
        int count = incomeMapper.count(param);
        List<IncomeDO> list = incomeMapper.search(param);
        return new JsonResult<>(count, list);
    }

    @Override
    public IncomeDO getById(int id) {
        return incomeMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(IncomeDO cost) {
        int res = incomeMapper.insert(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(IncomeDO cost) {
        IncomeDO old = incomeMapper.selectByPrimaryKey(cost.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = incomeMapper.update(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public Map<TableKey, BigDecimal> getIncomeTable2(TableQueryParam param) {
        Map<TableKey, BigDecimal> map = new HashMap<>();
        List<AmountTable> list = incomeMapper.searchIncomeTable2(param);
        if (list != null) {
            for (AmountTable table : list) {
                map.put(new TableKey(table.getMonths(), table.getType3()), table.getAmount());
            }
        }
        return map;
    }


    @Override
    public Map<TableKey, BigDecimal> getIncomeTableZhuanxiang(TableQueryParam param) {
        Map<TableKey, BigDecimal> map = new HashMap<>();
        List<AmountTable> list = incomeMapper.searchIncomeTableZhuanxiang(param);
        if (list != null) {
            for (AmountTable table : list) {
                map.put(new TableKey(table.getProjects(), table.getMonths()), table.getAmount());
            }
        }
        return map;
    }

    @Override
    public List<AmountTable> getIncomeTableList2(TableQueryParam param) {
        List<AmountTable> list = incomeMapper.searchIncomeTable2(param);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }
}
