package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.request.HeartBeatRequest;
import protocol.response.HeartBeatResponse;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequest msg) throws Exception {
        ctx.writeAndFlush(new HeartBeatResponse());
    }
}
