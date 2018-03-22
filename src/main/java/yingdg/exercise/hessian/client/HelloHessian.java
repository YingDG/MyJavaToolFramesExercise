package yingdg.exercise.hessian.client;

import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

/**
 * Created by yingdg on 2017/10/10 0010.
 */
public class HelloHessian {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        String url = "http://localhost/user/2";
        HessianProxyFactory clientFactory = new HessianProxyFactory();
        IMessage message = (IMessage) clientFactory.create(IMessage.class, url);
        System.out.println(message.sayHello());
    }
}
