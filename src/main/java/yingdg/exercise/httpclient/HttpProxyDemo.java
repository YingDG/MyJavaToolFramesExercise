package yingdg.exercise.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by yingdg on 2017/10/21.
 */
public class HttpProxyDemo {
    /**
     * 网络代理上网
     */
    public void proxy() {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient httpClient = httpClientBuilder.build();
        // 依次是目标请求地址，端口号,协议类型
        HttpHost target = new HttpHost("172.17.42.80", 8080, "http");
        // 依次是代理地址，代理端口号，协议类型
        HttpHost proxy = new HttpHost("172.17.42.80", 8080, "http");
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

        // 请求地址
//        HttpPost httpPost = new HttpPost("http://www.baidu.com");
//        httpPost.setConfig(config);
//        // 创建参数队列
//        List<NameValuePair> formparams = new ArrayList<>();
//        // 参数名为pid，值是2
//        formparams.add(new BasicNameValuePair("pid", "2"));
        HttpGet httpget = new HttpGet("https://www.baidu.com");
        httpget.setConfig(config);
        System.out.println("executing request " + httpget.getURI());

//        UrlEncodedFormEntity entity;
        try {
//            entity = new UrlEncodedFormEntity(formparams, "UTF-8");
//            httpPost.setEntity(entity);

//            CloseableHttpResponse response = closeableHttpClient.execute(target, httpPost);
            CloseableHttpResponse response = httpClient.execute(target, httpget);
            // getEntity
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                System.out.println("response:" + EntityUtils.toString(httpEntity, "UTF-8"));
                EntityUtils.consume(httpEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
