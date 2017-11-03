package com.xyj.study.test;

import com.xyj.study.utils.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by banma on 2017/7/26.
 */
public class HttpClientTest {

    @Test
    public void post() {
//        CloseableHttpClient client = HttpClients.createDefault();
//        String APIKEY = "e710ca9054bc3559e83d490d09af19bc";
//        String question = "讲个笑话";
//        HttpPost httpPost = new HttpPost("http://openapi.tuling123.com/openapi/api");
//
//        Map<String,String> map = new HashMap();
//        map.put("key",APIKEY);
//        map.put("info",question);
//        List<NameValuePair> body = HttpUtils.getEntity(map);
//        UrlEncodedFormEntity entity;
//
//        try {
//            entity = new UrlEncodedFormEntity(body, "utf-8");
//            httpPost.setEntity(entity);
//            CloseableHttpResponse response = client.execute(httpPost);
//
//            HttpEntity httpEntity = response.getEntity();
//            if (httpEntity != null) {
//                System.out.println(EntityUtils.toString(httpEntity, "utf-8"));
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Test
    public void get() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        String APIKEY = "e710ca9054bc3559e83d490d09af19bc";
        String question = "讲个笑话";
//        HttpGet httpGet = new HttpGet("http://openapi.tuling123.com/openapi/api?key=" + APIKEY+"&info=" + question);
        HttpGet httpGet = new HttpGet("https://login.weixin.qq.com/jslogin");
        CloseableHttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();

        System.out.println(EntityUtils.toString(entity));
    }


}
