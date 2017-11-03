package com.xyj.study.service.impl;

import com.xyj.study.sys.WeixinEntity;
import com.xyj.study.utils.HttpUtils;
import com.xyj.study.utils.Utils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by banma on 2017/11/1.
 */
@Service
public class WeixinLoginServiceImpl {

    @Autowired
    WeixinEntity weixinEntity;
    @Autowired
    HttpUtils httpUtils;

    /**
     * 获得二维码的地址
     *
     * @return
     */
    public String getUUID() {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("appid", weixinEntity.getAppid());
        paramsMap.put("fun", "new");
        paramsMap.put("lang", "zh_CN");
        paramsMap.put("_", String.valueOf(System.currentTimeMillis()));
        return httpUtils.getContent(weixinEntity.getGetuuid(), paramsMap);
    }

    public String showQR() {
        String uuidResult = this.getUUID();
        String uuid = Utils.match("window.QRLogin.uuid = \"(.*)\";", uuidResult);
        HttpEntity result = getQR(uuid);
        File file = new File("static/QR.jpg");
        try {
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(EntityUtils.toByteArray(result));
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(file.getPath());
        return file.getPath();
    }

    public HttpEntity getQR(String uuid) {
        StringBuilder url = new StringBuilder(weixinEntity.getShowQR());
        url.append(uuid);
        Map paramsMap = new HashMap();
        paramsMap.put("t", "webwx");
        paramsMap.put("_", String.valueOf(System.currentTimeMillis()));
        HttpResponse httpResponse = httpUtils.post(url.toString(), paramsMap);
        if (httpResponse == null) {
            return null;
        }
        return httpResponse.getEntity();
    }

}
