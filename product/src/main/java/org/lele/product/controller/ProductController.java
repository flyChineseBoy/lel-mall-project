package org.lele.product.controller;



import com.baomidou.mybatisplus.extension.api.ApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lele.common.dto.CommonResult;
import org.lele.product.dto.request.CreateProductRequest;
import org.lele.product.dto.request.QueryProductRequest;
import org.lele.common.entity.product.Product;
import org.lele.product.exception.MallProductException;
import org.lele.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 商品表(Product)表控制层
 *
 * @author lele
 * @since 2020-05-25 20:43:28
 */
@RestController
@RequestMapping("product")
@Api(tags = "商品API",position = 4)
public class ProductController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;


    /**
     * 商品详情
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public CommonResult selectOne(@PathVariable Serializable id) {
        // TODO 计算热点商品，缓存到redis
        return CommonResult.success(this.productService.getById(id));
    }

    /**
     * 更新商品
     * @param product 实体对象
     * @return 修改结果
     */
    @PutMapping
    public CommonResult update(@RequestBody Product product) throws MallProductException{
        // TODO 更新es
        if( null == product.getId() ) { throw new MallProductException("商品id不能为空"); }
        return CommonResult.success(this.productService.updateById(product));
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public CommonResult delete(@RequestParam("idList") List<Long> idList) {
        // TODO 删除es中商品
        return CommonResult.success(this.productService.removeByIds(idList));
    }


    /**
     * 新增一条商品
     *
     * @param request 商品，包含规格参数
     * @return 新增结果
     */
    @ApiOperation("新增商品，包含商品各种规格/价格/库存，注意新增后1s内不能查询到")
    @PostMapping
    public CommonResult saveProduct(@RequestBody CreateProductRequest request ) {
        return CommonResult.success(this.productService.saveProduct(request));
    }

    /**
     * 搜索商品
     * @param request 查询实体
     * @return 所有数据
     */
    @ApiOperation("ES中查询商品，包含商品各种规格/价格/库存。")
    @PostMapping("/search")
    public CommonResult search( QueryProductRequest request) {
        return CommonResult.success(this.productService.search(request));
    }
}