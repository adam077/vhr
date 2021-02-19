package org.javaboy.vhr;

import org.javaboy.vhr.utils.Const;
import org.javaboy.vhr.utils.HTTPUtils;
import org.javaboy.vhr.utils.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebQueryTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        Do();
    }

    public static void Do() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        CloseableHttpClient httpClient = HTTPUtils.GetHttpClient(false, 1);
        try {
            HttpPost httpPost = new HttpPost("https://amctr.matrix.jdcloud.com/api/crowd/");

            // body set: key value format

//            List<NameValuePair> nvps = new ArrayList<>();
//            nvps.add(new BasicNameValuePair("authorization", Const.authorization));
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            // body set: normal HashMap
            Map<String, Object> m = new HashMap<>();
            m.put("keyword", "Z");
            m.put("types", new ArrayList<>());
            m.put("statuses", new ArrayList<>());
            m.put("page", 10);
            m.put("pageSize", 10);
            httpPost.setEntity(new StringEntity(JsonUtils.TransMapToString(m)));

            // header set
            httpPost.setHeader("authorization", Const.authorization);
            httpPost.setHeader("cookie", Const.cookie);
            httpPost.setHeader("authority", "amctr.matrix.jdcloud.com");
            httpPost.setHeader("accept", "application/json, text/plain, */*");
            httpPost.setHeader("sec-fetch-dest", "empty");
            httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36");
            httpPost.setHeader("content-type", "application/json;charset=UTF-8");
            httpPost.setHeader("origin", "https://amctr.matrix.jdcloud.com");
            httpPost.setHeader("sec-fetch-site", "same-origin");
            httpPost.setHeader("sec-fetch-mode", "cors");
            httpPost.setHeader("referer", "https://amctr.matrix.jdcloud.com/crowd/manage");
            httpPost.setHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7");

            try (CloseableHttpResponse response2 = httpClient.execute(httpPost)) {
                System.out.println("----------------------------------------");
                System.out.println("返回响应：" + response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                System.out.println("响应内容：" + EntityUtils.toString(entity2));
                EntityUtils.consume(entity2);
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}