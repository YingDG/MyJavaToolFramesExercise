package yingdg.exercise.unirest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by zdm on 2018/3/22.
 */
@Slf4j
public class HelloUnirest {
    private static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";

    public static void main(String[] args) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080")
                .header("accept", "application/json")
                .queryString("id", "123456")//url后拼接的参数
                .field("last", "Polo") // 表单值
                .asJson(); // 返回json

        System.out.println(jsonResponse.getStatus());
        System.out.println(jsonResponse.getStatusText());
        System.out.println(jsonResponse.getHeaders());
        System.out.println(jsonResponse.getBody());
        System.out.println(jsonResponse.getRawBody());
    }

    public static String sendGet(String url) {
        try {
            HttpResponse<String> response = Unirest.get(url)
                    .header("User-Agent", USER_AGENT)
                    .asString();

            return response.getBody();
        } catch (UnirestException e) {
            log.error("HTTP Get Error:{}", e);
            return "";
        }
    }

    public static String sendPost(String url, String bodyParams, String contentType, String cookie) {
        try {
            HttpResponse<String> response = Unirest.post(url)
                    .header("User-Agent", USER_AGENT)
                    .header("Connection", "Keep-Alive")
                    .header("Content-Type", contentType)
                    .header("Cookie", cookie)
                    .body(bodyParams)
                    .asString();

            return response.getBody();
        } catch (UnirestException e) {
            log.error("HTTP Get Error:{}", e);
            return "";
        }
    }

    public static String sendPost(String url, String bodyParams, String contentType) {
        try {
            HttpResponse<String> response = Unirest.post(url)
                    .header("User-Agent", USER_AGENT)
                    .header("Connection", "Keep-Alive")
                    .header("Content-Type", contentType)
                    .body(bodyParams)
                    .asString();

            return response.getBody();
        } catch (UnirestException e) {
            log.error("HTTP Get Error:{}", e);
            return "";
        }
    }

}
