package com.reptile.proxy.yg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.reptile.proxy.yg.helper.HttpClientHelper;
import com.reptile.proxy.yg.service.ProxyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 实现
 *
 * @author Y.G
 * @date 2020/4/10 15:48
 **/
@Service
public class ProxyServiceImpl implements ProxyService {
    private static final Logger logger = LoggerFactory.getLogger(ProxyService.class);
    @Override
    public JSONObject proxy(JSONObject param) {
        JSONObject result = new JSONObject();
        try {
            String response = HttpClientHelper.executeGet(param.getString("url"));
            boolean isJSON = "1".equals(param.getString("is_json"));
            if(isJSON) {
                return JSONObject.parseObject(response);
            }else {
                result.put("data", response);
                return result;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return result;
        }
    }
}
