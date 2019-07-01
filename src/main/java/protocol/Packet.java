package protocol;

import lombok.Data;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
@Data
public abstract class Packet {

    private byte version = 1;

    public abstract Byte getCommand();
}
