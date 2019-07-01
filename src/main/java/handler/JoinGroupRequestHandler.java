package handler;

import common.Session;
import common.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocol.request.JoinGroupRequest;
import protocol.response.JoinGroupResponse;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequest msg) throws Exception {

        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        JoinGroupResponse response = new JoinGroupResponse();

        if (channelGroup != null) {
            channelGroup.add(ctx.channel());
            Session session = SessionUtil.getSession(ctx.channel());
            System.out.println("【加入群聊】 用户 " + session.getUsername() + "加入了群聊, 群组ID为：" + groupId);
            response.setIsSuccess(true);
            response.setGroupId(groupId);
            response.setUsername(session.getUsername());
            channelGroup.writeAndFlush(response);
        } else {
            System.out.println("【加入群聊】 加入群聊失败，群组ID不正确...");
            response.setIsSuccess(false);
            response.setGroupId(groupId);
            ctx.channel().writeAndFlush(response);
        }
    }
}
