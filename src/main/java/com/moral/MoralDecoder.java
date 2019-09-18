package com.moral;

import com.moral.util.NetUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MoralDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {

        //创建字节数组,buffer.readableBytes可读字节长度
        byte[] b = new byte[buffer.readableBytes()];

        //复制内容到字节数组b
        buffer.readBytes(b);

//        //字节数组转字符串
//        String str = new String(b);
//
//        System.out.println(str);

        out.add(NetUtils.bytesToHex(b));
    }
}
