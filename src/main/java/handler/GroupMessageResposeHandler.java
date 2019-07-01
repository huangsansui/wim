package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.response.GroupMessageResponse;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class GroupMessageResposeHandler extends SimpleChannelInboundHandler<GroupMessageResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponse msg) throws Exception {
        if (msg.getIsSuccess()) {
            System.out.println(msg.getMessage());
        } else {
            System.out.println(msg.getMessage());

        }
    }
}
