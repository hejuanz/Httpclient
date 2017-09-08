package com.zjrb.test.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by juanzhihe on 2017/6/29.
 */
public class HttpRequestUtils {
    //日志记录
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);
    /**
     * 发送get请求，无参数
     * @param url    路径
     * @return
     */
    public static String httpGet(String url){
        //get请求返回结果
        String result = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(request);
            int StatusCode = response.getStatusLine().getStatusCode();
            System.out.println("StatusCode = "+StatusCode);
            /**请求发送成功，并得到响应**/
            if (StatusCode == 200) {
                /**读取服务器返回过来的字符串数据**/
                result = EntityUtils.toString(response.getEntity());
                System.out.println("result = "+result);
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        }
        return result;
    }
    /**
     * 发送Get方法的HTTP请求，有参数，参数为K-V形式
     * @param url
     * @param params
     * @return
     */
    public static String httpParamsGet(String url,Map<String,String> params){
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for(String key : params.keySet()){
            if(i == 0){
                param.append("?");
            }
            else{
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        /*
        拼接apiUrl地址
         */
        apiUrl += param;
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            HttpGet request = new HttpGet(apiUrl);
            /*
            发送请求
             */
            CloseableHttpResponse response = httpClient.execute(request);
            /*
            获取状态码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("请求返回的状态码为："+statusCode);
            if (statusCode == 200)
            {
            /*
            获取实体
             */
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "UTF-8");
                    System.out.println("result = "+result);
                }
                else {
                    logger.error("响应实体为空！");

                }
            }
            else {
                logger.error("get请求提交失败:" + url);
                logger.info("statusCode = "+statusCode);
            }
        }catch (IOException e){
            logger.error("get请求提交失败:" + url, e);
        }
        return result;

    }

    /**
     * 利用HttpClient封装post请求的工具类，K-V形式
     * @param url
     * @param params
     * @return
     */
    public String doPostMap(String url,Map<String,String> params){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try{
            //设置参数
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(response.toString());
            System.out.println("statusCode = "+statusCode);
            /*
            获取response实体
             */
            if (statusCode == 200){
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "UTF-8");
                System.out.println("result = "+result);
            }
            else {
                logger.error("get请求提交失败:" + url);
                logger.info("statusCode = "+statusCode);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (response != null){
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 发送Post方式的http请求，入参形式为JSON形式
     * @param url
     * @param json
     * @return
     */
    public String doPostJson(String url,Object json){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;

        try{
            /*
            解决中文乱码问题
             */
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200){
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity,"UTF-8");
                System.out.println("result = "+result);

            }
            else{
                logger.error("post请求提交失败:" + url);
                logger.info("statusCode = "+statusCode);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(response != null){
                try{
                    EntityUtils.consume(response.getEntity());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
        return result;
    }
}

