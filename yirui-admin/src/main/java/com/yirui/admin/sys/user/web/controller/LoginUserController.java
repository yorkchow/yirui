package com.yirui.admin.sys.user.web.controller;

import com.yirui.admin.sys.user.model.User;
import com.yirui.common.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录用户的个人信息
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/7
 * Time: 22:59
 */
@Controller
@RequestMapping("/admin/sys/user/loginUser")
public class LoginUserController extends BaseController<User, Integer> {

}
