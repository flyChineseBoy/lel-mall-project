package org.lele.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.lele.common.entity.order.MOrder;

/**
 * 订单总表(MOrder)表数据库访问层
 *
 * @author lele
 * @since 2020-06-14 23:38:08
 */
@Mapper
public interface OrderDao extends BaseMapper<MOrder> {

}