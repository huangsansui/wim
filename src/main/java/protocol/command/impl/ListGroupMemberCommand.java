package protocol.command.impl;

import io.netty.channel.Channel;
import protocol.command.ConsoleCommand;
import protocol.request.ListGroupMemberRequest;

import java.util.Scanner;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class ListGroupMemberCommand implements ConsoleCommand {


    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("请输入 groupId, 获取群成员列表");
        String groupId = scanner.nextLine();
        ListGroupMemberRequest request = new ListGroupMemberRequest();
        request.setGroupId(groupId);
        channel.writeAndFlush(request);
    }
}
