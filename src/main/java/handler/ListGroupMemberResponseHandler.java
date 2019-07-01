package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.response.ListGroupMemberResponse;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class ListGroupMemberResponseHandler extends SimpleChannelInboundHandler<ListGroupMemberResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberResponse msg) throws Exception {
        if (msg.getIsExist()) {
            System.out.println("群组ID为：" + msg.getGroupId() + " 的群组成员列表为：" + msg.getUserList());
        } else {
            System.out.println("群组ID为：" + msg.getGroupId() + " 的群组不存在");
        }
    }
}
