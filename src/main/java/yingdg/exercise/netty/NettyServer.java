package yingdg.exercise.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by yingdg on 2018/1/30.
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap server = new ServerBootstrap();

        // 1.绑定两个线程组准备处理客户端的accept和读写事件
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        server.group(parentGroup, childGroup);

        // 2.绑定服务端通道
        server.channel(NioServerSocketChannel.class);

        // 3.给读写事件线程绑定handler去处理
        server.childHandler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(
                        Integer.MAX_VALUE, 0, 4, -1, 4));
                ch.pipeline().addLast(new SimpleServerHandler());
                ch.pipeline().addLast(new LengthFieldPrepender(4, false));
                ch.pipeline().addLast(new StringEncoder());
            }
        });

        // 4.监听端口
        ChannelFuture future = server.bind(9000).sync();
        future.channel().closeFuture().sync();
    }
}
