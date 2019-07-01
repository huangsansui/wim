package protocol.command;

import io.netty.channel.Channel;
import protocol.command.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class ConsoleCommandManager implements ConsoleCommand{

    private Map<String, ConsoleCommand> map;

    public ConsoleCommandManager() {

        map = new HashMap<>();
        map.put("createGroup", new CreateGroupCommand());
        map.put("joinGroup", new JoinGroupCommand());
        map.put("quitGroup", new QuitGroupCommand());
        map.put("groupMember", new ListGroupMemberCommand());
        map.put("sendToGroup", new GroupMessageCommand());
    }


    @Override
    public void exec(Scanner scanner, Channel channel) {

        String command = scanner.nextLine();

        ConsoleCommand consoleCommand = map.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.out.println("无法识别【" + command + "】命令，请重新输入");
        }
    }
}
