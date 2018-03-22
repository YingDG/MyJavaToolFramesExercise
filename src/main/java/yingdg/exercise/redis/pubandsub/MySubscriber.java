package yingdg.exercise.redis.pubandsub;

import redis.clients.jedis.JedisPubSub;

/**
 * Created by yingdg on 2017/8/30.
 */
public class MySubscriber extends JedisPubSub {

    /*
    初始化订阅时候的处理
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
                channel, subscribedChannels));
    }

    /*
    取得订阅的消息后的处理
     */
    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
    }

    /*
    取消订阅时候的处理
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                channel, subscribedChannels));

    }

    /*
    初始化按表达式的方式订阅时候的处理
      */
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println(pattern + ":" + subscribedChannels);
    }

    /*
    取得按表达式的方式订阅的消息后的处理
      */
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println(pattern + ":" + channel + "=" + message);
    }

    /*
    取消按表达式的方式订阅时候的处理
      */
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println(pattern + ":" + subscribedChannels);
    }

    // TODO，待确认
    @Override
    public void onPong(String pattern) {
        System.out.println(pattern);
    }

}
