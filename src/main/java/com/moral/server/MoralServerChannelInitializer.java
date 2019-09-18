package com.moral.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

public class MoralServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new MoralTimeoutHandler(5, 10, 20));
        pipeline.addLast(new ModelIdleStateTrigger());

        // 粘包/拆包: 固定长度
        pipeline.addLast(new FixedLengthFrameDecoder(12));

        // 自定义解码器
        pipeline.addLast(new MoralDecoder());

        // 自定义的处理器
        pipeline.addLast(new MoralServerHandler());
    }
}
