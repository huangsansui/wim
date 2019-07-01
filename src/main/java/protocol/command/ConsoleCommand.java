package protocol.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}
