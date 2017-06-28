package com.xyj.study.springbootstart;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by banma on 2017/6/28.
 */
public class TestRobot {

    @Test
    public void test(){
        HttpClient httpClient =  new DefaultHttpClient();
        HttpPost httpPost =  new HttpPost("http://www.tuling123.com/openapi/api");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("apiKey","e710ca9054bc3559e83d490d09af19bc"));
        params.add(new BasicNameValuePair("secret","e93215e0185bdb2c"));
        params.add(new BasicNameValuePair("timestamp", new Date().toString()));
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(params,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(entity);
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                System.out.println("请求和响应成功");
                HttpEntity httpEntity = httpResponse.getEntity();
                String response = EntityUtils.toString(httpEntity);
                System.out.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
