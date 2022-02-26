package cn.bug.dingding.proxy_demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/post")
    public Object post(@RequestParam(value = "code") String code) {
        return "code => " + code;
    }

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

}
