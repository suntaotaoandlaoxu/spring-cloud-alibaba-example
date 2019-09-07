package com.work.storage.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.storage.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * Created by IntelliJ IDEA.
 * User: suntaotao
 * Date: 2019/08/30
 * Time: 10:14
 * Description: 库存Mapper
 * To change this template use File | Settings | File Templates.
 */

@Mapper
@Repository
public interface StorageDAO extends BaseMapper<Storage> {

}
