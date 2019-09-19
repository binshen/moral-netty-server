package com.moral.client;

import com.moral.server.MoralDecoder;
import com.moral.server.MoralServerHandler;
import com.moral.server.MoralTimeoutHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

public class MoralClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new MoralTimeoutHandler(10, 10, 20));

        // 粘包/拆包: 固定长度
        pipeline.addLast(new FixedLengthFrameDecoder(12));

        // 自定义解码器
        pipeline.addLast(new MoralDecoder());

        // 自定义的处理器
        pipeline.addLast(new MoralClientHandler());
    }
}
