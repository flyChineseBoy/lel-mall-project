package org.lele.product.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.lele.common.dto.CommonResult;
import org.lele.common.entity.product.ProductSpecs;
import org.lele.product.service.ProductSpecsService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 商品规格表，记录sku数据和对应价格、库存数量(ProductSpecs)表控制层
 *
 * @author lele
 * @since 2020-06-14 22:24:20
 */
@RestController
@RequestMapping("productSpecs")
public class ProductSpecsController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProductSpecsService productSpecsService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param productSpecs 查询实体
     * @return 所有数据
     */
    @GetMapping
    public CommonResult selectAll(@ApiIgnore Page<ProductSpecs> page, ProductSpecs productSpecs) {
        return CommonResult.success(this.productSpecsService.page(page, new QueryWrapper<>(productSpecs)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public CommonResult selectOne(@PathVariable Serializable id) {
        return CommonResult.success(this.productSpecsService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param productSpecs 实体对象
     * @return 新增结果
     */
    @PostMapping
    public CommonResult insert(@RequestBody ProductSpecs productSpecs) {
        return CommonResult.success(this.productSpecsService.save(productSpecs));
    }

    /**
     * 修改数据
     *
     * @param productSpecs 实体对象
     * @return 修改结果
     */
    @PutMapping
    public CommonResult update(@RequestBody ProductSpecs productSpecs) {
        return CommonResult.success(this.productSpecsService.updateById(productSpecs));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public CommonResult delete(@RequestParam("idList") List<Long> idList) {
        return CommonResult.success(this.productSpecsService.removeByIds(idList));
    }
}