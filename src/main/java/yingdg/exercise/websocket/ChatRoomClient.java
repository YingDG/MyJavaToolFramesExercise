package yingdg.exercise.websocket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.apache.commons.lang3.StringUtils;

import java.net.ProtocolException;
import java.net.URI;

/**
 * @author yingdg
 * created in 2019/6/24 10:18 AM
 */
public class ChatRoomClient {
    private URI uri;
    private Channel channel;
    private ChatRoomClientHandler handler;

    public ChatRoomClient(String uri) {
        this.uri = URI.create(uri);
    }

    public void run() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        String protocal = uri.getScheme();
        if (!StringUtils.equals(protocal, "ws")) {
            throw new ProtocolException("Unsupported protocal:" + protocal);
        }
        handler = new ChatRoomClientHandler(uri);
        bootstrap.channel(NioSocketChannel.class)
                .group(workerGroup)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("http-codec", new HttpClientCodec());
                        pipeline.addLast("aggregator", new HttpObjectAggregator(65535));
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(handler);
                    }
                });

        this.channel = bootstrap.connect(uri.getHost(), uri.getPort()).sync().channel();
        ChannelFuture future = handler.handshakerFuture();//handshakerFuture用于等待握手结果，标识握手是否成功
        future.sync();//这里处理同步等待，一直等到握手成功，调用setSuccess()方法才会结束，终止等待状态
    }

    public void close() throws InterruptedException {
        this.channel.writeAndFlush(new CloseWebSocketFrame());
        this.channel.closeFuture().sync();//等待调用close()方法
    }

    public void send(final String text) {
        if (this.handler.handshakerFuture().isSuccess()) {
            this.channel.writeAndFlush(new TextWebSocketFrame(text));
        } else {
            System.out.println("没有握手成功！");
        }
    }

    public static void main(String[] args) {
        ChatRoomClient chatRoomClient = new ChatRoomClient("ws://localhost:8080"); // websocket协议地址
        try {
            chatRoomClient.run();
            chatRoomClient.send("chatRoom");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                chatRoomClient.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
