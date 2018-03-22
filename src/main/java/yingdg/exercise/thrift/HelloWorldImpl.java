package yingdg.exercise.thrift;

/**
 * Created by yingdg on 2017/10/22.
 */
public class HelloWorldImpl implements HelloWorldService.Iface {
    @Override
    public String sayHello(String name) throws org.apache.thrift.TException {
        return "Hi," + name + " ,Welcome to the thrift's world !";
    }
}
