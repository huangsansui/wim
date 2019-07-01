package protocol.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import protocol.Packet;
import protocol.command.Command;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
@Data
@AllArgsConstructor
public class MessageRequest extends Packet {

    private String toUserId;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
