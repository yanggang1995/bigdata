package com.reptile.proxy.yg;

import com.reptile.proxy.yg.entity.LoopList;
import com.reptile.proxy.yg.entity.LoopNode;
import com.reptile.proxy.yg.entity.Node;
import com.reptile.proxy.yg.helper.HttpClientHelper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/4/10 14:11
 **/
public class test {

    @Test
    public void httpGetTest() throws IOException {
        System.out.println(HttpClientHelper.executeGet("https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&page_limit=50&page_start=0"));
    }

    @Test
    public void loopListTest(){
        LoopList loopList = LoopList.of();
        loopList.add(new LoopNode<>("1"));
        loopList.add(new LoopNode("2"));
        loopList.add(new LoopNode("3"));
    }
}
