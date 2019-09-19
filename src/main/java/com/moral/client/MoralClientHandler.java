package com.moral.client;

import com.moral.util.NetUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class MoralClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //服务端的远程地址
        System.out.println(ctx.channel().remoteAddress() + ", " + msg);

//        String message = "5A0010010B040ECF23B87FA2";
//        ByteBuf buffer = Unpooled.buffer(message.length());
//        buffer.writeBytes(NetUtils.hexToByteArray(message));
//        ctx.writeAndFlush(buffer);
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
//    }

    /**
     * 当服务器端与客户端进行建立连接的时候会触发，如果没有触发读写操作，则客户端和客户端之间不会进行数据通信，也就是channelRead0不会执行，
     * 当通道连接的时候，触发channelActive方法向服务端发送数据触发服务器端的handler的channelRead0回调，然后
     * 服务端向客户端发送数据触发客户端的channelRead0，依次触发。
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String message = "5A0010010B040ECF23B87FA2";
        ByteBuf buffer = Unpooled.buffer(message.length());
        buffer.writeBytes(NetUtils.hexToByteArray(message));
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
