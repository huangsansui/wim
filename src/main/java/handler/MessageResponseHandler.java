package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.response.MessageResponse;

import java.util.Date;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponse> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponse msg) throws Exception {
        String fromUserId = msg.getFromUserId();
        String fromUserName = msg.getFromUserName();
        System.out.println(fromUserId + ":" + fromUserName + " -> " + msg .getMessage());

    }
}
