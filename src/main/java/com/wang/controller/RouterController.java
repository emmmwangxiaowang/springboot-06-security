package com.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 王航
 * @Email: 954544828@qq.com
 * @Date: 2021/10/6 0006
 */
@Controller
public class RouterController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "views/login";
    }

    //通过一个id参数匹配对应的页面  这样就不用一个一个的写了😄
    @RequestMapping("/level1/{id}")
    public String level1(@PathVariable("id") int id) {

        return "views/level1/" + id;
    }

    @RequestMapping("/level2/{id}")
    public String level2(@PathVariable("id") int id) {

        return "views/level2/" + id;
    }

    @RequestMapping("/level3/{id}")
    public String level3(@PathVariable("id") int id) {

        return "views/level3/" + id;
    }

}
