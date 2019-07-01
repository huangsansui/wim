package protocol.response;

import lombok.Data;
import protocol.Packet;
import protocol.command.Command;

import java.util.List;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
@Data
public class CreateGroupResponse extends Packet {

    private String groupId;

    private Boolean isSuccess;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
