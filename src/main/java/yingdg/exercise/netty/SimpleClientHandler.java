package yingdg.exercise.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.beans.Beans;
import java.nio.charset.Charset;

/**
 * Created by yingdg on 2018/1/30.
 */
public class SimpleClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // super.channelRead(ctx, msg);

        if (Beans.isInstanceOf(msg, ByteBuf.class)) {
            System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
        }
        ctx.close();
    }
}
