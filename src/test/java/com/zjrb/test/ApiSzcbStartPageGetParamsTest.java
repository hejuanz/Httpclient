package com.zjrb.test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by juanzhihe on 2017/7/13.
 */
public class ApiSzcbStartPageGetParamsTest {
    private static Logger logger = LoggerFactory.getLogger(ApiSzcbStartPageGetParamsTest.class);
    @Before
    public void before(){
        RestAssured.baseURI = "http://10.100.62.205";
        RestAssured.port = 80;
    }
    @Test
    /*
    URL为http://10.100.62.205/api/szcb_start_page/view?height=1136&session_id=59546c8dedfe26460ac6e6cb&type=iPhone5&vc=040000&width=640
     */
    public void testGetParamsStartPage() {
        try {
            ValidatableResponse response = RestAssured.given()
                    .param("height", "1136")
                    .param("session_id", "59546c8dedfe26460ac6e6cb")
                    .param("type", "iPhone5")
                    .param("vc", "040000")
                    .param("width", "640")
                    .get("/api/szcb_start_page/view")
                    .then()
                    .assertThat()
                    //状态相应码验证
                    .statusCode(200)
                    //最外层数据类型及值验证
                    .body("msg", Matchers.equalTo("ok"))
                    .body("code", Matchers.equalTo(1))

                    //result数组中的page_info对象类型及空验证
                    .body("result[0].page_info.id", Matchers.equalTo(347))
                    .body("result[0].page_info.id", Matchers.notNullValue(Integer.class))

                    .body("result[0].page_info.link", Matchers.notNullValue(String.class))
                    .body("result[0].page_info.title", Matchers.notNullValue(String.class))
                    .body("result[0].page_info.publish_at", Matchers.notNullValue(Long.class))

                    //result数组中的page_image对象类型及空验证
                    .body("result[0].page_image.width", Matchers.notNullValue(Integer.class))
                    .body("result[0].page_image.height", Matchers.notNullValue(Integer.class))
                    .body("result[0].page_image.url", Matchers.notNullValue(String.class));
        }finally {
            RestAssured.reset();
        }
    }

}
