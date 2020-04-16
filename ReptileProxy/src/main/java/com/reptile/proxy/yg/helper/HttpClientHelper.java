package com.reptile.proxy.yg.helper;

import com.google.common.collect.ImmutableList;
import com.reptile.proxy.yg.entity.LoopList;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


/**
 * Http请求帮助类
 *
 * @author Y.G
 * @date 2020/2/28 15:31
 **/
public class HttpClientHelper {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientHelper.class);

    public static final LoopList USER_AGENT = LoopList.of()
            .add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
            .add("Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
            .add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.18362");

    /**
     * 执行post请求，参数类型json
     *
     * @param url   请求地址
     * @param param 请求参数 json
     * @return 响应结果，若执行异常则返回null
     */
    public static String executePost(String url, String param) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url.replace(" ", "%20"));
            httpPost.setHeader("Content-type", "application/json;charset=utf-8");
            httpPost.setHeader("Connection", "keep-alive");
            StringEntity entity = new StringEntity(param, StandardCharsets.UTF_8);
            httpPost.setEntity(entity);
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                try (InputStream responseIn = response.getEntity().getContent()) {
                    return IOUtils.toString(responseIn);
                }
            }
        } catch (Exception e) {
            logger.error(String.format("%s 请求失败，参数：[%s]", url, param));
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * httpGet请求 获取响应结果
     *
     * @param url 请求地址
     * @return 响应结果
     * @throws IOException io异常
     */
    public static String executeGet(String url) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("User-Agent", (String)USER_AGENT.current().getData());
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                try (InputStream responseIn = response.getEntity().getContent()) {
                    return IOUtils.toString(responseIn);
                }
            }
        }
    }
}
