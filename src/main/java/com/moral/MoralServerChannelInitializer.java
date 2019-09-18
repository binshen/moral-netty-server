package com.moral;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class MoralServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //IdleStateHandler心跳机制, 如果超时触发Handle中userEventTrigger()方法
        pipeline.addLast("idleStateHandler", new IdleStateHandler(5, 0, 0, TimeUnit.MINUTES));

//        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
//
//        pipeline.addLast(new LengthFieldPrepender(4));
//
//        //字符串编码
//        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
//
//        //字符串解码
//        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));

        pipeline.addLast(new MoralDecoder());

        //自己定义的处理器
        pipeline.addLast(new MoralServerHandler());
    }
}
