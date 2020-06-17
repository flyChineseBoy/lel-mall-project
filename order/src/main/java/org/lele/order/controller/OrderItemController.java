package org.lele.order.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.lele.common.entity.order.OrderItem;
import org.lele.order.service.OrderItemService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (OrderItem)表控制层
 *
 * @author lele
 * @since 2020-06-13 20:45:40
 */
@RestController
@RequestMapping("orderItem")
public class OrderItemController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private OrderItemService orderItemService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param orderItem 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(@ApiIgnore Page<OrderItem> page, OrderItem orderItem) {
        return success(this.orderItemService.page(page, new QueryWrapper<>(orderItem)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.orderItemService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param orderItem 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody OrderItem orderItem) {
        return success(this.orderItemService.save(orderItem));
    }

    /**
     * 修改数据
     *
     * @param orderItem 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody OrderItem orderItem) {
        return success(this.orderItemService.updateById(orderItem));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.orderItemService.removeByIds(idList));
    }
}