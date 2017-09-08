package com.zjrb.test;

import com.zjrb.test.Utils.HttpRequestUtils;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by juanzhihe on 2017/7/12.
 * GET /api/szcb_news/city?column_ids=53845624e4b08e9fb1cdfc17&session_id=59546c8dedfe26460ac6e6cb&vc=040000 HTTP/1.1
 */
public class HttpParamsGetTest {
    private String url = "http://10.100.62.105/";
    // private String charset = "utf-8";
    private HttpRequestUtils httpRequestUtil = null;

    public HttpParamsGetTest(){
        httpRequestUtil = new HttpRequestUtils();
    }

    @Test
    public void test(){
        /*
        拼接url地址
         */
        String httpOrgCreateTest = url + "/api/szcb_news/city";
        System.out.println("httpOrgCreateTest"+httpOrgCreateTest);
        /*
        参数设置及定义
         */
        Map<String,String> createMap = new HashMap<String, String>();
        createMap.put("column_ids","53845624e4b08e9fb1cdfc17");
        createMap.put("session_id","59546c8dedfe26460ac6e6cb");
        createMap.put("vc","040000");
        String httpOrgCreateTestRtn = httpRequestUtil.httpParamsGet(httpOrgCreateTest,createMap);
        System.out.println("result: "+httpOrgCreateTestRtn);
}

    public static void main(String[] args){
        HttpParamsGetTest main = new HttpParamsGetTest();
        main.test();
    }
}
