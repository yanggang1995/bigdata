package com.reptile.proxy.yg.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 代理处理
 *
 * @author Y.G
 * @date 2020/4/10 15:46
 **/
public interface ProxyService {
    /**
     * 代理请求
     * @param param 参数
     * @return 响应
     */
    JSONObject proxy(JSONObject param);
}
