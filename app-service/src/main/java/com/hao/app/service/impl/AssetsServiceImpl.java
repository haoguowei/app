package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.AssetsQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.AssetsMapper;
import com.hao.app.pojo.AssetsDO;
import com.hao.app.service.AssetsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AssetsServiceImpl implements AssetsService {

    @Resource
    private AssetsMapper assetsMapper;


    @Override
    public JsonResult<AssetsDO> searchAssets(AssetsQueryParam param) {
        int count = assetsMapper.count(param);
        List<AssetsDO> list = assetsMapper.search(param);

        if (list != null) {

            AtomicInteger qTotal = new AtomicInteger();
            AtomicInteger qqTotal = new AtomicInteger();
            list.forEach(v -> {
                v.setIdStr(String.valueOf(v.getId()));
                qTotal.addAndGet(v.getQuantity());
                qqTotal.addAndGet(v.getQuoQuantity());
            });

            AssetsDO last = new AssetsDO();
            last.setIdStr("合计");
            last.setQuantity(qTotal.get());
            last.setQuoQuantity(qqTotal.get());
            list.add(last);
        }
        return new JsonResult<>(count, list);
    }

    @Override
    public AssetsDO getById(int id) {
        return assetsMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(AssetsDO assets) {
        int res = assetsMapper.insert(assets);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(AssetsDO assets) {
        AssetsDO old = assetsMapper.selectByPrimaryKey(assets.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = assetsMapper.update(assets);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }
}
