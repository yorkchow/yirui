package com.yirui.admin.index.web.controller;

import com.yirui.admin.sys.resource.service.ResourceService;
import com.yirui.admin.sys.user.model.User;
import com.yirui.admin.sys.user.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 22:36
 */
@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = {"/{index:index;?.*}"})
    public String index(@CurrentUser User user, Model model) {


        return "admin/index/index";
    }
}
