package handler;

import common.Session;
import common.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocol.request.GroupMessageRequest;
import protocol.response.GroupMessageResponse;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequest msg) throws Exception {
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        GroupMessageResponse response = new GroupMessageResponse();
        if (channelGroup != null) {
            Session session = SessionUtil.getSession(ctx.channel());
            String message = "【群消息】 " + session.getUsername() + "发来消息：" + msg.getMessage();
            System.out.println(message);
            response.setIsSuccess(true);
            response.setUsername(session.getUsername());
            response.setMessage(message);
            channelGroup.writeAndFlush(response);
        } else {
            response.setIsSuccess(false);
            response.setMessage("【群消息】 发送失败，groupId 不正确...");
            ctx.channel().writeAndFlush(response);
        }
    }
}
