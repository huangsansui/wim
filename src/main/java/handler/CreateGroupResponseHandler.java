package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.response.CreateGroupResponse;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponse msg) throws Exception {
        if (msg.getIsSuccess()) {
            System.out.println("创建群组成功，群组ID【" + msg.getGroupId() + "】,群组成员有：" + msg.getUserNameList());
        } else {
            System.out.println("创建群组失败");
        }
    }
}
