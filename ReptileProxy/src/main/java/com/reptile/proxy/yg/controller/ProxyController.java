package com.reptile.proxy.yg.controller;

import com.alibaba.fastjson.JSONObject;
import com.reptile.proxy.yg.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求控制
 *
 * @author Y.G
 * @date 2020/4/10 11:32
 **/
@RestController
@RequestMapping("/proxy")
public class ProxyController {
    @Autowired
    private ProxyService proxyService;

    @RequestMapping(value = "/reptile", method = RequestMethod.POST)
    public JSONObject proxyRequest(@RequestBody JSONObject param) {
        JSONObject result = new JSONObject();
        result.put("result", "ok");
        result.put("data", proxyService.proxy(param));
        return result;
    }
}
