package com.yg.flink.demo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/5/9 13:39
 **/
public class FuncTest {
    public static void main(String[] args) {
        JsonObject jObj = JsonParser.parseString("{\"cover\": \"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2594916975.jpg\", \"cover_y\": 2222, \"cover_x\": 1500, \"rate\": \"8.1\", \"is_new\": true, \"id\": \"33420285\", \"title\": \"\\u6821\\u56ed\\u60c5\\u5723\", \"url\": \"https://movie.douban.com/subject/33420285/\", \"playable\": false, \"hot_index\": 1}").getAsJsonObject();
        String title = jObj.get("title").getAsString();
        System.out.println(title);
    }
}
