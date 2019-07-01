package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.request.JoinGroupRequest;
import protocol.response.JoinGroupResponse;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponse> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponse msg) throws Exception {
        if (msg.getIsSuccess()) {
            System.out.println("用户 " + msg.getUsername() + " 成功加入群聊，群组ID为：" + msg.getGroupId());
        } else {
            System.out.println("加入群聊失败");
        }
    }
}
