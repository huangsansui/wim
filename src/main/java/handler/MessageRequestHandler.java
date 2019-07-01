package handler;

import common.Session;
import common.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.request.MessageRequest;
import protocol.response.MessageResponse;

import java.util.Date;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequest msg) throws Exception {

        System.out.println(new Date() + " 收到客户端发来的消息:【" + msg.getMessage() + "】");

        // 拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 构造发送消息
        MessageResponse response = new MessageResponse();
        response.setMessage(msg.getMessage());
        response.setFromUserName(session.getUsername());
        response.setFromUserId(session.getUserId());

        // 拿到接收方的会话消息
        Channel channel = SessionUtil.getChannel(msg.getToUserId());

        // 判断对方是否在线
        if (SessionUtil.hashLogin(channel)) {
            channel.writeAndFlush(response);
        } else {
            System.out.println("【" + msg.getToUserId() +"】不在线，发送失败");

        }

//        ctx.channel().writeAndFlush(response);
    }
}
