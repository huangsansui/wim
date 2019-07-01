package handler;

import common.Session;
import common.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocol.request.QuitGroupRequest;
import protocol.response.QuitGroupResponse;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequest msg) throws Exception {
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        if (channelGroup != null) {
            Session session = SessionUtil.getSession(ctx.channel());
            channelGroup.remove(ctx.channel());
            QuitGroupResponse response = new QuitGroupResponse();
            response.setUsername(session.getUsername());
            channelGroup.writeAndFlush(response);
            ctx.channel().writeAndFlush(response);
        }
    }
}
