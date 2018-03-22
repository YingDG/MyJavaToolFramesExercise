package yingdg.exercise.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.beans.Beans;
import java.nio.charset.Charset;

/**
 * Created by yingdg on 2018/1/30.
 */
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {
    /*
    读取客户端通道数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // super.channelRead(ctx, msg);

        if (Beans.isInstanceOf(msg, ByteBuf.class)) {
            System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
        }
        ctx.channel().writeAndFlush("OK");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
