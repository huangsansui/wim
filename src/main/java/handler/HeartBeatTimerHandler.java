package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.request.HeartBeatRequest;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHearBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHearBeat(ChannelHandlerContext ctx) {

        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequest());
                scheduleSendHearBeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
