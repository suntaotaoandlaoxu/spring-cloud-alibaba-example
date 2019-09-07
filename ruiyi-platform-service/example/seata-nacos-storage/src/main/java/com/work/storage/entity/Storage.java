package com.work.storage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * Created by IntelliJ IDEA.
 * User: suntaotao
 * Date: 2019/08/30
 * Time: 10:14
 * Description: 库存模型
 * To change this template use File | Settings | File Templates.
 */

@Data
@Accessors(chain = true)
@TableName("storage_tbl")
public class Storage {

  private Long id;
  private String commodityCode;
  private Long count;


}
