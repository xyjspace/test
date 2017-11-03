package com.xyj.study.test;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by banma on 2017/7/26.
 */
public class RabbitTest {

    public static void main(String[] args) throws Exception{
        String APIKEY  = "e710ca9054bc3559e83d490d09af19bc";
        String question = "讲个笑话";
        String INFO = URLEncoder.encode(question,"utf-8");
        String getURL = "http://openapi.tuling123.com/openapi/api?key=" + APIKEY + "&info=" +INFO;
        URL getUrl = new URL(getURL);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while((line = reader.readLine())!=null){
            sb.append(line);
        }
        reader.close();
        connection.disconnect();
        System.out.println(sb);

    }
}
