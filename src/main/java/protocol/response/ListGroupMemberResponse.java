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
public class ListGroupMemberResponse extends Packet {

    private String groupId;

    private Boolean isExist;

    private List<String> userList;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBER_RESPONSE;
    }
}
