package com.zp;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

/**
 * HttpCilent
 *
 * @author zhengpanone
 * @since 2021-11-25
 */
public class HttpClient {
    /**
     * 带参数Get请求
     *
     * @param url
     * @param param
     * @return String
     */
    public static String doGet(String url, Map<String, String> param) {
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = null;
        CloseableHttpResponse response = null;
        try {
            // 创建URL
            URIBuilder uriBuilder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    uriBuilder.addParameter(key, param.get(key));
                }
            }
            URI uri = uriBuilder.build();
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数get请求
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doPost(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response= null;
        String resultString = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            URI uri = uriBuilder.build();
            HttpPost httpPost = new HttpPost(uri);
            if(param !=null){
                ArrayList<NameValuePair> paramList = new ArrayList<>();
                for(String key:param.keySet()){
                    paramList.add(new BasicNameValuePair(key,param.get(key)));
                }
                //模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);

                httpPost.setEntity(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
