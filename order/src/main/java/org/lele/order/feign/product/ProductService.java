package org.lele.order.feign.product;

import org.lele.common.dto.CommonResult;
import org.lele.common.entity.product.ProductSpecs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

/**
 * org.lele.order.service
 *
 * @author: lele
 * @date: 2020-06-13
 */
@FeignClient("product")
public interface ProductService {
    @GetMapping("/product/{id}")
    public CommonResult selectOne(@PathVariable Long id);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/productSpecs/{id}")
    public CommonResult<ProductSpecs> selectOneProductSpecs(@PathVariable Serializable id);

}
