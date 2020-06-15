package org.lele.user.controller;



import com.baomidou.mybatisplus.extension.api.ApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lele.common.dto.CommonResult;
import org.lele.common.entity.user.MUserRole;
import org.lele.user.service.MUserRoleService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户角色表(MUserRole)表控制层
 *
 * @author lele
 * @since 2020-05-07 20:55:22
 */
@Api(tags = "用户授予角色",position = 2)
@RestController
@RequestMapping("mUserRole")
public class MUserRoleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private MUserRoleService mUserRoleService;


    /**
     * 用户授予角色
     * @param mUserRole 实体对象
     * @return 新增结果
     */
    @ApiOperation("用户授予角色")
    @PostMapping
    public CommonResult insert(@RequestBody MUserRole mUserRole) {
        return CommonResult.success(this.mUserRoleService.save(mUserRole));
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