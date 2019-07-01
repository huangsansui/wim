package protocol.response;

import lombok.Data;
import protocol.Packet;
import protocol.command.Command;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
@Data
public class GroupMessageResponse extends Packet {

    private Boolean isSuccess;

    private String username;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
