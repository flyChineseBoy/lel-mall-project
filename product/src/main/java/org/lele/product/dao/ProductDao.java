package org.lele.product.dao;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.lele.common.entity.product.Product;

/**
 * 商品表(Product)表数据库访问层
 *
 * @author lele
 * @since 2020-05-25 20:43:28
 */
@Mapper
public interface ProductDao extends BaseMapper<Product> {

}