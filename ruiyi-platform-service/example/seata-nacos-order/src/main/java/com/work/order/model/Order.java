package com.work.order.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
/**
 * Created by IntelliJ IDEA.
 * User: suntaotao
 * Date: 2019/08/30
 * Time: 10:14
 * Description: 订单模型
 * To change this template use File | Settings | File Templates.
 */

@Data
@Accessors(chain = true)
@TableName("order_tbl")
public class Order {

  @TableId(type=IdType.AUTO)
  private Integer id;
  private String userId;
  private String commodityCode;
  private Integer count;
  private BigDecimal money;

}
