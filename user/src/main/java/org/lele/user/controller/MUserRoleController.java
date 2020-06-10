package org.lele.user.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lele.common.dto.CommonResult;
import org.lele.common.entity.MRolePermission;
import org.lele.common.entity.MUserRole;
import org.lele.user.service.MUserRoleService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户角色表(MUserRole)表控制层
 *
 * @author lele
 * @since 2020-05-07 20:55:22
 */
@Api(tags = "用户授予角色")
@RestController
@RequestMapping("mUserRole")
public class MUserRoleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private MUserRoleService mUserRoleService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param mUserRole 查询实体
     * @return 所有数据
     */
    @GetMapping
    public CommonResult selectAll(@ApiIgnore Page<MUserRole> page, MUserRole mUserRole) {
        return CommonResult.success(this.mUserRoleService.page(page, new QueryWrapper<>(mUserRole)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public CommonResult selectOne(@PathVariable Serializable id) {
        return CommonResult.success(this.mUserRoleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param mUserRole 实体对象
     * @return 新增结果
     */
    @ApiOperation("用户授予角色")
    @PostMapping
    public CommonResult insert(@RequestBody MUserRole mUserRole) {
        return CommonResult.success(this.mUserRoleService.save(mUserRole));
    }

    /**
     * 修改数据
     *
     * @param mUserRole 实体对象
     * @return 修改结果
     */
    @PutMapping
    public CommonResult update(@RequestBody MUserRole mUserRole) {
        return CommonResult.success(this.mUserRoleService.updateById(mUserRole));
    }

    /**
     * 取消授权
     *@param mUserRole 实体对象
     *              必填： roleId、userId
     * @return 删除结果
     */
    @ApiOperation("取消角色授权")
    @PostMapping("/remove")
    public CommonResult delete(@RequestBody MUserRole mUserRole) {
        Assert.notNull(mUserRole,"用户id和资源id不能为空");
        Assert.notNull(mUserRole.getUserId(),"用户id不能为空");
        Assert.notNull(mUserRole.getRoleId(),"角色id不能为空");

        Map<String,Object> param = new HashMap<>();
        param.put("user_id",mUserRole.getUserId());
        param.put("role_id",mUserRole.getRoleId());
        return CommonResult.success(this.mUserRoleService.removeByMap(param));
    }
}