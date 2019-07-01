package protocol.command.impl;

import io.netty.channel.Channel;
import protocol.command.ConsoleCommand;
import protocol.request.JoinGroupRequest;

import java.util.Scanner;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class JoinGroupCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【加入群聊】 请输入你要加入的群组ID：");
        String groupId = scanner.nextLine();
        JoinGroupRequest request = new JoinGroupRequest();
        request.setGroupId(groupId);

        channel.writeAndFlush(request);
    }
}
