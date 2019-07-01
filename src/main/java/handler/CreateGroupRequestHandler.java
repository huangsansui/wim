package handler;

import common.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import protocol.request.CreateGroupRequest;
import protocol.response.CreateGroupResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequest msg) throws Exception {
        List<String> userList = msg.getUserList();

        List<String> userNameList = new ArrayList<>();

        ChannelGroup group = new DefaultChannelGroup(ctx.executor());

        for (String s : userList) {
            Channel channel = SessionUtil.getChannel(s);
            if (channel != null) {
                group.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUsername());
            }
        }



        CreateGroupResponse response = new CreateGroupResponse();
        String groupId = UUID.randomUUID().toString().substring(0, 8);
        response.setGroupId(groupId);
        response.setIsSuccess(true);
        response.setUserNameList(userNameList);

        // 记录群组
        SessionUtil.addChanelGroup(groupId, group);

        group.writeAndFlush(response);

        System.out.println("创建群组成功，群组ID：【" + groupId + "】");
        System.out.println("群组成员有: " + response.getUserNameList());
    }
}
