package com.copy.controller;

import com.perfree.base.BaseController;
import com.perfree.commons.Constants;
import com.perfree.permission.AdminMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingController extends BaseController {

    @RequestMapping("/admin/copy/setting")
    @AdminMenu(groupId = Constants.ADMIN_MENU_GROUP_PLUGIN, name = "网页复制")
    public String setting(){
        return "/copy-static/setting.html";
    }

}
