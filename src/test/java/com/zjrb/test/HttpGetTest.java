package com.zjrb.test;

import com.zjrb.test.Utils.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by juanzhihe on 2017/7/12.
 */
public class HttpGetTest{
    private static Logger logger = LoggerFactory.getLogger(HttpGetTest.class);
    public static void main(String[] args){
         String URL = "http://10.100.62.205/api/szcb_news/list?column_id=52e5f902cf81d754a434fb50&session_id=59546c8dedfe26460ac6e6cb&size=20&vc=040000";
         HttpRequestUtils SzcbNewGet = new HttpRequestUtils();
         SzcbNewGet.httpGet(URL);

    }
}
