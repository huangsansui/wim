package handler;

import common.Session;
import common.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocol.request.ListGroupMemberRequest;
import protocol.response.ListGroupMemberResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class ListGroupMemberRequestHandler extends SimpleChannelInboundHandler<ListGroupMemberRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberRequest msg) throws Exception {
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        ListGroupMemberResponse response = new ListGroupMemberResponse();
        if (channelGroup != null) {
            List<String> userList = new ArrayList<>();
            Iterator<Channel> iterator = channelGroup.iterator();
            while (iterator.hasNext()) {
                Session session = SessionUtil.getSession(iterator.next());
                userList.add(session.getUsername());
            }
            response.setIsExist(true);
            response.setGroupId(groupId);
            response.setUserList(userList);
        } else {
            response.setIsExist(false);
            response.setGroupId(groupId);
        }
        ctx.channel().writeAndFlush(response);
    }
}
