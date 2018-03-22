package yingdg.exercise.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by yingdg on 2018/1/30.
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap client = new Bootstrap();

        // 1.绑定线程组处理读写事件和连接
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group);

        // 2.绑定客户端通道
        client.channel(NioSocketChannel.class);

        // 3.给读写事件线程绑定handler去处理
        client.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new SimpleClientHandler());
                ch.pipeline().addLast(new LengthFieldPrepender(4, false));
                ch.pipeline().addLast(new StringEncoder());
            }
        });

        // 4.监听端口
        ChannelFuture future = client.connect("localhost", 9000).sync();
        // 发送数据
        future.channel().writeAndFlush("Hello Netty");

        future.channel().closeFuture().sync();
        group.shutdownGracefully().sync(); // 关闭
    }
}
