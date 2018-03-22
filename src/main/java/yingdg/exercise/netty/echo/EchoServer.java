package yingdg.exercise.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by yingdg on 2017/8/18.
 */
public class EchoServer {
    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        //create ServerBootstrap instance
        ServerBootstrap b = new ServerBootstrap();
        //Specifies NIO transport, local socket address
        b.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(port)
                .childHandler(new ChannelInitializer<Channel>() {
                    //Adds handler to channel pipeline
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new EchoServerHandler());
                    }
                });

        //Binds server, waits for server to close, and releases resources
        ChannelFuture f = b.bind().sync();
        System.out.println(EchoServer.class.getSimpleName() + "started and listen on " + f.channel().localAddress());
        f.channel().closeFuture().sync();

        group.shutdownGracefully().sync();
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServer(8080).start();
    }
}
