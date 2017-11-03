package com.xyj.study.utils;

import com.sun.xml.internal.xsom.impl.Ref;
import com.xyj.study.sys.TulingEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by banma on 2017/10/31.
 */
@Service
public class HttpUtils {

    /**
     * 设置参数， 必须都为String
     *
     * @param map
     * @return
     */
    public List<NameValuePair> getEntity(Map<String, String> map) {
        List<NameValuePair> nameValuePairs = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return nameValuePairs;
    }

    /**
     * post请求
     *
     * @param url
     * @param postEntity
     * @return
     */
    public HttpResponse post(String url, Map<String, String> postEntity) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> body = this.getEntity(postEntity);
        UrlEncodedFormEntity entity;
        try {
            entity = new UrlEncodedFormEntity(body, "utf-8");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = client.execute(httpPost);
            if (response != null) {
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求获取String类型的值
     *
     * @param url
     * @param postEntity
     * @return
     */
    public String postContent(String url, Map<String, String> postEntity) {
        CloseableHttpResponse response = (CloseableHttpResponse) this.post(url, postEntity);
        return gainString(response);
    }

    /**
     * get请求
     *
     * @param url
     * @param postEntity
     * @return
     */
    public HttpResponse get(String url, Map<String, String> postEntity) {
        CloseableHttpClient client = HttpClients.createDefault();
        List<NameValuePair> body = this.getEntity(postEntity);
        try {
            String params = EntityUtils.toString(new UrlEncodedFormEntity(body, "utf-8"));
            HttpGet httpGet = new HttpGet(url + "?" + params);
            CloseableHttpResponse response = client.execute(httpGet);
            if (response != null) {
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getContent(String url, Map<String, String> postEntity) {
        CloseableHttpResponse response = (CloseableHttpResponse) this.post(url, postEntity);
        return gainString(response);
    }

    private String gainString(HttpResponse httpResponse) {
        CloseableHttpResponse response = null;
        if (httpResponse == null || !(httpResponse instanceof CloseableHttpResponse)) {
            return "";
        }
        response = (CloseableHttpResponse) httpResponse;
        HttpEntity httpEntity = response.getEntity();
        try {
            if (httpEntity != null) {
                return EntityUtils.toString(httpEntity);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

}
