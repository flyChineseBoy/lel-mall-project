package org.lele.authorization.controller;



import com.baomidou.mybatisplus.extension.api.ApiController;
import io.swagger.annotations.Api;
import org.lele.authorization.service.MUserService;
import org.lele.common.entity.user.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表(MUser)表控制层
 *
 * @author lele
 * @since 2020-05-07 20:55:11
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("mUser")
public class MUserController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private MUserService mUserService;

    /**
     * 通过username查询单条数据
     *
     * @param username 用户名
     * @return 单条数据
     */
    @GetMapping("selectByUserName/{username}")
    public MUser selectByUserName(@PathVariable String username) {
        return this.mUserService.selectByUserName(username);
    }


}