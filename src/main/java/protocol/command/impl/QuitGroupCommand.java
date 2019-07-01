package protocol.command.impl;

import io.netty.channel.Channel;
import protocol.command.ConsoleCommand;
import protocol.request.QuitGroupRequest;

import java.util.Scanner;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class QuitGroupCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("请输入你要退出的群组 groupId:");
        String groupId = scanner.nextLine();

        QuitGroupRequest request = new QuitGroupRequest();
        request.setGroupId(groupId);
        channel.writeAndFlush(request);
    }
}
