package protocol.response;

import lombok.Data;
import protocol.Packet;
import protocol.command.Command;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
@Data
public class MessageResponse extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
