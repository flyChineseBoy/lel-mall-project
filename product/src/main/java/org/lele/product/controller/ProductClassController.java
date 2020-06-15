package org.lele.product.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lele.common.dto.CommonResult;
import org.lele.common.entity.product.ProductClass;
import org.lele.product.service.ProductClassService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品类别(ProductClass)表控制层
 *
 * @author lele
 * @since 2020-05-25 20:43:28
 */
@RestController
@RequestMapping("productClass")
@Api(tags = "商品分类",position = 1)
public class ProductClassController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProductClassService productClassService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param productClass 查询实体
     * @return 所有数据
     */
    @ApiOperation("查看所有分类")
    @GetMapping
    public CommonResult selectAll(@ApiIgnore Page<ProductClass> page, ProductClass productClass) {
        return CommonResult.success(this.productClassService.page(page, new QueryWrapper<>(productClass)));
    }

    /**
     * 新增数据
     * TODO 约束：不可添加同名分类。
     * @param productClass 实体对象
     * @return 新增结果
     */
    @ApiOperation("新增商品分类")
    @PostMapping
    public CommonResult insert(@RequestBody ProductClass productClass) {
        return CommonResult.success(this.productClassService.save(productClass));
    }


    /**
     * 删除数据
     * 约束：校验该商品分类必须没有产品才可以删除
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public CommonResult delete(@RequestParam("idList") List<Long> idList) {
        // TODO 校验该商品分类必须没有产品才可以删除
        return CommonResult.success(this.productClassService.removeByIds(idList));
    }
}