package com.moral.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutException;

public class MoralTimeoutHandler extends IdleStateHandler {

    private boolean closed;

    public MoralTimeoutHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
    }

    @Override
    protected final void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        assert evt.state() == IdleState.READER_IDLE;
        readTimedOut(ctx);
    }

    /**
     * Is called when a read timeout was detected.
     */
    protected void readTimedOut(ChannelHandlerContext ctx) throws Exception {
        if (!closed) {
            ctx.fireExceptionCaught(ReadTimeoutException.INSTANCE);
            ctx.close();
            closed = true;
        }
    }
}
