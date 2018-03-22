package yingdg.exercise.httpclient;

/**
 * Created by yingdg on 2017/10/20 0020.
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        // get请求
//        new HttpGetDemo().get();

        // post请求
//        new HttpPostDemo().post();
        new HttpPostFormDemo().postForm();

        // post文件上传
//        new HttpFileuploadDemo().upload();

        // 代理请求
//        new HttpProxyDemo().proxy();
    }
}
