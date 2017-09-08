package com.zjrb.test;

import com.alibaba.fastjson.JSON;
import com.zjrb.test.Utils.HttpRequestUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by juanzhihe on 2017/7/12.
 * http://10.100.62.205/api/client_log/new_batch
 */
public class HttpPostTest {
    private String url = "http://10.100.62.205/";
   // private String charset = "utf-8";
    private HttpRequestUtils httpRequestUtil = null;

    public HttpPostTest(){
        httpRequestUtil = new HttpRequestUtils();
    }
    @Test
    public void test(){
        String httpOrgCreateTest = url + "/api/follow/create";
        Map<String,String> createMap = new HashMap<String, String>();
        createMap.put("news_id","2155");
        createMap.put("session_id","59546c8dedfe26460ac6e6cb");
        createMap.put("type","4");
        createMap.put("vc","040000");
        String httpOrgCreateTestRtn = httpRequestUtil.doPostMap(httpOrgCreateTest,createMap);
        System.out.println("result:"+httpOrgCreateTestRtn);
        String jsonResult = JSON.toJSONString(httpOrgCreateTestRtn);
        System.out.println("jsonResult: "+jsonResult);
    }

    public static void main(String[] args){
        HttpPostTest main = new HttpPostTest();
        main.test();
    }

}
