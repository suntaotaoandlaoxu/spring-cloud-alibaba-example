package com.work.storage.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.work.storage.entity.Storage;
import com.work.storage.repository.StorageDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
/**
 * Created by IntelliJ IDEA.
 * User: suntaotao
 * Date: 2019/08/30
 * Time: 10:14
 * Description: 库存实现类
 * To change this template use File | Settings | File Templates.
 */

@Service
public class StorageService {

    @Resource
    private StorageDAO storageDAO;

    /**
     * 减库存
     * 
     * @param commodityCode
     * @param count
     */
    @Transactional(rollbackFor = Exception.class)
    public void deduct(String commodityCode, int count) {
        if (commodityCode.equals("product-2")) {
            throw new RuntimeException("异常:模拟业务异常:Storage branch exception");
        }

        QueryWrapper<Storage> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Storage().setCommodityCode(commodityCode));
        Storage storage = storageDAO.selectOne(wrapper);
        storage.setCount(storage.getCount() - count);

        storageDAO.updateById(storage);
    }
}
